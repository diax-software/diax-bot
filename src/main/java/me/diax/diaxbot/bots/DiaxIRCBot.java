package me.diax.diaxbot.bots;

import me.diax.diaxbot.lib.bots.DiaxBot;

import java.io.*;
import java.net.Socket;

/**
 * Created by comportment on 16/04/17.
 */
public class DiaxIRCBot extends DiaxBot {

    private final String server;
    private final String channel;
    private final int port;

    public DiaxIRCBot(String server, String channel, int port) {
        this.server = server;
        this.channel = channel;
        this.port = port;
        try {
            this.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        Socket socket = new Socket(server, port);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.write("NICK 0 0 Diax\r\n");
        writer.write("USER Diax * : Java IRC Bot\r\n");
        writer.flush();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("433")) {
                System.err.println("Nickname is already in use.");
                return;
            }
        }

        writer.write("JOIN " + channel + "\r\n");
        writer.flush();

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if (line.startsWith("PING ")) {
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.flush();
            }
        }
    }
}
