package aniela.remanent.pozycje.helperyZapytanDoBazy.expression;

import java.util.HashMap;
import java.util.Map;
import aniela.remanent.pozycje.helperyZapytanDoBazy.Conditions;

public final class ExpressionStrategy {

    private final Map<Integer, ExpressionContract> expressionsMapper = new HashMap<>();

    public ExpressionStrategy() {
        expressionsMapper.put(Conditions.EMPTY.value, new ExpressionEmpty());
        expressionsMapper.put(Conditions.EQUALS.value, new ExpressionEquals());
        expressionsMapper.put(Conditions.MORE_THAN.value, new ExpressionMoreThan());
        expressionsMapper.put(Conditions.LESS_THAN.value, new ExpressionLessThan());
    }

    public ExpressionContract getExpression(int radioButtonValue) {
        return expressionsMapper.get(radioButtonValue);
    }

    public ExpressionContract getExpression(String value) {
        if (!value.equalsIgnoreCase("false")) {
            return new ExpressionLike();
        }
        return expressionsMapper.get(Conditions.EMPTY.value);
    }
}
