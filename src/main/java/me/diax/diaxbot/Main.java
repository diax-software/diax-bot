package me.diax.diaxbot;

public class Main {



    public static void main(String[] args) throws Exception {

        // The server to connect to and our details.
        String server = "irc.domirc.net";
        String nick = "NachtBot";
        String login = "NachtBot";

        // The channel which the bot will join.
        String channel = "#diax.me";

        IRCBot test = new IRCBot(server, nick, login);
        test.joinChannel(channel, "##testing");

//        // Connect directly to the IRC server.
//        Socket socket = new Socket(server, 6667);
//        BufferedWriter writer = new BufferedWriter(
//                new OutputStreamWriter(socket.getOutputStream( )));
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(socket.getInputStream( )));
//
//        // Log on to the server.
//        writer.write("NICK " + nick + "\r\n");
//        writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
//        writer.flush( );
//
//        // Read lines from the server until it tells us we have connected.
//        System.out.println("Logging in.");
//        String line = null;
//        while ((line = reader.readLine( )) != null) {
//            if (line.contains("004")) {
//                // We are now logged in.
//                break;
//            }
//            else if (line.contains("433")) {
//                System.out.println("Nickname is already in use.");
//                return;
//            }
//        }
//        System.out.println("Logged in.");
//
//        // Join the channel.
//        System.out.println("Joining channel: " + channel);
//        writer.write("JOIN " + channel + "\r\n");
//        writer.flush();
//
//        // Keep reading lines from the server.
//        while ((line = reader.readLine( )) != null) {
//            if (line.startsWith("PING")) {
//                System.err.println("Got a ping.");
//                // We must respond to PINGs to avoid being disconnected.
//                writer.write("PONG " + line.substring(5) + "\r\n");
//                writer.flush();
//            }
//            else {
//                // Print the raw line received by the bot.
//                if(messagePattern.matcher(line).matches()) {
//                    MessageEvent sender = new MessageEvent(line);
//                    System.out.println(sender.toString());
//                } else if(userJoinPattern.matcher(line).matches()) {
//                    JoinEvent join = new JoinEvent(line);
//                    System.out.println(join.toString());
//                } else if(userLeavePattern.matcher(line).find()) {
//                    QuitEvent quit = new QuitEvent(line);
//                    System.out.println(quit.toString());
//                } else {
//                    System.out.println("[UNKNOWN] " + line);
//                }
//            }
//        }
    }

//    public static class MessageEvent {
//
//        private String user;
//        private String client;
//        private String address;
//        private String channel;
//        private String message;
//
//        public MessageEvent(String s) {
//            Matcher m = messagePattern.matcher(s);
//            if(m.find()) {
//                user = m.group(1);
//                client = m.group(2);
//                address = m.group(3);
//                channel = m.group(4);
//                message = m.group(5);
//            }
//        }
//
//        public String getUser() {
//            return user;
//        }
//
//        public String getClient() {
//            return client;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//
//        public String getChannel() {
//            return channel;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        @Override
//        public String toString() {
//            return "[" + channel + "][" + user + "]: " + message;
//        }
//    }
//
//    public static class JoinEvent {
//
//        private String user;
//        private String client;
//        private String address;
//        private String channel;
//
//        public JoinEvent(String s) {
//            Matcher m = userJoinPattern.matcher(s);
//            if(m.find()) {
//                user = m.group(1);
//                client = m.group(2);
//                address = m.group(3);
//                channel = m.group(4);
//            }
//        }
//
//        public String getUser() {
//            return user;
//        }
//
//        public String getClient() {
//            return client;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//
//        public String getChannel() {
//            return channel;
//        }
//
//        @Override
//        public String toString() {
//            return "[" + channel + "]: User joined, " + user + ".";
//        }
//    }
//
//    public static class QuitEvent {
//
//        private String user;
//        private String client;
//        private String address;
//        private String reason;
//
//        public QuitEvent(String s) {
//            Matcher m = userJoinPattern.matcher(s);
//            if(m.find()) {
//                user = m.group(1);
//                client = m.group(2);
//                address = m.group(3);
//                reason = m.group(4);
//            }
//        }
//
//        public String getUser() {
//            return user;
//        }
//
//        public String getClient() {
//            return client;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//
//        public String getReason() {
//            return reason;
//        }
//
//        @Override
//        public String toString() {
//            return "[    ]: User quit, " + user + ", for reason: " + reason;
//        }
//    }

}