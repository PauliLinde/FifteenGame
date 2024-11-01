import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class FifteenGameBoard extends JFrame {

    Logic logic;
    boolean easyWin;

    JPanel panel = new JPanel();
    JPanel labelPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JButton newGameButton = new JButton("New Game");

    JLabel messageLabel = new JLabel();
    JLabel counterLabel = new JLabel();
    JLabel winnerLabel = new JLabel("You won!");

    LinkedList<JButton> buttons = new LinkedList<>();

    FifteenGameBoard(boolean easyWin) {
        this.easyWin = easyWin;

        panel.setLayout(new BorderLayout());
        panel.add(labelPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        for (int i = 0; i < 16; i ++){
            JButton newButton = new JButton();
            buttons.add(newButton);
        }

        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.setPreferredSize(new Dimension(500, 500));

        southPanel.setLayout(new FlowLayout());

        southPanel.add(newGameButton);
        newGameButton.addActionListener(l -> newGameAction());

        labelPanel.setLayout(new GridLayout(1,2));
        labelPanel.add(messageLabel); labelPanel.add(counterLabel);

        add(panel);
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
                setBoard();
                messageLabel.setText(null);
            }
            else{
                messageLabel.setText(" Invalid move");
            }
        }
        else{
            messageLabel.setText(" Only numbered tiles can be clicked");
        }
    }
    public void newGameAction() {
        logic = new Logic(easyWin);

        buttonPanel.removeAll();
        messageLabel.setText(null);
        counterLabel.setText(null);

        for(JButton button : buttons) {
            button.setEnabled(true);
            button.addActionListener(l -> moveAction(button));
            buttonPanel.add(button);
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
        buttonPanel.revalidate();
        buttonPanel.repaint();

        if (logic.checkWinning())
            win();
    }

    public void win(){
        buttons.get(15).setBackground(logic.getTilesList().get(0).getTileColor());
        for(JButton button : buttons) {
            button.setText(null);
            button.setEnabled(false);
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();

        Timer timer = new Timer(1000, e -> {
            buttonPanel.removeAll();
            buttonPanel.add(winnerLabel);
            winnerLabel.setFont(new Font("Serif", Font.BOLD, 100));
            winnerLabel.setForeground(Color.RED);
        });

        timer.setRepeats(false);
        timer.start();

    }

}
