import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ParserString {

    public static ArrayList<String> parseSymbol(String line, String separators){
        ArrayList<String> list = new ArrayList<>();

        Pattern del = Pattern.compile(separators);

        Matcher match = del.matcher(line);

        while(match.find()){
            list.add(match.group());

        }
        return list;
    }

    public static ArrayList<String> parseToList(String mathExpression){
        ArrayList<String> list = parseSymbol(mathExpression,"\\b?[0-9]*\\.?[0-9]+\\b||[+\\-\\*\\/\\(\\)]");
        ArrayList<String> listSymbol = parseSymbol(mathExpression,"[+\\-\\*\\/\\(\\)]");
        ArrayList<String> listFin = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < list.size()-1;i++){
            if(list.get(i).isEmpty()){
                listFin.add(listSymbol.get(count));
                count++;
            } else {
                listFin.add(list.get(i));
            }
        }
        return listFin;
    }

}
