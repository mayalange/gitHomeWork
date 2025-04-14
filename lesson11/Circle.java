public class Circle implements Figure{

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        double area = Math.PI *  Math.pow(getRadius(), 2);
        System.out.println("Area of circle = " + area);

        return area;
    }
}