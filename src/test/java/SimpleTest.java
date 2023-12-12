import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void oneTest() {
        int a = 1 + 2;
        Assert.assertEquals(a, 3);
    }

}
