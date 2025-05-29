package Assertions;

public class AssertException extends RuntimeException{
    private final TestResult assertResult;

    public AssertException(TestResult assertResult) {
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