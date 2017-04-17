package me.diax.diaxbot;

import me.diax.diaxbot.events.ChannelMessageEvent;
import me.diax.diaxbot.events.SystemEvent;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public class IRCBot implements Runnable {

    private static final ExecutorService exec = Executors.newCachedThreadPool();

    private Socket socket;

    private String server;
    private String login;
    private String nickname;
    private Set<String> channels;

    private boolean loggedIn = false;

    private BufferedReader reader;
    private BufferedWriter writer;

    private Set<Integer> waitForCode;
    private List<EventHandler> eventHandlers;

    public IRCBot(String server, String nickname, String login) throws IOException {
        this.server = server;
        this.nickname = nickname;
        this.login = login;
        channels = new HashSet<>();
        waitForCode = new HashSet<>();
        eventHandlers = new ArrayList<>();
        socket = new Socket(server, 6667);
        if(socket.isConnected()) {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Connection established. Logging in.");
            exec.execute(this);
            eventHandlers.add(new EventHandler() {
                @Override
                public void onSystemEvent(SystemEvent event) {
                    switch (event.getCode()) {
                        case 001:
                            loggedIn = true;
                            break;
                        case 443:
                            throw new IllegalArgumentException("Username was in use!");
                        default:
                            //System.out.println("[SystemEvent]: " + event.getIrcResponse());
                    }
                }
            });
            login();
        }
    }

    private void read() throws IOException {
        String line = reader.readLine();
        if(line != null) {
            //System.out.println(line);
            if(line.contains(" PING ")) {
                System.err.println("Ping Recieved: " + line);
                writeMessage("PONG" + line.substring(5));
            }
            // TODO: Event matching.
            else if(Patterns.SYSTEM_EVENT_PATTERN.matcher(line).find()) {
                Matcher m = Patterns.SYSTEM_EVENT_PATTERN.matcher(line);
                if(m.find()) {
                    SystemEvent event = new SystemEvent(this, m);
                    eventHandlers.forEach(eh -> eh.onSystemEvent(event));
                }
            } else {
                System.out.println("Unhandled Event: " + line);
            }
        }
    }

    public void writeMessage(String message) {
        if(!socket.isConnected())
            throw new IllegalArgumentException("Attempted to send message on closed socket!");

        try {
            writer.write(message + "\r\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to write message to server!");
            e.printStackTrace();
        }
    }

    public void joinChannel(String... channels) {
        for(String channel : channels) {
            if(this.channels.contains(channel))
                throw new IllegalArgumentException("You have already joined " + channel);

            this.channels.add(channel);
            writeMessage("JOIN " + channel);
        }
    }

    private void login() {
        if(loggedIn)
            throw new IllegalArgumentException("Client is already logged in!");

        writeMessage("NICK " + nickname + "\r\nUSER " + login + " 8 * NachtBotTest");
        while(!loggedIn) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Logged in successfully.");
    }

    public void addEventHandler(EventHandler... handlers) {
        eventHandlers.addAll(Arrays.asList(handlers));
    }

    public void removeEventHandler(EventHandler... handlers) {
        eventHandlers.removeAll(Arrays.asList(handlers));
    }

    @Override
    public void run() {
        while(!socket.isClosed()) {
            try {
                read();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client " + nickname + " has been disconnected.");
    }
}
