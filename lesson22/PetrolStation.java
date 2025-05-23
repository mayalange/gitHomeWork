import lombok.Data;

import java.util.Random;
import java.util.concurrent.Semaphore;

@Data
public class PetrolStation {
    private final Semaphore smp = new Semaphore(3, true);

    Random random = new Random();
    float amount;
    int timeForTank;

    public PetrolStation(float amount) throws ArithmeticException {
        this.amount = amount;
    }

    public void doTank(float value) throws InterruptedException {
        synchronized (this) {
            if (value > amount) {
                throw new ArithmeticException("Sorry we doesn't have more fuel");
            }
        }

        System.out.println("Перед занятием потока " + Thread.currentThread().getId());
        smp.acquire();

        try {
            System.out.println("Выполняется слип " + Thread.currentThread().getId());
            Thread.sleep(random.nextInt(3000, 11000));
            synchronized (this) {
                if (value < amount) {
                    amount = amount - value;
                    System.out.println("Закончил заправляться " + Thread.currentThread().getId());
                } else {
                    throw new ArithmeticException("Sorry we doesn't have more fuel for " + Thread.currentThread().getId() +
                            ", " + value + ", current fuel: " + getAmount());
                }
            }
        } finally {
            smp.release();
        }
    }
}