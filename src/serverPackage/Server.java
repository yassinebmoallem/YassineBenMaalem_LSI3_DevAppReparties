package serverPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Serveur en attente d un client...");

        Socket socket = serverSocket.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Un client est connecte.");

        String message;
        while ((message = input.readLine()) != null) {
            if (message.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Message recu: " + message);
            output.println("Bien recu: " + message);
        }

        input.close();
        output.close();
        socket.close();
        serverSocket.close();
        System.out.println("Serveur arrete.");
    }
}