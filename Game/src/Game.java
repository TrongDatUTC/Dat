import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Game extends JFrame implements IMenu, IMap {
    private int level;
    private Container cn;
    private Map map;
    private Menu menu;

    public Game(String name) {
        super(name);
    }

    public Game(int _level) {
        this("Game Sắp Xếp Số - Level: " + String.valueOf(_level));
        level = _level;
        cn = this.getContentPane();
        map = new Map(level);
        menu = new Menu();
        cn.add(map);
        cn.add(menu, BorderLayout.SOUTH);
        map.AddListener(this);
        menu.AddListener(this);git
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void OnCommand(ButtonEvent event) {
        // TODO Auto-generated method stub
        switch (event) {
            case END_GAME:
                System.exit(0);
                break;
            case NEW_GAME:
                new Game(level);
                this.dispose();
                break;
            default:
                break;
        }

    }

    @Override
    public void OnWin() {
        // TODO Auto-generated method stub
        this.dispose();
        new Game(level + 1);
    }

    public static void main(String[] args) {
        Game game = new Game(1);
    }
}

