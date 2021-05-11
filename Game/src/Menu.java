import javax.swing.JPanel;

public class Menu extends JPanel implements IButton {
    private IMenu listener;

    public Menu() {
        super();
        Button bNewGame = new Button("New Game");
        Button bExit = new Button("Exit");
        bNewGame.addListener(this);
        bExit.addListener(this);
        this.add(bNewGame);
        this.add(bExit);
    }

    public void AddListener(IMenu listener) {
        this.listener = listener;
    }

    @Override
    public void OnButtonPressed(String name) {

        // TODO Auto-generated method stub
        if (name == "New Game") {
            listener.OnCommand(ButtonEvent.NEW_GAME);
        } else if (name == "Exit") {
            listener.OnCommand(ButtonEvent.END_GAME);
        }
    }

}