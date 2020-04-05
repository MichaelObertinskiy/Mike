import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculateInterface extends JFrame {

    //    JFrame frame = new JFrame("Калькулятор");
    JPanel panelExpression = new JPanel();
    JPanel yourPanel = new JPanel();
    JPanel panel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JTextField text = new JTextField(20);
    JLabel textResult = new JLabel();
    JLabel expression = new JLabel("Your expression");

    BorderLayout borderLayout = new BorderLayout();
    GridLayout gridLayout = new GridLayout(1, 4);

    JButton reset = new JButton("C");
    JButton resultButton = new JButton("Result");
    JButton undo = new JButton("Undo");
    JButton redo = new JButton("Redo");
    EventHandler handler = new EventHandler();

    public CalculateInterface(String s) {
        super(s);
        setLayout(new FlowLayout());
        panelExpression.setSize(120, 20);
        buttonPanel.setLayout(gridLayout);

        yourPanel.setSize(100, 30);
        buttonPanel.setSize(120, 10);
        panelExpression.add("East", expression);
        panelExpression.add("West", text);
        yourPanel.add("Center", textResult);

        buttonPanel.add(reset);
        buttonPanel.add(resultButton);
        buttonPanel.add(undo);
        buttonPanel.add(redo);


        panel.add("North", buttonPanel);
        panel.add("South", yourPanel);
        panel.add("Centre", panelExpression);
        buttonPanel.setLayout(gridLayout);
//        panel.add("Center", borderLayout);

        setContentPane(panel);

        resultButton.addActionListener(handler);
        reset.addActionListener(handler);
        undo.addActionListener(handler);
        redo.addActionListener(handler);

    }


    class EventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == resultButton) {
                String expression = text.getText();
                Calculator calc = new Calculator(expression);
                String result = null;
                try {
                    result = String.valueOf(calc.printResult());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                textResult.setText("Yor result: " + result);
            }

            if (e.getSource() == reset) {
                textResult.setText(null);
                text.setText(null);
            }

            if (e.getSource() == undo) {
                UndoRedo undo = new UndoRedo(text.getText());
                text.setText(undo.undo());
            }

            if(e.getSource() == redo){
                UndoRedo redo = new UndoRedo(text.getText());
                text.setText(redo.redo());
            }
        }
    }
}



