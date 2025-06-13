import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClassForLesson26 {

    @Test
    public void checkArrayNotContainsNumberFour() {
        int[] array = {6, 1, 8, 8, 6, 8, 0, 6, 1, 7};
        assertThatThrownBy(() -> Lesson26ArrayCut.method1(array))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("В массиве не найдены четвёрки!");
    }

    @Test
    public void checkArrayContainsNumberFour() {
        int[] array = {6, 1, 8, 8, 4, 8, 0, 6, 1, 7};
        assertThat(Lesson26ArrayCut.method1(array)).isEqualTo(List.of(8, 0, 6, 1, 7));
    }

    @Test
    public void checkArrayContainsFewNumberFour() {
        int[] array = {6, 1, 8, 8, 4, 8, 0, 4, 1, 7};
        assertThat(Lesson26ArrayCut.method1(array)).isEqualTo(List.of(1, 7));
    }

    @Test
    public void checkArraysLastNumberIsFour() {
        int[] array = {6, 1, 8, 8, 4, 8, 0, 4, 1, 4};
        assertThat(Lesson26ArrayCut.method1(array)).isEqualTo(List.of());
    }

    @Test
    public void checkArrayHasOnlyNumberOne() {
        int[] array = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        assertFalse(Lesson26ArrayCut.method2(array));
    }

    @Test
    public void checkArrayHasOnlyNumberFour() {
        int[] array = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        assertFalse(Lesson26ArrayCut.method2(array));
    }

    @Test
    public void checkArrayHasNumbersFourAndOne() {
        int[] array = {4, 1, 4, 4, 1, 4, 4, 1, 4, 4};
        assertTrue(Lesson26ArrayCut.method2(array));
    }

    @Test
    public void checkArrayHasAnotherNumbers() {
        int[] array = {7, 1, 4, 4, 1, 4, 9, 1, 4, 4};
        assertFalse(Lesson26ArrayCut.method2(array));
    }
}