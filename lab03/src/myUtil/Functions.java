package myUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions{
    public static String format(String str){
        str = str.replace('_', ' ');
        Matcher m = Pattern.compile("(\\b)(\\w)").matcher(str);
        return m.replaceAll(r -> r.group(0).toUpperCase());
    }
}
