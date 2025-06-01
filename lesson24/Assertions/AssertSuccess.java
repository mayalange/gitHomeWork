package Assertions;

public class AssertSuccess extends RuntimeException{
    private final TestResult assertResult;

    public AssertSuccess(TestResult assertResult) {
        this.assertResult = assertResult;
    }

    public TestResult getAssertResult() {
        return assertResult;
    }

    @Override
    public String getMessage() {
        return assertResult.toString();
    }
}