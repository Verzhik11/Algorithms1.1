import exceptions.IncorrectIndexException;
import exceptions.NullItemException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class IntegerListImplTest {
    IntegerListImpl out = new IntegerListImpl(3);
    @BeforeEach
    void setUp() {
        out.add(1);
        out.add(2);
        out.add(3);
    }
    @AfterEach
    void clearList() {
        out.clear();
    }


    @Test
    void addTest() {
        out.resize(out.size());
        Assertions.assertEquals(out.add(5), 5);
    }
    static Stream<Arguments> addParams() {
        return Stream.of(
                Arguments.of(1, 9),
                Arguments.of(3, 11));
    }

    @ParameterizedTest
    @MethodSource ("addParams")
    void testAdd(int index, Integer item) {
        out.resize(out.size());
        Assertions.assertEquals(out.add(index, item), item);
        Assertions.assertEquals(out.size(), 4);
    }

    static Stream<Arguments> setParams() {
        return Stream.of(
                Arguments.of(1, 9),
                Arguments.of(2, 11));
    }
    @ParameterizedTest
    @MethodSource ("setParams")
    void setTest(int index, Integer item) {
        Assertions.assertEquals(out.set(index, item), item);
    }


    @Test
    void validateItemTest() {
        Assertions.assertThrows(NullItemException.class, () -> out.set(1, null));

    }

    @Test
    void removeTest() {
        Assertions.assertEquals(out.remove(2), 3);
    }
    @Test
    void ifRemoveObjectIsAbsend() {
        Assertions.assertThrows(RuntimeException.class, () -> out.remove(4));

    }

    @Test
    void testRemoveIndex() {
        Assertions.assertEquals(out.remove(2), 3);
        Assertions.assertEquals(out.size(), 2);
    }
    @Test
    void validateIndexTest() {
        Assertions.assertThrows(IncorrectIndexException.class, () -> out.validateIndex(4));

    }
    static Stream<Arguments> containsParams() {
        return Stream.of(
                Arguments.of(9),
                Arguments.of(11));
    }

    @ParameterizedTest
    @MethodSource ("containsParams")
    void containsTest(Integer item) {
        Assertions.assertFalse(out.contains(item));

    }

    @Test
    void indexOfTest() {
        Assertions.assertEquals(out.indexOf(1), 0);
    }
    static Stream<Arguments> lastIndexParams() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(3, 2),
                Arguments.of(9, -1));
    }


    @ParameterizedTest
    @MethodSource("lastIndexParams")
    void lastIndexOfTest(Integer item, int index) {
        Assertions.assertEquals(index, out.lastIndexOf(item));

    }

    @Test
    void getTest() {
        Assertions.assertEquals(out.get(2), 3);
    }
    @Test
    void testEquals() {
        IntegerListImpl test = new IntegerListImpl(3);
        test.add(1);
        test.add(2);
        test.add(3);
        Assertions.assertTrue(out.equals(test));
    }

    @Test
    void equalsIsNullTest() {
        Assertions.assertThrows(NullItemException.class, () -> out.equals(null));

    }


    @Test
    void sizeTest() {
        Assertions.assertEquals(out.size(), 3);

    }

    @Test
    void isEmptyTest() {
        Assertions.assertFalse(out.isEmpty());

    }

    @Test
    void toArrayTest() {
        Integer [] test = {1, 2, 3};
        Assertions.assertArrayEquals(test, out.toArray());
    }
}