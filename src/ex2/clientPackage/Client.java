package clientPackage;


import modelPackage.Operation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Client connecte au serveur.");

        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner clavier = new Scanner(System.in);

        System.out.print("Entrez le premier operande : ");
        int operande1 = clavier.nextInt();

        System.out.print("Entrez l operateur (+, -, *, /) : ");
        String operateur = clavier.next();

        System.out.print("Entrez le deuxieme operande : ");
        int operande2 = clavier.nextInt();

        if (!operateur.equals("+") && !operateur.equals("-") && !operateur.equals("*") && !operateur.equals("/")) {
            System.out.println("Erreur : operateur invalide.");
            socket.close();
            return;
        }

        Operation operation = new Operation(operande1, operateur, operande2);
        objectOutput.writeObject(operation);
        objectOutput.flush();

        String resultat = input.readLine();
        System.out.println("Resultat recu : " + resultat);

        objectOutput.close();
        input.close();
        socket.close();
    }
}