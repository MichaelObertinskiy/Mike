import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {


        CalculateInterface calculateInterface = new CalculateInterface("Calculator");
        calculateInterface.setVisible(true);
        calculateInterface.setSize(400, 180);
        calculateInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
