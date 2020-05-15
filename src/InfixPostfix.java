import java.lang.StringBuilder;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

public class InfixPostfix{

        /* Map operators to precedence */
    public static int prec(char ch){
        switch (ch){
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    public static String convert(String _infix){
        /* Create stack for conversion algorithm */
        MyStack<Character> stack = new MyStack<>();

        /* Remove all whitespace */
        String stripped = _infix.replaceAll("\\s+", "");

        /* Create stringbuilder object for building postfix string */
        StringBuilder result = new StringBuilder();
        StringBuilder infix = new StringBuilder(stripped);

        /* Convert */
        for(int i = 0; i < stripped.length(); i++){
            char current = stripped.charAt(i);
            if(Character.isLetterOrDigit(current)){
                result.append(current);
            } 
            else if (current == '('){
                stack.push(current);
            }
            else if (current == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    result.append(stack.pop());
                }

                if(!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression";
                else
                    stack.pop();
            }
            else{
                while(!stack.isEmpty() && (prec(current) <= prec(stack.peek()))){
                    if(stack.peek() == '(')
                        return "Invalid Expression";
                    result.append(stack.pop());
                }
                stack.push(current);
            }
        }
        while(!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            result.append(stack.pop());
        }
        return result.toString();
    }
}
