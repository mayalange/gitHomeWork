public class Lesson11Main {

    public static void main(String[] args) {
        Circle circle = new Circle(10);
        Triangle triangle = new Triangle(5, 8);
        Square square = new Square(5);

        double sum = 0;

        Figure[] figures = {circle, triangle, square};
        for (Figure figure : figures) {
            sum += figure.area();
        }
        System.out.println(sum);
    }
}