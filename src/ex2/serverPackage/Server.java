package ex2.serverPackage;

import ex2.modelPackage.Operation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Serveur en attente d un client...");

        Socket socket = serverSocket.accept();
        System.out.println("Un client est connecte.");

        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        Operation operation = (Operation) objectInput.readObject();
        System.out.println("Objet recu : " + operation.getOperande1() + " " +
                operation.getOperateur() + " " + operation.getOperande2());

        int a = operation.getOperande1();
        String operateur = operation.getOperateur();
        int b = operation.getOperande2();
        int resultat = 0;

        switch (operateur) {
            case "+": resultat = a + b; break;
            case "-": resultat = a - b; break;
            case "*": resultat = a * b; break;
            case "/":
                if (b == 0) {
                    output.println("Erreur : division par zero.");
                    objectInput.close();
                    output.close();
                    socket.close();
                    serverSocket.close();
                    return;
                }
                resultat = a / b;
                break;
        }

        output.println(String.valueOf(resultat));
        System.out.println("Resultat envoye : " + resultat);

        objectInput.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}