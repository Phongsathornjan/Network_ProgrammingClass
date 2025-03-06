import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScan {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java PortScan <hostname/IP>");
            return;
        }

        String host = args[0];

        try {
            for (int port = 70; port <= 100; port++) {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(host, port), 200);
                    System.out.println("port " + port + ": open");
                } catch (IOException e) { }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown host or IP address");
        }
    }
}
