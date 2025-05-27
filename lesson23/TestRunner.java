import java.lang.module.ResolutionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TestRunner {

    public static void start(Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object testInstance = aClass.getDeclaredConstructor().newInstance();

        Method beforeSuite = null;
        Method afterSuite = null;
        List<Method> testsArray = new ArrayList<>();

        for (Method method : aClass.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null && beforeSuite == null) {
                beforeSuite = method;
            } else if (method.getAnnotation(AfterSuite.class) != null && afterSuite == null) {
                afterSuite = method;
            } else if (method.getAnnotation(Test.class) != null) {
                testsArray.add(method);
            } else if (method.getAnnotation(BeforeSuite.class) != null && beforeSuite != null) {
                throw new ResolutionException("вызвано несколько @BeforeSuite");
            } else if (method.getAnnotation(AfterSuite.class) != null && afterSuite != null) {
                throw new ResolutionException("вызвано несколько @AfterSuite");
            }
        }

        if (beforeSuite != null) {
            beforeSuite.invoke(testInstance);
        }

        if (!testsArray.isEmpty()){
            testsArray.sort(Comparator.comparingInt(order -> order.getAnnotation(Test.class).order()));
            for (Method method : testsArray) {
                method.invoke(testInstance);
            }
        }

        if (afterSuite != null) {
            afterSuite.invoke(testInstance);
        }
    }
}
