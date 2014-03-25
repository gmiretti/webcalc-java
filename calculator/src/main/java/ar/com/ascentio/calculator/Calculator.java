package ar.com.ascentio.calculator;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Calculator {

	public static Double eval(String statement) {
		Map<String, Double> variables = new HashMap<>();
        variables.put("pi", Math.PI);
        
        MyErrorListener errorListener = new MyErrorListener();
        CalculatorLexer lexer = new CalculatorLexer(null);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        lexer.setInputStream(new ANTLRInputStream(statement));
        
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        EvalVisitor visitor = new EvalVisitor(variables);
        Double answer = visitor.visitEval(parser.eval());
        return answer;
	}
}
