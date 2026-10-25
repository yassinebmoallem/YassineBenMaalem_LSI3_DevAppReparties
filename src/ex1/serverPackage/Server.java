package serverPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Serveur en attente d'un client...");
        Socket socket = serverSocket.accept();
        System.out.println("Un client est connecté.");
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        String operation = input.readLine();
        System.out.println("Opération reçue : " + operation);
        String resultat = calculer(operation);
        output.println(resultat);
        System.out.println("Résultat envoyé : " + resultat);
        socket.close();
        serverSocket.close();
    }

    private static String calculer(String operation) {
        Pattern pattern = Pattern.compile("^(\\d+)\\s*([+*/-])\\s*(\\d+)$");
        Matcher matcher = pattern.matcher(operation);
        if (!matcher.matches()) {
            return "Erreur : opération invalide.";
        }
        int a = Integer.parseInt(matcher.group(1));
        String operateur = matcher.group(2);
        int b = Integer.parseInt(matcher.group(3));
        int resultat = 0;
        switch (operateur) {
            case "+":
                resultat = a + b;
                break;
            case "-":
                resultat = a - b;
                break;
            case "*":
                resultat = a * b;
                break;
            case "/":
                if (b == 0)
                    return "Erreur : division par zéro.";
                resultat = a / b;
                break;
        }
        return String.valueOf(resultat);
    }
}