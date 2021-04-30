import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.lang.*;

public class GameField extends JPanel implements ActionListener{
    private final int SIZE = 420;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 500;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private int k = 1;
    private double time = 0.00;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean esc = false;
    private boolean inGame = true;
    private final JFrame frame;
    private Font f = new Font("Consolas", Font.ITALIC,20);

    public GameField(JFrame frame){
        this.frame = frame;
        frame.setBackground(Color.white);
        loadImages();
        //downloading images
        initGame();
        //coordinates snake and start timer
        frame.addKeyListener(new FieldKeyListener());
        //using keyboard
        frame.setFocusable(true);
    }

    public void initGame(){
        dots = 3;//length snake
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }//coordinates snake
        timer = new Timer(250,this);
        timer.start();
        //start timer
        createApple();
        //method for coordinates apple
    }//coordinates snake and start timer

    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }//coordinates apple

    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("snake.png");
        dot = iid.getImage();
    }//downloading images

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot,x[i],y[i],this);
            }//drawing snake and apple
        } else{
            int timeInt = (int) time;
            g.setColor(Color.black);
            g.setFont(f);

            g.drawString("Game Over",(SIZE/4),SIZE/2 - 120);
            g.drawString("Snake length:  " + dots,(SIZE/4),SIZE/2 - 95);
            g.drawString("Timer:  " + timeInt + " s",(SIZE/4),SIZE/2 - 70);
            //add inscription Game Over, Snake length, Timer
            setLayout(null);

            JButton buttonNewGame = new JButton("New Game");
            buttonNewGame.setBounds((SIZE/4),SIZE/2 - 60,160,40);
            buttonNewGame.setContentAreaFilled(false);
            buttonNewGame.setFont(f);
            buttonNewGame.addActionListener(new Menu.startGame(frame));
            add(buttonNewGame);
            //create and add button New Game

            JButton bEGame = new JButton("Exit");
            bEGame.setBounds((SIZE/4),(SIZE/2) - 15,160,40);
            bEGame.setFont(f);
            bEGame.setContentAreaFilled(false);
            bEGame.addActionListener(new Menu.exitGame(frame));
            add(bEGame);
            //create and add button Exit
        }
    }

    public void move(){
        time = time + 0.25;
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        } if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }//movement

    public void checkApple(){
        for (int i = 0; i < dots; i++) {
            if(x[i] == appleX && y[i] == appleY) {
                dots++;
                createApple();
            }
        }//eat an apple
    }

    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
        if(x[0]>SIZE){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>SIZE){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
    }//going beyond the boundaries of the playing field

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();
        }
        frame.repaint();
    }//listener

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }//move left
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }//move right
            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }//upward movement
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }//downward movement
            if(key == KeyEvent.VK_ESCAPE && !esc) {
                if(k%2==0){
                    timer.start();
                }
                else{
                    timer.stop();
                }
                k = k + 1;
            }//pause
            }
        }
    }