package me.diax.diaxbot.bots;

import me.diax.diaxbot.EventHandler;
import me.diax.diaxbot.IRCPatterns;
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
public class DiaxIRCBot extends DiaxAbstractBot {

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

    public DiaxIRCBot(String server, String nickname, String login) throws IOException {
        this.server = server;
        this.nickname = nickname;
        this.login = login;
        channels = new HashSet<>();
        waitForCode = new HashSet<>();
        eventHandlers = new ArrayList<>();
        socket = new Socket(server, 6667);
        if (socket.isConnected()) {
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

    private DiaxIRCBot read() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            if (line.startsWith("PING")) {
                writeMessage("PONG" + line.substring(5));
            } else if (IRCPatterns.SYSTEM_EVENT_PATTERN.matcher(line).find()) {
                Matcher m = IRCPatterns.SYSTEM_EVENT_PATTERN.matcher(line);
                if (m.find()) {
                    SystemEvent event = new SystemEvent(this, m);
                    eventHandlers.forEach(eh -> eh.onSystemEvent(event));
                }
            } else {
                System.err.println("Unhandled Event: " + line);
            }
        }
        return this;
    }

    private DiaxIRCBot writeMessage(String message) {
        if (!socket.isConnected()) throw new IllegalArgumentException("Attempted to send a message on a closed socket.");
        try {
            writer.write(message + "\r\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to write message to server!");
            e.printStackTrace();
        }
        return this;
    }

    private DiaxIRCBot writeToChannel(String message, String... channels) {
        Arrays.stream(channels).forEach(channel ->
            writeMessage(String.format("PRIVMSG %s %s", channel, message)));
        return this;
    }

    public DiaxIRCBot joinChannel(String... channels) {
        for (String channel : channels) {
            if (this.channels.contains(channel))
                throw new IllegalArgumentException("You have already joined " + channel);
            this.channels.add(channel);
            writeMessage("JOIN " + channel).writeToChannel("Hello!", channel);
        }
        return this;
    }

    private DiaxIRCBot login() {
        if (loggedIn) throw new IllegalArgumentException("Client is already logged in!");
        writeMessage("NICK " + nickname + "\r\nUSER " + login + " 8 * NachtBotTest");
        while (!loggedIn) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Logged in successfully.");
        return this;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client " + nickname + " has been disconnected.");
    }
}
