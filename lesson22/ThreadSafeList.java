import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList {

    private static final LinkedList<Integer> list = new LinkedList<>();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void action() throws InterruptedException{
        readWriteLock.writeLock().lock();
        try {
            list.add(10);
        } finally {
            readWriteLock.writeLock().unlock();
        }

        readWriteLock.readLock().lock();
        try {
            list.get(0);
        } finally {
            readWriteLock.readLock().unlock();
        }

        readWriteLock.writeLock().lock();
        try {
            list.remove(0);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}