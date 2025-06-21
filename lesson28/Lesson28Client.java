import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Lesson28Client {
    private static Selector selector;
    private static SocketChannel socketChannel;
    private static boolean running = true;

    public static void main(String[] args) {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 7));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (running) {
                    try {
                        if (System.in.available() > 0) {
                            String message = scanner.nextLine();
                            if (message.equalsIgnoreCase("exit")) {
                                running = false;
                                selector.wakeup();
                                break;
                            }
                            sendMessage(message);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                scanner.close();
            }).start();

            while (running) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isConnectable()) {
                        stopConnection(key);
                    } else if (key.isReadable()) {
                        readMessage();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void stopConnection(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            if (channel.finishConnect()) {
                channel.register(selector, SelectionKey.OP_READ);
                System.out.println("Подключился к серверу");
            }
        } catch (IOException e) {
            System.out.println("Не смог подключиться к серверу");
            key.cancel();
            running = false;
        }
    }

    private static void readMessage() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String message = new String(bytes);
        System.out.println("Получено сообщение от сервера: " + message);
    }

    private static void sendMessage(String message) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            socketChannel.write(buffer);
        } catch (IOException e) {
            System.out.println("Ошибка отправки сообщения " + e.getMessage());
        }
    }
}