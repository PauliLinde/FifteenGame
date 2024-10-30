import javax.swing.*;
import java.awt.*;

public class FifteenGameBoard extends JFrame {

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
    ButtonGroup gameButtons = new ButtonGroup();

    JButton newGameButton = new JButton("New Game");


    FifteenGameBoard() {
        add(panel);
        panel.setLayout(new BorderLayout());
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        northPanel.setLayout(new GridLayout(4, 4));
        southPanel.setLayout(new FlowLayout());

        //Här tror jag att vi behöver anropa en metod som blandar siffrorna
        //och då lägger till knapparna i den ordning som uppstår

        southPanel.add(newGameButton);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
