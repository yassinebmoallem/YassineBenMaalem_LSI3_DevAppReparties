package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String [] args) throws IOException {
        ServerSocket socketServeur = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente la connexion d'un client ");
        Socket socket = socketServeur.accept();
        System.out.println("un client est connecté");

        DataInputStream input= new DataInputStream((socket.getInputStream() ));
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        int x;
        do {
            x = input.readInt();
            int result = x*5;
            System.out.println("Numéro reçu: "+ x );
            System.out.println("Résultat calculé: " + result);
            output.writeInt(result);
        }while (x!=0);



        input.close();
        output.close();
        socket.close();
        socketServeur.close();
        System.out.println("Serveur arrêté.");
    }
}


