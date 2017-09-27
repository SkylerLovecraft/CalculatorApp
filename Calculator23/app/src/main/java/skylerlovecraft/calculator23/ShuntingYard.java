
/*
Adapted from: https://rosettacode.org/wiki/Parsing/Shunting-yard_algorithm
 */
/*
while there are tokens to be read:
	read a token.
	if the token is a number, then push it to the output queue.
	if the token is an operator, then:

		while there is an operator at the top of the operator stack with
		greater than or equal to precedence and the operator is left associative:
			pop operators from the operator stack, onto the output queue.
		push the read operator onto the operator stack.
    if there are no more tokens to read:
        while there are still operator tokens on the stack:
		/* if the operator token on the top of the stack is a bracket, then
		there are mismatched parentheses. */
      /* pop the operator onto the output queue.
        exit. */

package skylerlovecraft.calculator23;
import java.util.Stack;

public class ShuntingYard {

    public static void main(String[] args) {
        String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
        System.out.printf("infix:   %s%n", infix);
        System.out.printf("postfix: %s%n", infixToPostfix(infix));
    }

    static String infixToPostfix(String infix) {
        /* To find out the precedence, we take the index of the
           token in the ops string and divide by 2 (rounding down).
           This will give us: 0, 0, 1, 1, 2 */
        final String ops = "-+/*";
        System.out.println("in the yard algorithm");

        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();

        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            //will set ndx to the index of the operator in the string "-+/*", which MAY match
            //the char c. If it does not find it, indexOf returns -1, meaning the token is a numer. 
            int ndx = ops.indexOf(c);

            // check for operator, because indexOf returns -1 if it did not find an operator in the char
            // we only need to push the ndx to the operator stack if it isn't -1.
            if (ndx != -1) {
                if (s.isEmpty())
                    s.push(ndx);

                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = ndx / 2;
                        if (prec2 > prec1 || prec2 == prec1)
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(ndx);
                }
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }
}