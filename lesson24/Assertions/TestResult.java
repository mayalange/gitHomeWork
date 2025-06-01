package Assertions;

public abstract class TestResult {
    private final boolean success;

    public TestResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}