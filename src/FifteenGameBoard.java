import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;

public class FifteenGameBoard extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JButton emptyButton = new JButton("");
    JButton oneButton = new JButton("1");
    JButton twoButton = new JButton("2");
    JButton threeButton = new JButton("3");
    JButton fourButton = new JButton("4");
    JButton fiveButton = new JButton("5");
    JButton sixButton = new JButton("6");
    JButton sevenButton = new JButton("7");
    JButton eightButton = new JButton("8");
    JButton nineButton = new JButton("9");
    JButton tenButton = new JButton("10");
    JButton elevenButton = new JButton("11");
    JButton twelveButton = new JButton("12");
    JButton thirteenButton = new JButton("13");
    JButton fourteenButton = new JButton("14");
    JButton fifteenButton = new JButton("15");

    JButton newGameButton = new JButton("New Game");
    JLabel winnerLabel = new JLabel("You won!");


    FifteenGameBoard() {
        add(panel);
        panel.setLayout(new BorderLayout());
        //Vi har två paneler, en med spelknappar, och en med nytt spel-knappen
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        northPanel.setLayout(new GridLayout(4, 4));
        southPanel.setLayout(new FlowLayout());

        southPanel.add(newGameButton);
        newGameButton.addActionListener(this);

        pack();
        setSize(300, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            //Alla knappar läggs till i en lista
            LinkedList<JButton> buttons = new LinkedList<>();
            buttons.add(emptyButton); buttons.add(oneButton); buttons.add(twoButton); buttons.add(threeButton);
            buttons.add(fourButton); buttons.add(fiveButton); buttons.add(sixButton); buttons.add(sevenButton);
            buttons.add(eightButton); buttons.add(nineButton); buttons.add(tenButton); buttons.add(elevenButton);
            buttons.add(twelveButton); buttons.add(thirteenButton); buttons.add(fourteenButton); buttons.add(fifteenButton);
            Collections.shuffle(buttons);

            //Vi börjar med att rensa panelen, sen lägger vi till knapparna
            northPanel.removeAll();
            for(JButton button : buttons) {
                northPanel.add(button);
            }
            northPanel.revalidate();
            northPanel.repaint();
        }
    }

}
