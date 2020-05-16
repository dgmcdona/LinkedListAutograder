import org.junit.*;
import static org.junit.Assert.*;

public class TestInfixPostfix{	
	
	@Test
	public void testInfixPostfix(){
		assertTrue(InfixPostfix.convert("x ^ y / (5 * z) + 10").equals("xy^5z*/10+"));
		assertTrue(InfixPostfix.convert("a*b^2*(a+b*2)").equals("ab2^*ab2*+*"));
	}
}
