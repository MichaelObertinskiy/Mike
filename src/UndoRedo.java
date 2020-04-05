import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;

public class UndoRedo {
 private String mathExpression;

    public UndoRedo(String mathExpression) {
        this.mathExpression = mathExpression;
    }

    public String undo(){
        ArrayList<String> list = ParserString.parseToList(this.mathExpression);
        list.remove(list.size()-1);
        StringBuffer buf = new StringBuffer();
        for(String str: list){
            buf.append(str);
        }
        return buf.toString();
    }

    public String redo(){
        LinkedList<String> list = new LinkedList<>();
        list.addAll(ParserString.parseToList(this.mathExpression));
        String s3, s2, s1;
        if(isDigit(list.getLast())){
           s3 = list.getLast();
           list.removeLast();
           s2 = list.getLast();
           list.addLast(s3);
           list.addLast(s2);
           list.addLast(s3);
        } else {
            s3 = list.getLast();
            list.removeLast();
            s2 = list.getLast();
            list.addLast(s3);
            list.addLast(s2);
        }
        StringBuffer buf = new StringBuffer();
        while(!list.isEmpty()){
            buf.append(list.getFirst());
            list.removeFirst();
        }
        return buf.toString();
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
}
