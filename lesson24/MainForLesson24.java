
public class MainForLesson24 {
    public static void main(String[] args){
        Printer printer = new FilePrinter();
        Printer printer2 = new FilePrinter();
        TestClass testClass = new TestClass();

        ExtendedTestAutomationRunner extendedTestAutomationRunner = new ExtendedTestAutomationRunner();
        extendedTestAutomationRunner.setTestClass(testClass.getClass(), printer, printer2);
        extendedTestAutomationRunner.run();
    }
}
