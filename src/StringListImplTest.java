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

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
StringListImpl out = new StringListImpl(3);
    @BeforeEach
    void setUp() {
        out.add("one");
        out.add("two");
        out.add("three");
    }
    @AfterEach
    void clearList() {
        out.clear();
    }


    @Test
    void addTest() {
        out.resize(out.size());
        Assertions.assertEquals(out.add("five"), "five");
    }
    static Stream<Arguments> addParams() {
        return Stream.of(
                Arguments.of(1, "nine"),
                Arguments.of(3, "eleven"));
    }

    @ParameterizedTest
    @MethodSource ("addParams")
    void testAdd(int index, String item) {
        out.resize(out.size());
        Assertions.assertEquals(out.add(index, item), item);
        Assertions.assertEquals(out.size(), 4);
    }

    static Stream<Arguments> setParams() {
        return Stream.of(
                Arguments.of(1, "nine"),
                Arguments.of(2, "eleven"));
    }
    @ParameterizedTest
    @MethodSource ("setParams")
    void setTest(int index, String item) {
        Assertions.assertEquals(out.set(index, item), item);
    }


    @Test
    void validateItemTest() {
        Assertions.assertThrows(NullItemException.class, () -> out.set(1, null));

    }

    @Test
    void removeTest() {
        Assertions.assertEquals(out.remove("two"), "two");
    }
    @Test
    void ifRemoveObjectIsAbsend() {
        Assertions.assertThrows(RuntimeException.class, () -> out.remove("four"));

    }

    @Test
    void testRemoveIndex() {
        Assertions.assertEquals(out.remove(2), "three");
        Assertions.assertEquals(out.size(), 2);
    }
    @Test
    void validateIndexTest() {
        Assertions.assertThrows(IncorrectIndexException.class, () -> out.validateIndex(4));

    }
    static Stream<Arguments> containsParams() {
        return Stream.of(
                Arguments.of("nine"),
                Arguments.of("eleven"));
    }

    @ParameterizedTest
    @MethodSource ("containsParams")
    void containsTest(String item) {
        Assertions.assertFalse(out.contains(item));

    }

    @Test
    void indexOfTest() {
        Assertions.assertEquals(out.indexOf("one"), 0);
    }
    static Stream<Arguments> lastIndexParams() {
        return Stream.of(
                Arguments.of("one", 0),
                Arguments.of("three", 2),
                Arguments.of("nine", -1));
    }


    @ParameterizedTest
    @MethodSource("lastIndexParams")
    void lastIndexOfTest(String item, int index) {
        Assertions.assertEquals(index, out.lastIndexOf(item));

    }

    @Test
    void getTest() {
        Assertions.assertEquals(out.get(2), "three");
    }
    @Test
    void testEquals() {
        StringListImpl test = new StringListImpl(3);
        test.add("one");
        test.add("two");
        test.add("three");
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
        String [] test = {"one", "two", "three"};
        Assertions.assertArrayEquals(test, out.toArray());
    }

}