import Assertions.AssertException;
import Assertions.AssertSuccess;

import java.lang.reflect.Method;
import java.util.*;

public class ExtendedTestAutomationRunner implements Runner {
    private final Set<Printer> printers = new HashSet<>();
    private Class<?> testClass;

    public void setTestClass(Class<?> testClass, Printer... printers) {
        this.printers.addAll(Arrays.asList(printers));
        this.testClass = testClass;
    }

    @Override
    public void run() {
        if (testClass == null) {
            throw new IllegalStateException("Test class not set");
        }

        List<String> testResults = new ArrayList<>();
        executeTests(testResults);
        distributeResults(testResults);
    }

    private void executeTests(List<String> testResults) {
        try {
            Object testInstance = testClass.getDeclaredConstructor().newInstance();

            for (Method method : testClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    executeSingleTest(testInstance, method, testResults);
                }
            }
        } catch (Exception e) {
            testResults.add("TEST SETUP FAILED: " + e.toString());
        }
    }

    private void executeSingleTest(Object testInstance, Method method, List<String> results) {
        String testName = method.getName();
        try {
            method.invoke(testInstance);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof AssertException) {
                results.add("❌ " + testName + " - FAILED: " + cause.getMessage());
            } else if (cause instanceof AssertSuccess) {
                results.add("✅ " + testName + " - PASSED: " + cause.getMessage());
            } else {
                results.add("⚠️ " + testName + " - ERROR: " +
                        (cause != null ? cause.toString() : e.toString()));
            }
        }
    }

    private void distributeResults(List<String> results) {
        printers.forEach(printer -> {
            results.forEach(result -> {
                try {
                    printer.writeIntoFile(result);
                } catch (Exception e) {
                    System.err.println("Failed to print result: " + e.getMessage());
                }
            });
        });
    }
}