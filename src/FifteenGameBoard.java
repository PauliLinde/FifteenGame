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

    JButton oneButton = new JButton(), twoButton = new JButton(), threeButton = new JButton(), fourButton = new JButton(),
        fiveButton = new JButton(), sixButton = new JButton(), sevenButton = new JButton(), eightButton = new JButton(),
        nineButton = new JButton(), tenButton = new JButton(), elevenButton = new JButton(), twelveButton = new JButton(),
        thirteenButton = new JButton(), fourteenButton = new JButton(), fifteenButton = new JButton(), emptyButton = new JButton();


    JButton newGameButton = new JButton("New Game");

    JLabel messageLabel = new JLabel("");
    JLabel counterLabel = new JLabel("");

    JLabel winnerLabel = new JLabel("You won!");

    LinkedList<JButton> buttons = new LinkedList<>();

    FifteenGameBoard(boolean easyWin) {
        this.easyWin = easyWin;

        //Vi har tre paneler, en med labels, en med spelknappar, och en med nytt spel-knappen
        panel.setLayout(new BorderLayout());
        panel.add(labelPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        buttons.add(oneButton); buttons.add(twoButton); buttons.add(threeButton); buttons.add(fourButton);
        buttons.add(fiveButton); buttons.add(sixButton); buttons.add(sevenButton); buttons.add(eightButton);
        buttons.add(nineButton); buttons.add(tenButton); buttons.add(elevenButton); buttons.add(twelveButton);
        buttons.add(thirteenButton); buttons.add(fourteenButton); buttons.add(fifteenButton); buttons.add(emptyButton);

        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.setPreferredSize(new Dimension(500, 500));
        southPanel.setLayout(new FlowLayout());

        southPanel.add(newGameButton);
        newGameButton.addActionListener(l -> newGameAction());

        labelPanel.setLayout(new GridLayout(1,2));
        labelPanel.add(messageLabel); labelPanel.add(counterLabel);

        add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }
    public void moveAction(JButton button) {
        logic.countMoves();
        counterLabel.setText("Moves: " + logic.getCounter());

        if (button.getText() != null) {
            int numberInPlay = Integer.parseInt(button.getText());

            if (logic.validMove(numberInPlay)) {
                setBoard();
                messageLabel.setText("");
            }//else //Visa invalidMoveLabel
            else{
                messageLabel.setText(" Invalid move");
            }
        }//else // Visa pushedEmptyLabel
        else{
            messageLabel.setText(" Only numbered tiles can be clicked");
        }
    }

    public void newGameAction() {
        logic = new Logic(easyWin);

        buttonPanel.removeAll();
        messageLabel.setText("");
        counterLabel.setText("");

        //Här sätter jag actionlistner och gör lambda-anrop till metoden moveAction(button)
        for(JButton button : buttons) {
            button.addActionListener(l -> moveAction(button));
            buttonPanel.add(button);
        }
        setBoard();
    }

    public void setBoard(){
        int i = 0;
        for(JButton button : buttons) {
            button.setText(null);
            if (i != logic.findEmptyTile())
                button.setText(logic.getTilesList().get(i).toString());
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
        //Test
        System.out.println("win");
        //visa winnerLabel
        for(JButton button : buttons) {
            button.removeActionListener(l -> moveAction(button));
            buttonPanel.removeAll();
            buttonPanel.add(winnerLabel);
            messageLabel.setText(" You won!");
            winnerLabel.setFont(new Font("Serif", Font.BOLD, 100));
            winnerLabel.setForeground(Color.RED);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();

    }
}
