import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.*;

public class TestMyStack{
    private MyStack<Integer> stack;

    @Before
    public void setUp(){
        stack = new MyStack<>();
    }    

    @Test
    public void testPush(){
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
    }

    @Test
    public void testPop(){
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4,(int)stack.pop());
        assertEquals(3,(int)stack.pop());
        assertEquals(2,(int)stack.pop());
        assertEquals(1,(int)stack.pop());
    }

    @Test
    public void testIsEmpty(){
        assertTrue(stack.isEmpty()); 
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}
