import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

public class Menu extends JPanel{
    private final int SIZE = 420;
    private final JFrame frame;

    public Menu(JFrame frame){
        this.frame = frame;
        frame.setBackground(Color.white);
        Container container = frame.getContentPane();
        Font fa = new Font("Consolas", Font.ITALIC, 20);
        //add font Consolas

        JButton bSGame = new JButton("Start Game");
        bSGame.setBounds((SIZE/4),(SIZE/2) - 90,200,50);
        bSGame.setFont(fa);
        bSGame.setContentAreaFilled(false);
        bSGame.addActionListener(new startGame(frame));
        container.add(bSGame);
        //create and add button Start Game

        JButton bEGame = new JButton("Exit");
        bEGame.setBounds((SIZE/4),(SIZE/2) - 30,200,50);
        bEGame.setFont(fa);
        bEGame.setContentAreaFilled(false);
        bEGame.addActionListener(new exitGame(frame));
        container.add(bEGame);
        //create and add button Exit

        frame.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font f = new Font("Consolas", Font.ITALIC, 30);
        g.setColor(Color.black);
        g.setFont(f);
        g.drawString("Main Menu:", (SIZE/4), SIZE / 2 - 110);
    }//add inscription Main Menu

    static class startGame implements ActionListener {
        private final JFrame frame;
        startGame(JFrame frame){
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new MainWindow(false);
        }
    }//closes the window about clicking the button and create a new window
    static class exitGame implements ActionListener {
        private final JFrame frame;
        exitGame(JFrame frame){
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            System.exit(0);
        }
    }//closes the window about clicking the button Exit
}
