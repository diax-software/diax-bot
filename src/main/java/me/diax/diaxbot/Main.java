package me.diax.diaxbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Comportment on 16/04/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String server = "irc.domirc.net";
        String nick = "diax";
        String login = "diax";
        String channel = "#diax.me";

        Socket socket = new Socket(server, 6667);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("NICK " + nick + "\r\n");
        writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
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