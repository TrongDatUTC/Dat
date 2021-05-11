package Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class puzzle extends JFrame implements KeyListener, ActionListener {
    Color colorYes = Color.black; // Màu nền của ô trống đã ĐÚNG vị trí.
    Color colorNo = Color.yellow; // Màu nền của ô trống đã SAI vị trí.
    Color colorNumber = Color.green; // Màu của chữ số.
    Color colorBox = Color.LIGHT_GRAY; // Màu của ô trống.
    int maxSize = 1001;
    int indexI, indexJ; // tọa độ của ô trống.
    int n; // lưu kích thước của cạnh và hàng trong mảng.
    private Container cn;
    private JPanel pn;
    private JPanel pn1;
    private JButton b[][] = new JButton[maxSize][maxSize]; // một mảng hai chiều là các button.
    JButton size;

    public puzzle(String s, String SIZE) {
        super(s);
        cn = this.getContentPane();
        size = new JButton(SIZE);
        n = Integer.parseInt(SIZE);
        pn = new JPanel();
        pn.setLayout(new GridLayout(n, n));
        pn1 = new JPanel();
        JButton bNewGame = new JButton("New Game");
        JButton bExit = new JButton("Exit");
        bNewGame.addActionListener(this);
        bExit.addActionListener(this);
        pn1.add(bNewGame);
        pn1.add(bExit);

        // khởi tạo ma trân mặc định.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                b[i][j] = new JButton(String.valueOf(n * (i - 1) + j));
                b[i][j].addKeyListener(this);
                b[i][j].setForeground(colorNumber);
                b[i][j].setFont(new Font("Arial", Font.BOLD, 72));
                pn.add(b[i][j]);
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
        // Gọi hàm cập nhật màu nền của các ô trống
        updateColor();
        cn.add(pn);
        cn.add(pn1, BorderLayout.SOUTH);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        indexI = n;
        indexJ = n;
    }


    // Hàm cập nhập màu.
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

    // Hàm kiểm tra hoàn thành màn chơi đó.
    public void checkWin() {
        if (b[n][n].getText() == "") {
            b[n][n].setText(String.valueOf(n * n));
            boolean kt = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (Integer.parseInt(b[i][j].getText()) != n * (i - 1) + j) kt = false;
                }
            }
            if (kt) {
                this.dispose(); // Đóng cửa số màn hình hiển tại.
                // Qua level mới
                new puzzle("CodeLearn - Game Sắp Xếp Số - Level: " + String.valueOf(Integer.parseInt(size.getText()) - 1), String.valueOf(Integer.parseInt(size.getText()) + 1));
            } else {
                b[n][n].setText(String.valueOf(""));
            }
        }
    }

    // Xử lý khi gõ phím
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0); // thoát chương trình
        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // khi bấm phím xuống: Hoán đổi vị trị của ôn trống với ô phím trên nó.
            if (indexI > 1) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI - 1][indexJ].getText());
                b[indexI - 1][indexJ].setText(s);
                indexI--;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) { //khi bấm phím lên: Hoán đổi vị trị của ôn trống với ô phím dưới nó.
            if (indexI < n) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI + 1][indexJ].getText());
                b[indexI + 1][indexJ].setText(s);
                indexI++;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {//khi bấm phím sang phải: Hoán đổi vị trị của ôn trống với ô bên trái nó.
            if (indexJ > 1) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI][indexJ - 1].getText());
                b[indexI][indexJ - 1].setText(s);
                indexJ--;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { //khi bấm phím sang trái: Hoán đổi vị trị của ôn trống với ô bên phải nó.
            if (indexJ < n) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI][indexJ + 1].getText());
                b[indexI][indexJ + 1].setText(s);
                indexJ++;
            }
        }
        checkWin();
    }

    public void keyReleased(KeyEvent e) {
        updateColor();
    }

    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        new puzzle("CodeLearn - Game Sắp Xếp Số - Level: 1", "3");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "New Game") {
            new puzzle("CodeLearn - Game Sắp Xếp Số - Level: 1", "3");
            this.dispose();
        }
        else
        if (e.getActionCommand() == "Exit") {
            System.exit(0);;
        }
    }
}