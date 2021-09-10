import org.junit.Test;

/**
 * @author chenfh
 * @date 2021-08-03 09:28
 */
public class JUnitTest {
    public int add(int a, int b) {
        return a + b;
    }

    @Test
    public void test1() {
        assert(add(1,1) == 2);
    }

    @Test
    public void test2() {
        assert(add(1,2) == 3);
    }
}
