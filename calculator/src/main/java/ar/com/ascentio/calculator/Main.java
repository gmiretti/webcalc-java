package ar.com.ascentio.calculator;

public class Main {

    public static void main(String[] args) throws Exception {

    	String expression = "(pi - 3) * 2";
    	Double answer = Calculator.eval(expression);
        System.out.println(expression + " = " + answer);
    }
}
