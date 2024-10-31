import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class FifteenGameBoard extends JFrame implements ActionListener {

    Logic logic;
    //boolean easyWin;
    JPanel panel = new JPanel();
    //Knapppanel:
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JButton oneButton = new JButton(), twoButton = new JButton(), threeButton = new JButton(), fourButton = new JButton(),
        fiveButton = new JButton(), sixButton = new JButton(), sevenButton = new JButton(), eightButton = new JButton(),
        nineButton = new JButton(), tenButton = new JButton(), elevenButton = new JButton(), twelveButton = new JButton(),
        thirteenButton = new JButton(), fourteenButton = new JButton(), fifteenButton = new JButton(), emptyButton = new JButton();
    ButtonGroup gameButtons = new ButtonGroup();

    JButton newGameButton = new JButton("New Game");
    JLabel winnerLabel = new JLabel("You won!");

    FifteenGameBoard() {

        panel.setLayout(new BorderLayout());
        //Vi har två paneler, en med spelknappar, och en med nytt spel-knappen
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        northPanel.setLayout(new GridLayout(4, 4));
        southPanel.setLayout(new FlowLayout());

        //Alla spelknappar läggs till i en ButtonGroup
        //Antingen så är det väldigt onödigt, eller så är det bra av nån anledning
        //Kanske behövs om vi ska ha en ram runt knapparna?
        gameButtons.add(emptyButton); gameButtons.add(oneButton); gameButtons.add(twoButton);
        gameButtons.add(threeButton); gameButtons.add(fourButton); gameButtons.add(fiveButton);
        gameButtons.add(sixButton); gameButtons.add(sevenButton); gameButtons.add(eightButton);
        gameButtons.add(nineButton); gameButtons.add(tenButton); gameButtons.add(elevenButton);
        gameButtons.add(twelveButton); gameButtons.add(thirteenButton); gameButtons.add(fourteenButton);
        gameButtons.add(fifteenButton);

        southPanel.add(newGameButton);
        newGameButton.addActionListener(l -> newGameAction());

        add(panel);
        setSize(300, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void newGameAction() {
        logic = new Logic();
        LinkedList<JButton> buttons = new LinkedList<>();

        buttons.add(oneButton); buttons.add(twoButton); buttons.add(threeButton); buttons.add(fourButton);
        buttons.add(fiveButton); buttons.add(sixButton); buttons.add(sevenButton); buttons.add(eightButton);
        buttons.add(nineButton); buttons.add(tenButton); buttons.add(elevenButton); buttons.add(twelveButton);
        buttons.add(thirteenButton); buttons.add(fourteenButton); buttons.add(fifteenButton); buttons.add(emptyButton);

        int i = 0;
        for(JButton button : buttons) {
            northPanel.add(button);
            if (i != logic.findEmptyTile())
                button.setText(logic.getTilesList().get(i).toString());

            i++;
        }
        northPanel.revalidate();
        northPanel.repaint();
    }
}
