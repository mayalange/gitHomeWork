public class Square implements Figure{

    private final double sideLength;

    public double getSideLength() {
        return sideLength;
    }

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double area() {
        double area = getSideLength()*getSideLength();
        System.out.println("Area of Square = " + area);

        return area;
    }
}