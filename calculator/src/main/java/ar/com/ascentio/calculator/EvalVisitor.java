package ar.com.ascentio.calculator;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Map;

public class EvalVisitor extends CalculatorBaseVisitor<Double> {

    private final Map<String, Double> variables;

    public EvalVisitor(Map<String, Double> variables) {
        this.variables = variables;
    }

    @Override
    public Double visitMin_expr(@NotNull CalculatorParser.Min_exprContext ctx) {
        return this.visit(ctx.expr(0)) - this.visit(ctx.expr(1));
    }

    @Override
    public Double visitId_expr(@NotNull CalculatorParser.Id_exprContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Double value = variables.get(id);
        if (value == null) {
            throw new RuntimeException("unknown variable: " + id);
        }
        return value;
    }

    @Override
    public Double visitDiv_expr(@NotNull CalculatorParser.Div_exprContext ctx) {
        return this.visit(ctx.expr(0)) / this.visit(ctx.expr(1));
    }

    @Override
    public Double visitMult_expr(@NotNull CalculatorParser.Mult_exprContext ctx) {
        return this.visit(ctx.expr(0)) * this.visit(ctx.expr(1));
    }

    @Override
    public Double visitEval(@NotNull CalculatorParser.EvalContext ctx) {
        return super.visit(ctx.expr());
    }

    @Override
    public Double visitNum_expr(@NotNull CalculatorParser.Num_exprContext ctx) {
        return Double.valueOf(ctx.NUMBER().getText());
    }

    @Override
    public Double visitPar_expr(@NotNull CalculatorParser.Par_exprContext ctx) {
        return super.visit(ctx.expr());
    }

    @Override
    public Double visitAdd_expr(@NotNull CalculatorParser.Add_exprContext ctx) {
        return this.visit(ctx.expr(0)) + this.visit(ctx.expr(1));
    }
    
    @Override
    public Double visitUnaryminus_expr(@NotNull CalculatorParser.Unaryminus_exprContext ctx) {
    	return -1 * super.visit(ctx.expr());
    }
    
    @Override
    public Double visitUnaryplus_expr(@NotNull CalculatorParser.Unaryplus_exprContext ctx) {
    	return super.visit(ctx.expr());
    }
    
    @Override
    public Double visitLog_expr(@NotNull CalculatorParser.Log_exprContext ctx) {
    	return Math.log10(super.visit(ctx.expr()));
    }
}
