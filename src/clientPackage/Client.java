package clientPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.56.1", 1234);
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Client connecte au serveur.");


            System.out.print("Entrez un message : ");
            String message = clavier.readLine();

            output.println(message);
            String reponse = input.readLine();
            System.out.println("Reponse du serveur: " + reponse);


        input.close();
        output.close();
        socket.close();
        System.out.println("Client arrete.");
    }
}