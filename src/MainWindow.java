import javax.swing.*;
import java.awt.Dimension;

public class MainWindow extends JFrame {
    public MainWindow(boolean keys){
        JFrame frame = new JFrame("Змейка");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(440, 460));
        frame.setLocation(700, 200);
        if(keys) {
            frame.add(new Menu(frame));
        }
        else{
            frame.add(new GameField(frame));
        }
        frame.pack();
        frame.setVisible(true);
    }//create a new window and adding items

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new MainWindow(true);
    }//call the first window
}