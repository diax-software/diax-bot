package me.diax.bot.bots.irc;

import me.diax.bot.lib.AbstractDiaxBot;
import me.diax.bot.lib.objects.DiaxChannel;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by Comportment on 19/04/17.
 * <p>
 * Lets make this pretty again.
 */
public class DiaxIRCBot extends AbstractDiaxBot {

    private final String host;
    private final int port;
    private Socket socket;
    private String[] channels;
    private BufferedReader reader;
    private BufferedWriter writer;

    public DiaxIRCBot(String host, int port, String... channels) {
        this.host = host;
        this.port = port;
        this.channels = channels.clone();
    }

    @Override
    public DiaxIRCBot start() throws Exception {
        System.out.println("Starting...");
        socket = new Socket(host, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        return this;
    }

    @Override
    public DiaxIRCBot stop() throws Exception {
        socket = null;
        return this;
    }

    @Override
    public AbstractDiaxBot messageTo(DiaxChannel channel, String message) {
        writeToChannel(message, channel.getName());
        return this;
    }

    private DiaxIRCBot writeToChannel(String message, String... channels) {
        Arrays.stream(channels).forEach(channel -> writeMessage(String.format("PRIVMSG %s %s", channel, message)));
        return this;
    }

    private DiaxIRCBot writeMessage(String message) {
        if (!socket.isConnected())
            throw new IllegalArgumentException("Attempted to send a message on a closed socket.");
        try {
            writer.write(message + "\r\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to write message to server!");
            e.printStackTrace();
        }
        return this;
    }
}