import Assertions.Assertions;

public class TestClass {

    @Test
    public void contains() {
        Assertions.contains(
                new Integer[]{5, 9, 1, 2, 3, 10},
                new Integer[]{1, 2, 3}
        );
    }

    @Test
    public void contains2() {
        Assertions.contains(
                new Integer[]{5, 9, 1, 2, 3, 10},
                new Integer[]{1, 2, 7}
        );
    }
}