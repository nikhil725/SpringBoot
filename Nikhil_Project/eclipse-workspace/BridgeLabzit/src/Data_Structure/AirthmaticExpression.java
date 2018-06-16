package Data_Structure;
import java.util.*;
public class AirthmaticExpression
{
	public static void main(String[] args)
	{
		System.out.println(AirthmaticExpression.evaluate(" ( 5 + 6 ) ∗ ( 7 + 8 ) / ( 4 + 3 ) ( 5 + 6 ) ∗ ( 7 + 8 ) / ( 4 + 3 ) "));
		System.out.println(AirthmaticExpression.evaluate(" ( 8 + 6 ) "));
	}
	public static int evaluate(String expression)
	{
		char tokens[] = expression.toCharArray();
		Stack<Integer> values = new Stack<Integer>();
		Stack<Character> ops = new Stack<Character>();
		for (int i = 0; i < tokens.length; i++)
		{
			if (tokens[i] == ' ')// Current token is a whitespace, skip it
				continue;			
			if (tokens[i] >= '0' && tokens[i] <= '9')// Current token is a number, push it to stack for numbers
			{
				StringBuffer sb = new StringBuffer();//more than one digits in number
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sb.append(tokens[i++]);
				values.push(Integer.parseInt(sb.toString()));
			}
			else if (tokens[i] == '(')
				ops.push(tokens[i]);
			else if (tokens[i] == ')')//encountered, solve entire brace
		{
				while (ops.peek() != '(')
				values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
		}		
			else if (tokens[i] == '+' || tokens[i] == '-' ||
					tokens[i] == '*' || tokens[i] == '/')// Current token is an operator.
			{
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
				values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.push(tokens[i]);
			}
		}
		while (!ops.empty())
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));
		return values.pop();
	}
	public static boolean hasPrecedence(char op1, char op2)
	{
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}
	public static int applyOp(char op, int b, int a)
	{
		switch (op)
		{
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new
				UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}
	
}