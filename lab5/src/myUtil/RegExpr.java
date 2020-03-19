package myUtil;

import java.util.HashMap;
import java.util.Map;

public class RegExpr {
    public static Map<String, String> regExpressions;

    static {
        regExpressions = new HashMap<>();
        regExpressions.put("Натуральное число", "[1-9]\\d*");
        regExpressions.put("Целое число", "-?[1-9]\\d*");
        regExpressions.put("Число с плавающей запятой", "");
        regExpressions.put("Дата", "");
        regExpressions.put("Время", "");
        regExpressions.put("e-mail", "");
    }
}
