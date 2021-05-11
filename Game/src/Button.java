import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton implements ActionListener {
    private IButton listener;
    private String name;

    public Button(String name) {
        super(name);
        this.name = name;
         addActionListener(this);
    }

    public void addListener(IButton listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        listener.OnButtonPressed(name);
    }
}