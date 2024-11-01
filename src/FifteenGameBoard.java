import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class FifteenGameBoard extends JFrame {

    Logic logic;
    boolean easyWin;

    JPanel panel = new JPanel();
    JPanel labelPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JButton newGameButton = new JButton("New Game");

    JLabel winnerLabel = new JLabel("You won!");
    JLabel invalidMoveLabel = new JLabel("Invalid move");
    JLabel pushedEmptyLabel = new JLabel("Only numbered tiles can be clicked.");

    LinkedList<JButton> buttons = new LinkedList<>();

    FifteenGameBoard(boolean easyWin) {
        this.easyWin = easyWin;

        //Vi har tre paneler, en med labels, en med spelknappar, och en med nytt spel-knappen
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

        add(panel);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void moveAction(JButton button) {

        if (button.getText() != null) {
            int indexPushed = buttons.indexOf(button);

            if (logic.validMove(indexPushed)) {
                setBoard();
            }//else //Visa invalidMoveLabel
        }//else // Visa pushedEmptyLabel
    }
    public void newGameAction() {
        logic = new Logic(easyWin);

        //Här sätter jag actionlistner och gör lambda-anrop till metoden moveAction(button)
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
            if (i != logic.findEmptyTile())
                button.setText(String.valueOf(tempTile.getTileNumber()));
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
                button.setFont(new Font("Serif", Font.BOLD, 40));
            i++;
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();

        if (logic.checkWinning()){
            //Anropa vinstmetod?
            win();
        }
    }
    //Metod för win-action
    public void win(){
        buttons.get(15).setBackground(logic.getTilesList().get(0).getTileColor());
        for(JButton button : buttons) {
            button.setText(null);
            button.setEnabled(false);
        }
        //visa winnerLabel
        buttonPanel.revalidate();
        buttonPanel.repaint();

    }
}
