import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void test1() {
        String text = "3 3\n" +
                "1 1 0\n" +
                "1 0 0\n" +
                "1 0 1";
        assertEquals(6, Main.test(text));
    }
    @Test
    public void test2() {
        String text = "5 4\n" +
                "0 1 1 0 0\n" +
                "1 1 1 0 1\n" +
                "1 1 0 0 1\n" +
                "0 0 0 1 0";
        assertEquals(9, Main.test(text));
    }
    @Test
    public void test3() {
        String text = "5 3\n" +
                "1 1 1 0 1\n" +
                "1 1 1 0 1\n" +
                "1 1 1 0 1";
        assertEquals(9, Main.test(text));
    }
    @Test
    public void test4() {
        String text = "3 3\n" +
                "1 0 1\n" +
                "1 0 1\n" +
                "1 0 0\n";
        assertEquals(3, Main.test(text));
    }
    @Test
    public void test5() {
        String text = "3 3\n" +
                "1 0 0\n" +
                "0 0 1\n" +
                "1 0 0\n";
        assertEquals(0, Main.test(text));
    }
    @Test
    public void test6() {
        String text = "3 3\n" +
                "1 1 1\n" +
                "1 1 1\n" +
                "1 1 1\n";
        assertEquals(9, Main.test(text));
    }
    @Test
    public void test7() {
        String text = "3 3\n" +
                "1 1 1\n" +
                "1 0 1\n" +
                "1 1 1\n";
        assertEquals(9, Main.test(text));
    }
    @Test
    public void test8() {
        String text = "3 3\n" +
                "0 0 0\n" +
                "0 0 0\n" +
                "0 0 0\n";
        assertEquals(0, Main.test(text));
    }
    @Test
    public void test9() {
        String text = "3 3\n" +
                "1 0 1\n" +
                "0 0 0\n" +
                "0 1 0\n";
        assertEquals(0, Main.test(text));
    }
    @Test
    public void test10() {
        String text = "3 5\n" +
                "1 0 1\n" +
                "0 1 1\n" +
                "1 0 1\n" +
                "0 0 0\n" +
                "0 1 0";
        assertEquals(9, Main.test(text));
    }
    @Test
    public void test11() {
        String text = "5 3\n" +
                "1 1 1 1 1\n" +
                "1 0 0 0 1\n" +
                "1 0 1 0 1";
        assertEquals(15, Main.test(text));
    }
    @Test
    public void test12() {
        String text = "5 3\n" +
                "1 1 0 1 1\n" +
                "1 1 0 0 1\n" +
                "0 0 0 0 1";
        assertEquals(4, Main.test(text));
    }
    @Test
    public void test13() {
        String text = "5 3\n" +
                "1 1 0 1 1\n" +
                "1 0 0 1 1\n" +
                "1 0 0 0 0";
        assertEquals(4, Main.test(text));
    }
    @Test
    public void test14() {
        String text = "5 3\n" +
                "1 1 0 1 1\n" +
                "1 1 0 0 1\n" +
                "0 0 0 1 1";
        assertEquals(4, Main.test(text));
    }
    @Test
    public void test15() {
        String text = "5 3\n" +
                "1 1 0 1 1\n" +
                "1 1 0 1 1\n" +
                "0 0 0 1 1";
        assertEquals(6, Main.test(text));
    }
    @Test
    public void test16() {
        String text = "5 3\n" +
                "0 1 0 0 1\n" +
                "1 0 0 1 0\n" +
                "0 0 1 0 0";
        assertEquals(9, Main.test(text));
    }
}
