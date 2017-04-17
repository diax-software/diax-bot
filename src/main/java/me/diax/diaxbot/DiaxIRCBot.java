package me.diax.diaxbot;

import me.diax.diaxbot.util.AbstractDiaxBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by Comportment on 16/04/17.
 *
 * Don't worry, if it breaks we can run to NachtRaben.
 */
public class DiaxIRCBot extends AbstractDiaxBot {

    public DiaxIRCBot(int port, String server, String... channel) throws Exception {
        Socket socket = new Socket(server, port);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("NICK Diax \r\n");
        writer.write("USER Diax 8 * : Diax multiplatform bot.\r\n");
        writer.flush();

        String line;
        while ((line = reader.readLine( )) != null) {
            if (line.contains("004")) {
                break;
            } else if (line.contains("433")) {
                System.err.println("Nickname is already in use.");
            }
        }

        Arrays.stream(channel).forEach(string -> {
            try {
                writer.write("JOIN " + string + "\r\n");
            } catch (Exception ignored){}
        });
        writer.flush();

        while ((line = reader.readLine( )) != null) {
            System.out.println(line);
            if (line.startsWith("PING ")) {
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.flush();
            } else if (line.contains("meow")) {
                writer.write("PRIVMSG #diax.me meooooooooooooooooooooooooooooooooooooowww\r\n");
                writer.flush();
            }
        }
    }
}