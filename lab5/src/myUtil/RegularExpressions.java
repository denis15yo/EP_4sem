package myUtil;

import java.util.HashMap;
import java.util.Map;

public class RegularExpressions {
    public static Map<String, String> mapOfRegularExpressions;

    static {
        mapOfRegularExpressions = new HashMap<>();
        mapOfRegularExpressions.put("Натуральное число", "[1-9]\\d*");
        mapOfRegularExpressions.put("Целое число", "(-?[1-9]\\d*|0)");
        mapOfRegularExpressions.put("Число с плавающей запятой", "(-?[1-9]\\d*|0)?(\\.\\d*(e(-?[1-9]\\d*|0))?|(e(-?[1-9]\\d*|0)))");
//           1 - NO
//           .1 - YES
//           1. - YES
//          .1. - NO
//          1e - NO
//          1e1 - YES
//          1.5e-2 - YES
        mapOfRegularExpressions.put("Дата", "((0[1-9]|2[1-8])\\.(0[1-9]|1[0-2])|(29|30)\\.(0[13-9]|1[0-2])|31\\.(0[13578]|1[02]))\\.\\d{1,4}");
//        31.01.2020 - YES
//        32.01.2020 - NO
//        28.02.2020 - YES
//        (29-31).02.2020 - NO
//        30.04.2020 - YES
//        31.04.2020 - NO
        mapOfRegularExpressions.put("Время", "([01]\\d|2[0-3])[-\\.:][0-5]\\d");
        mapOfRegularExpressions.put("e-mail", "\\w[\\w\\._]*@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}");
    }
}
