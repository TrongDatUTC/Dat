import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Map extends JPanel implements KeyListener{
    Color colorYes = Color.black; 
    Color colorNo = Color.yellow; 
    Color colorNumber = Color.green; 
    Color colorBox = Color.LIGHT_GRAY; 
    int maxSize = 1001;
    int indexI, indexJ; 
    private int n; 
    private JButton b[][] = new JButton[maxSize][maxSize]; 
    private IMap listener;
    public Map(int size) {
        super();
        this.n = size+2;
        this.setLayout(new GridLayout(this.n, this.n));
        Init();
    }

    private void Init() {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                b[i][j] = new JButton(String.valueOf(n * (i - 1) + j));
                b[i][j].addKeyListener(this);
                b[i][j].setForeground(colorNumber);
                b[i][j].setFont(new Font("Arial", Font.BOLD, 72));
                this.add(b[i][j]);
            }
        }
        int i1, j1, i2, j2;
        for (int k = 1; k <= 2 * n * n; k++) {
            do {
                i1 = (int) (Math.round((n - 1) * Math.random() + 1));
                ;
                j1 = (int) (Math.round((n - 1) * Math.random() + 1));
            } while (i1 == n && j1 == n);
            do {
                i2 = (int) (Math.round((n - 1) * Math.random() + 1));
                j2 = (int) (Math.round((n - 1) * Math.random() + 1));
            } while ((i2 == n && j2 == n) || (i2 == i1 && j2 == j1));
            String p = b[i1][j1].getText();
            b[i1][j1].setText(b[i2][j2].getText());
            b[i2][j2].setText(p);
        }
        b[n][n].setText("");
        b[n][n].setBackground(colorBox);

        updateColor();

        indexI = n;
        indexJ = n;
    }

    public void updateColor() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (b[i][j].getText() != "") {
                    if (Integer.parseInt(b[i][j].getText()) == n * (i - 1) + j) {
                        b[i][j].setBackground(colorYes);
                    } else {
                        b[i][j].setBackground(colorNo);
                    }
                } else {
                    b[i][j].setBackground(colorBox);
                }
            }
        }
    }


    public void checkWin() {
        if (b[n][n].getText() == "") {
            b[n][n].setText(String.valueOf(n * n));
            boolean kt = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (Integer.parseInt(b[i][j].getText()) != n * (i - 1) + j)
                        kt = false;
                }
            }
            if (kt) {
                listener.OnWin();
               
            } else {
                b[n][n].setText(String.valueOf(""));
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0); // 
        if (e.getKeyCode() == KeyEvent.VK_DOWN) { 
            if (indexI > 1) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI - 1][indexJ].getText());
                b[indexI - 1][indexJ].setText(s);
                indexI--;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) { 
            if (indexI < n) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI + 1][indexJ].getText());
                b[indexI + 1][indexJ].setText(s);
                indexI++;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (indexJ > 1) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI][indexJ - 1].getText());
                b[indexI][indexJ - 1].setText(s);
                indexJ--;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
            if (indexJ < n) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI][indexJ + 1].getText());
                b[indexI][indexJ + 1].setText(s);
                indexJ++;
            }
        }
        checkWin();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        updateColor();
    }
    public void AddListener(IMap listener){
        this.listener = listener;
    }
}
