package by.bsu.myUtil;

import by.bsu.expressions.Expression;
import by.bsu.expressions.formulas.*;
import by.bsu.expressions.operands.CellReference;
import by.bsu.expressions.operands.MyDate;
import by.bsu.expressions.operands.Operand;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parser {
    public static MyDate parseToMyDate(String string) throws ParseException {
        SimpleDateFormat simpleDateFormat = MyDate.DATE_FORMAT;
        return new MyDate(simpleDateFormat.parse(string));
    }

    public static Expression parseToExpression(String string) throws ParseException {
        string = string.trim();
        if(string.length() == 0){
            throw new ParseException("Пустая строка.", 0);
        }

        if(string.charAt(0) == '='){
            return parseToFormula(string.substring(1));
        }

        return parseToMyDate(string);
    }

    public static Formula parseToFormula(String string) throws ParseException {
        char operation = '+';
        int indexOperation;
        for(indexOperation = 0; indexOperation < string.length(); ++indexOperation){
            operation = string.charAt(indexOperation);
            if(operation == '+' || operation == '-'){
                break;
            }
        }
        if(indexOperation < string.length()){
            try{
                return new OffsetFormula(parseToOperand(string.substring(0, indexOperation)),
                        operation, Integer.parseInt(string.substring(indexOperation + 1)));
            }
            catch (NumberFormatException numberFormatException){
                throw new ParseException("Некорректная OffsetFormula.", 0);
            }
        }
        return parseToExtremumFormula(string);
    }

    public static ExtremumFormula parseToExtremumFormula(String string) throws ParseException {
        ExtremumFormula extremumFormula;
        if(string.startsWith("МИН")){
            extremumFormula = new MinFormula();
            string = string.substring(3);
        } else if(string.startsWith("МАКС")){
            extremumFormula = new MaxFormula();
            string = string.substring(4);
        }
        else{
            throw new ParseException("Некорректная МИН/МАКС формула.", 0);
        }
        if(string.charAt(0) != '(' || string.charAt(string.length() - 1) != ')'){
            throw new ParseException("Некорректная МИН/МАКС формула.", 0);
        }
        string = string.substring(1, string.length() - 1);
        String[] stringOperands = string.split(",");
        for(String s : stringOperands){
            extremumFormula.addOperand(parseToOperand(s));
        }
        return extremumFormula;
    }

    public static Operand parseToOperand(String string) throws ParseException {
        string = string.trim();
        SimpleDateFormat simpleDateFormat = MyDate.DATE_FORMAT;
        try{
            return new MyDate(simpleDateFormat.parse(string));
        } catch (ParseException parseException){
            return parseToCellReference(string);
        }
    }

    public static CellReference parseToCellReference(String string) throws ParseException {
        if(string.matches("[a-zA-Z]+\\d+")){
            int firstDigitIndex;
            for(firstDigitIndex = 0; firstDigitIndex < string.length(); ++firstDigitIndex){
                if(Character.isDigit(string.charAt(firstDigitIndex))){
                    break;
                }
            }
            return new CellReference(Integer.parseInt(string.substring(firstDigitIndex)),
                    string.substring(0, firstDigitIndex));
        }
        else{
            throw new ParseException("Некорректная ссылка на ячейку.", 0);
        }
    }
}
