package myUtil;

import java.util.HashMap;
import java.util.Map;

public class RegularExpressions {
    public static Map<String, String> mapOfRegularExpressions;

    static {
        mapOfRegularExpressions = new HashMap<>();
        mapOfRegularExpressions.put("Натуральное число", "[1-9]\\d*");
        mapOfRegularExpressions.put("Целое число", "-?[1-9]\\d*");
        mapOfRegularExpressions.put("Число с плавающей запятой", "-?[1-9]\\d*(\\.\\d+)?(e-?\\d+)?");
        mapOfRegularExpressions.put("Дата", "(0[1-9]|[12][0-9]|3[01])[- /\\.](0[1-9]|1[012])[- /\\.]\\d{1,4}");
        mapOfRegularExpressions.put("Время", "([01]\\d|2[0-3])[-\\.:][0-5]\\d");
        mapOfRegularExpressions.put("e-mail", "\\w[\\w\\._]*@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}");
    }
}
