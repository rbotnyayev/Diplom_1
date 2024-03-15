import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {
    Bun bun;

    @Before
    public void setUp(){
        bun = new Bun("testBun", 42.5f);
    }

    @Test
    public void testBunName(){
        assertEquals("testBun", bun.getName());
    }

    @Test
    public void testBunPrice(){
        assertEquals(42.5f, bun.getPrice(), 0.0);
    }
}
