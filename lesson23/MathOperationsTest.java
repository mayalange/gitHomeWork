public class MathOperationsTest {

    @BeforeSuite
    public void beforeTest() {
        System.out.println("before test");
    }

    @Test(order = 2)
    public void test2() {
        System.out.println("Тест сложения: 1 + 4 = " + MathOperations.sum(1, 4));

    }

    @Test(order = 1)
    public void test1() {
        System.out.println("Тест вычитания: 71 - 4 = " + MathOperations.subtract(71, 4));
    }

    @Test(order = 3)
    public void test3() {
        System.out.println("Тест деления: 10 // 4 = " + MathOperations.divide(10, 4));
    }

    @AfterSuite
    public void afterTest() {
        System.out.println("after test");
    }
}