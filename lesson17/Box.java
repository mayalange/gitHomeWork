import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> fruits = new ArrayList<>();
    private final Class<T> fruitType;

    public Box(Class<T> fruitType) {
        this.fruitType = fruitType;
    }

    public void add(T fruit) {
        if (!fruitType.isInstance(fruit)) {
            throw new IllegalArgumentException("Нельзя добавить " + fruit.getClass().getSimpleName() +
                    " в коробку для " + fruitType.getSimpleName());
        }
        fruits.add(fruit);
    }

    public double getWeight() {
        if (fruits.isEmpty()) {
            return 0.0;
        }
        double singleFruitWeight = fruits.get(0).getWeight();
        return fruits.size() * singleFruitWeight;
    }

    public boolean compare(Box<?> otherBox) {
        if (otherBox == null) {
            return false;
        }
        return Math.abs(this.getWeight() - otherBox.getWeight()) < 0.0001;
    }

    public void transfer(Box<T> destinationBox) {
        if (destinationBox == this) {
            throw new IllegalArgumentException("Нельзя пересыпать фрукты в ту же коробку");
        }
        if (!destinationBox.fruitType.equals(this.fruitType)) {
            throw new IllegalArgumentException("Нельзя пересыпать " + this.fruitType.getSimpleName() +
                    " в коробку для " + destinationBox.fruitType.getSimpleName());
        }
        destinationBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
