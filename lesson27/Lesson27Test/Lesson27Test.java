import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Lesson27Test {

    @Test
    public void checkSum(){
        Assertions.assertEquals(7, Lesson27Methods.sum(5,2));
    }

    @Test
    public void checkDivide(){
        Assertions.assertEquals(3, Lesson27Methods.divide(6,2));
    }

    @Test
    public void checkDivideNegative(){
        assertThatThrownBy(() -> Lesson27Methods.divide(5,0))
                .isInstanceOf(MatchException.class)
                .hasMessage("Нельзя делить на ноль");
    }

    @Test
    public void checkSubtract(){
        Assertions.assertEquals(3, Lesson27Methods.subtract(5,2));
    }

    @Test
    public void checkMultiply(){
        Assertions.assertEquals(25, Lesson27Methods.multiply(5,5));
    }

    @Test
    public void checkSquare(){
        Assertions.assertEquals(25, Lesson27Methods.square(5));
    }
}