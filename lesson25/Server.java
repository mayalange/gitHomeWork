import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7)) {
            System.out.println("server started");
            Socket accept = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());

            Thread clientMessageThread = new Thread(() -> {
                try {
                    while (true) {
                        String strFromClient = dataInputStream.readUTF();
                        if (strFromClient.equals("/end")) break;
                        System.out.println(strFromClient);
                    }
                } catch(IOException e){
                    System.out.println("Error");
                }
            });
            clientMessageThread.start();

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String serverMessage = scanner.nextLine();
                dataOutputStream.writeUTF(serverMessage);
                System.out.println("The message has been sent. Message: " + serverMessage);
                if (serverMessage.equals("/end")) break;
            }
            clientMessageThread.interrupt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
