public class Triangle implements Figure{

    private final double sideLength;
    private final double height;

    public double getHeight() {
        return height;
    }

    public double getSideLength() {
        return sideLength;
    }

    public Triangle(double sideLength, double height) {
        this.sideLength = sideLength;
        this.height = height;
    }

    @Override
    public double area() {
        double area = getSideLength() * getHeight()/2;
        System.out.println("Area of triangle = " + area);

        return area;
    }
}
