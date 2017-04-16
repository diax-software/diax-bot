package me.diax.diaxbot.bots;

import me.diax.diaxbot.lib.bots.DiaxBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by comportment on 16/04/17.
 */
public class DiaxIRCBot extends DiaxBot {

    private final String server;
    private final String channel;
    private final int port;

    public DiaxIRCBot(String server, String channel, int port) throws Exception {
        this.server = server;
        this.channel = channel;
        this.port = port;
        this.init();
    }

    public void init() throws Exception {
        Socket socket = new Socket(server, port);
        socket.setKeepAlive(true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("NICK Diax\r\n");
        writer.write("USER Diax * : Java IRC Bot\r\n");
        writer.flush();

        String line;
        while ((line = reader.readLine()) != null) {

            if (line.indexOf("004") >= 0) {
                break;
            } else if (line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
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
