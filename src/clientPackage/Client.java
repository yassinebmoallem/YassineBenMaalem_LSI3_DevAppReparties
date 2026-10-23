package clientPackage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public static void main (String [] args ) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Je suis un client pas encore connecté…");
        Socket socket = new Socket("192.168.1.5", 1234);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        System.out.println("je suis un client connecté");



        int x;
        do {
            System.out.print("donner un entier: ");
            x = scanner.nextInt();
            output.writeInt(x);
            int result = input.readInt();
            System.out.println("Résultat reçu: " + x + " * 5 = " + result);
        }while (x!=0);

        input.close();
        output.close();
        socket.close();
        scanner.close();
        System.out.println("Client arrêté.");
    }
}
