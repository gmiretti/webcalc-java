package ar.com.ascentio.calculator;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Calculator {

	public static Double eval(String expression) {
		Map<String, Double> variables = new HashMap<>();
        variables.put("pi", Math.PI);

        
        CalculatorLexer lexer = new CalculatorLexer(new ANTLRInputStream(expression));
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));
        EvalVisitor visitor = new EvalVisitor(variables);
        Double answer = visitor.visitEval(parser.eval());
        return answer;
	}
}
