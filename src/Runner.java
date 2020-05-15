import java.util.Iterator;

public class Runner{
        public static void main(String[] args){
                String infix = "a+b*(c^d-e)^(f+g*h)-i";
                System.out.println((InfixPostfix.convert(infix)));
        }
}
