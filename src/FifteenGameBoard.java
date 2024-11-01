import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class FifteenGameBoard extends JFrame {

    Logic logic;
    boolean easyWin;

    JPanel basePanel = new JPanel();
    JPanel labelPanel = new JPanel();
    JPanel tilePanel = new JPanel();
    JPanel newGamePanel = new JPanel();

    JButton newGameButton = new JButton("New Game");

    JLabel messageLabel = new JLabel();
    JLabel counterLabel = new JLabel();
    JLabel winnerLabel = new JLabel(" You won! ");

    LinkedList<JButton> buttons = new LinkedList<>();

    public FifteenGameBoard(boolean easyWin) {
        this.easyWin = easyWin;

        basePanel.setLayout(new BorderLayout());
        basePanel.add(labelPanel, BorderLayout.NORTH);
        basePanel.add(tilePanel, BorderLayout.CENTER);
        basePanel.add(newGamePanel, BorderLayout.SOUTH);

        for (int i = 0; i < 16; i ++){
            JButton newButton = new JButton();
            buttons.add(newButton);
        }

        tilePanel.setLayout(new GridLayout(4, 4));
        tilePanel.setPreferredSize(new Dimension(500, 500));

        newGamePanel.setLayout(new FlowLayout());

        newGamePanel.add(newGameButton);
        newGameButton.addActionListener(l -> newGameAction());

        labelPanel.setLayout(new GridLayout(1,2));
        labelPanel.add(messageLabel); labelPanel.add(counterLabel);

        add(basePanel);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void moveAction(JButton button) {
        logic.countMoves();
        counterLabel.setText("Moves: " + logic.getCounter());

        if (button.getText() != null) {
            int indexPushed = buttons.indexOf(button);

            if (logic.validMove(indexPushed)) {

                messageLabel.setText(null);
            }
            else{
                messageLabel.setText(" Invalid move");
            }

        }
        else{
            messageLabel.setText(" Only numbered tiles can be clicked");
        }

        setBoard();
    }
    public void newGameAction() {
        logic = new Logic(easyWin);

        tilePanel.removeAll();
        messageLabel.setText(" ");
        counterLabel.setText("Moves: " + logic.getCounter());;

        for(JButton button : buttons) {
            button.setEnabled(true);
            button.addActionListener(l -> moveAction(button));
            tilePanel.add(button);
        }

        setBoard();
    }

    public void setBoard(){
        int i = 0;
        for(JButton button : buttons) {

            button.setText(null);
            Tile tempTile = logic.getTilesList().get(i);

            button.setBackground(tempTile.getTileColor());
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            button.setFont(new Font("Serif", Font.BOLD, 40));

            if (i != logic.findEmptyTile())
                button.setText(String.valueOf(tempTile.getTileNumber()));
            i++;
        }
        tilePanel.revalidate();
        tilePanel.repaint();

        if (logic.checkWinning())
            win();
    }

    public void win(){
        buttons.get(15).setBackground(logic.getTilesList().get(0).getTileColor());
        for(JButton button : buttons) {
            button.setText(null);
            button.setEnabled(false);
        }
        tilePanel.revalidate();
        tilePanel.repaint();

        Timer timer = new Timer(1000, e -> {
            tilePanel.removeAll();
            tilePanel.add(winnerLabel);
            winnerLabel.setFont(new Font("Serif", Font.BOLD, 100));
            winnerLabel.setForeground(Color.RED);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
