import java.util.ArrayList;
import java.util.LinkedList;

public class Calculator {
    String mathExpression;
    private LinkedList<String> symbols = new LinkedList<>();
    private LinkedList<Double> numbers = new LinkedList<>();



    public Calculator(String mathExpression) {
        this.mathExpression = mathExpression;
    }


    public double printResult() throws Exception {
        ArrayList<String> list = ParserString.parseToList(this.mathExpression);

        for (int i = 0; i < list.size(); i++) {
            if (isDigit(list.get(i))) {
                numbers.add(Double.parseDouble(list.get(i)));
                if (i == list.size() - 1) {
                    addRemove();
                }
            } else if (symbols.isEmpty()) {
                symbols.add(list.get(i));
            } else {
                equalsSymbol(list.get(i));
            }
        }
        if(!symbols.isEmpty()){
            addRemove();
        }

        return numbers.getLast();

    }

    private void addRemove() throws Exception {
        Double rightOperand = new Double(numbers.getLast());
        numbers.removeLast();
        Double leftOperand = new Double(numbers.getLast());
        numbers.removeLast();
        String operand = new String(symbols.getLast());
        symbols.removeLast();

        numbers.add(calculateResult(leftOperand, rightOperand, operand));
    }

    private void equalsSymbol(String lastSymbol) throws Exception {

        switch (lastSymbol) {
            case "(":
                symbols.add(lastSymbol);
                break;
            /**/
            case "+":
                function(lastSymbol);
                break;
            /**/
            case "-":
                function(lastSymbol);
                break;
            case "*":
                function(lastSymbol);
                break;
            case "/":
                function(lastSymbol);
                break;
            /**/
            case ")":
                if (prioritetSymbol(lastSymbol) < prioritetSymbol(symbols.getLast())) {
                    addRemove();
                    equalsSymbol(lastSymbol);
                } else if (symbols.getLast().equals("(")) {
                    symbols.removeLast();
                }
                break;
            default:
                throw new Exception();
        }

    }

    private void function(String lastSymbol) throws Exception {
        if (prioritetSymbol(symbols.getLast()) < prioritetSymbol(lastSymbol)) {
            symbols.add(lastSymbol);
        } else if (prioritetSymbol(lastSymbol) == prioritetSymbol(symbols.getLast())) {
            addRemove();
            symbols.add(lastSymbol);
        } else if(prioritetSymbol(lastSymbol) == -1){
            addRemove();
            symbols.add(lastSymbol);
        } else {
            addRemove();
            symbols.add(lastSymbol);
        }
    }

    public boolean isDigit(String str) {

        char[] ch = str.toCharArray();
        if (str == null || str.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i <= ch.length - 1; i++) {
                int code = ch[i];
                if (code == 46 || code > 47 && code < 58) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private int prioritetSymbol(String symbol) {
        int prioritet = 0;
        switch (symbol) {
            case "+":
                prioritet = 1;
                break;
            case "-":
                prioritet = 1;
                break;
            case "*":
                prioritet = 2;
                break;
            case "/":
                prioritet = 2;
                break;
            default:
                prioritet = -1;
        }

        return prioritet;
    }

    private double calculateResult(double leftOperand, double rightOperand, String operand) throws Exception {
        double result = 0;
        switch (operand) {
            case "+":
                result = leftOperand + rightOperand;
                break;
            case "-":
                result = leftOperand - rightOperand;
                break;
            case "*":
                result = leftOperand * rightOperand;
                break;
            case "/":
                try{
                    result = leftOperand / rightOperand;
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                return result;
        }
        return result;
    }


}
