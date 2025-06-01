import java.io.DataOutputStream;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("localhost", 7)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            Thread serverMessageThread = new Thread(() -> {
                try {
                    while (true) {
                        String strFromServer = dataInputStream.readUTF();
                        if (strFromServer.contains("/end")) break;
                        System.out.println(strFromServer);
                        dataOutputStream.writeUTF("Echo: " + strFromServer);
                    }
                } catch (IOException e) {
                    System.out.println("Error");
                }
            });
            serverMessageThread.start();
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String clientMessage = scanner.nextLine();
                dataOutputStream.writeUTF(clientMessage);
                if (clientMessage.equals("/end")) break;
                System.out.println("The message has been sent. Message: " + clientMessage);
            }
            serverMessageThread.interrupt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
