/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import poker.domain.Card;
import poker.domain.Hand;
import poker.logic.Calculate;
import poker.logic.WinningHands;

/**
 *
 * @author Ivan Local
 */
public class GUI implements Runnable {

    private JLabel balance;
    private JFrame frame;
    private Calculate calculate;
    private ArrayList<Card> cards;
    private ArrayList<JButton> cardImageButtons;
    private ArrayList<Icon> cardIcons;
    private ArrayList<Boolean> cardsSelected;
    private boolean isThisFirstDealing;
    private JTextField isThisFirstDealingTextField;
    private JComboBox<String> betComboBox;

    public GUI() {
        //inicijalizacija calculate logike i posavljanje balance na 100
        this.calculate = new Calculate(100, 5);
        System.out.println("HAND is ***   " + calculate.getDeal().getHand().toString());
        this.cardImageButtons = new ArrayList();
        this.cardIcons = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.isThisFirstDealing = true;
        this.cardsSelected = new ArrayList<>();
        setCardsSelectedToFalse();
    }

    @Override
    public void run() {

        frame = new JFrame("Bife Poker");
        frame.setPreferredSize(new Dimension(16 * 111, 9 * 84));
        positionJFramToCenter();
        frame.getContentPane().setBackground(Color.WHITE);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container) {
        container.add(createDefaultMainStage(), BorderLayout.CENTER);
        //container.add(createMainStage(this.calculate.getDeal().getHand()), BorderLayout.CENTER);
        container.add(createNorthPanel(), BorderLayout.NORTH);
        container.add(createSouthPanel(), BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void positionJFramToCenter() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dim.width / 20;
        int y = dim.height / 6;
        this.frame.setLocation(x, y);
    }

    private JPanel createNorthPanel() {
        GridLayout gridLayout = new GridLayout(1, 6, 10, 10);
        JPanel panel = new JPanel(gridLayout);
        //JPanel panel = new JPanel();
        JButton quit = new JButton("Quit");
        isThisFirstDealingTextField = new JTextField(getIsThisText());
        Dimension dim = new Dimension(400, 28);
        isThisFirstDealingTextField.setPreferredSize(dim);
        //quit.setAlignmentX(0);
        String balance = String.valueOf(calculate.getBalance());
        this.balance = new JLabel(balance);
        JLabel credit = new JLabel("CREDIT");
        Dimension dimCredit = new Dimension(100, 28);
        credit.setPreferredSize(dimCredit);
        this.balance.setPreferredSize(dimCredit);
        JLabel labelBet = new JLabel("BET");
        String[] comboBetIems = {"5", "10", "20", "50", "100", "200", "500", "1000"};
        this.betComboBox = new JComboBox(comboBetIems);
        this.betComboBox.addActionListener(getComboBetListener());


        panel.add(quit);
        panel.add(isThisFirstDealingTextField);
        panel.add(labelBet);
        panel.add(this.betComboBox);
        panel.add(credit);
        panel.add(this.balance);
        return panel;
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel();
        JButton dealButton = new JButton("DEAL");
        Font dealFont = new Font("Calibri", Font.BOLD, 48);
        dealButton.setFont(dealFont);
        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (getIsThisFirstDealing()) {
                    
                    betComboBox.setVisible(false);
                    System.out.println("FIRST DEALING");
                    System.out.println("First hand begins. Selected cards are " + cardsSelected);
                    calculate.makeFirstHand();
                    setCardsSelectedToFalse();
                    String bal = String.valueOf(calculate.getBalance());
                    balance.setText(bal);
                    checkListsAndClearThem();
                    //pronaci lokaciju slika ovih karata
                    makeArrayListofCards();
                    System.out.println("First hand over Selected cards are " + cardsSelected);
                } else {
                    betComboBox.setVisible(true);
                    System.out.println("SECOND DEALING");
                    System.out.println("Second hand begins. Selected cards are " + cardsSelected);
                    System.out.println("Actual Cards is calculate.getDeal.getHand.getPlayerHand " + cards.toString());
                    String replaceString = getReplaceString();
                    calculate.replaceCards(replaceString);
                    //calculate.replaceCards("qw");
                    checkListsAndClearThem();
                    makeArrayListofCards();
                    //ovde se sada dodaje winning hand interfejs
                    int winningAmount = calculateHandWinningAmount();
                    //ukoliko je nesto dobijeno poziva se metoda za igranje veca manja
                    if (winningAmount > 0) {
                        //ovo uraditi sutra
                        //winningAmount = vecaManjaPlay(winningAmount);
                        //za sada samo uvecvati sumu
                        //calculate.addToBalance(winningAmount);
                        //System.out.println("POGODAK " + winningAmount);
                        GuiVecaManja guiVecaManja = new GuiVecaManja();
                        guiVecaManja.initializaCalculate(calculate, winningAmount);
                        guiVecaManja.setVisible(true);
                    }

                    System.out.println("Second hand over. Selected cards are " + cardsSelected);
                }
                switchIsThisFirstDealing();
                isThisFirstDealingTextField.setText(getIsThisText());
            }
        });
        Dimension buttonSize = new Dimension(16 * 110, 80);
        //System.out.println("Button size is " + buttonSize);
        dealButton.setPreferredSize(buttonSize);
        panel.add(dealButton);
        return panel;
    }

    int calculateHandWinningAmount() {
        WinningHands wh = new WinningHands(this.calculate.getDeal().getHand(), this.calculate.getBet());
        return wh.calculateWinningHand();
    }

    private JPanel createDefaultMainStage() {
        GridLayout gridLayout = new GridLayout(1, 5, 10, 10);
        JPanel mainPanel = new JPanel(gridLayout);
        Icon cardBack = getCardImage("Pictures/yellow_back.png");

        JButton buttonCard1 = new JButton(cardBack);
        buttonCard1.setBackground(Color.WHITE);
        buttonCard1.setOpaque(true);
        buttonCard1.setBorder(new RoundedBorder(10));
        //metodika rada svakog card dugmeta
        buttonCard1.addActionListener(getCard1AL());
        this.cardImageButtons.add(buttonCard1);

        JButton buttonCard2 = new JButton(cardBack);
        buttonCard2.setBackground(Color.WHITE);
        buttonCard2.setOpaque(true);
        buttonCard2.setBorder(new RoundedBorder(10));
        //metodika rada svakog card dugmeta
        buttonCard2.addActionListener(getCard2AL());
        this.cardImageButtons.add(buttonCard2);

        JButton buttonCard3 = new JButton(cardBack);
        buttonCard3.setBackground(Color.WHITE);
        buttonCard3.setOpaque(true);
        buttonCard3.setBorder(new RoundedBorder(10));
        //metodika rada svakog card dugmeta
        buttonCard3.addActionListener(getCard3AL());
        this.cardImageButtons.add(buttonCard3);

        JButton buttonCard4 = new JButton(cardBack);
        buttonCard4.setBackground(Color.WHITE);
        buttonCard4.setOpaque(true);
        buttonCard4.setBorder(new RoundedBorder(10));
        //metodika rada svakog card dugmeta
        buttonCard4.addActionListener(getCard4AL());
        this.cardImageButtons.add(buttonCard4);

        JButton buttonCard5 = new JButton(cardBack);
        buttonCard5.setBackground(Color.WHITE);
        buttonCard5.setOpaque(true);
        buttonCard5.setBorder(new RoundedBorder(10));
        //metodika rada svakog card dugmeta
        buttonCard5.addActionListener(getCard5AL());
        this.cardImageButtons.add(buttonCard5);

        mainPanel.add(buttonCard1);
        mainPanel.add(buttonCard2);
        mainPanel.add(buttonCard3);
        mainPanel.add(buttonCard4);
        mainPanel.add(buttonCard5);

        return mainPanel;
    }

    private JPanel createMainStage(Hand hand) {
        JPanel mainPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        for (Card card : hand.getPlayerHand()) {
            Icon cardImage = getCardImage(card.getImageLocation());
            mainPanel.add(new JButton(cardImage));
        }
        return mainPanel;
    }

    private JPanel crateMainStageTest() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        Icon card1 = getCardImage("Pictures/10H.png");
        Icon card2 = getCardImage("Pictures/JH.png");
        Icon card3 = getCardImage("Pictures/QH.png");
        Icon card4 = getCardImage("Pictures/KH.png");
        Icon card5 = getCardImage("Pictures/AH.png");

        mainPanel.add(new JButton(card1));
        mainPanel.add(new JButton(card2));
        mainPanel.add(new JButton(card3));
        mainPanel.add(new JButton(card4));
        mainPanel.add(new JButton(card5));
        return mainPanel;
    }

    private Icon getCardImage(String locationURL) {
        try {
            File file = new File(locationURL);
            BufferedImage originalImage = ImageIO.read(file);
            int height = originalImage.getHeight();
            int width = originalImage.getWidth();
            //System.out.println("Original image size is height " + height + " width is " + width);
            Icon card = new ImageIcon(locationURL);
            return card;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private boolean getIsThisFirstDealing() {
        return this.isThisFirstDealing;
    }

    private void switchIsThisFirstDealing() {
        if (getIsThisFirstDealing()) {
            this.isThisFirstDealing = false;
        } else {
            this.isThisFirstDealing = true;
        }
    }

    private String getIsThisText() {
        String isThisFirst;
        if (this.isThisFirstDealing == true) {
            isThisFirst = "This is first Dealing!!!";
        } else {
            isThisFirst = "Selected Cards will be replaced!!!";
        }
        return isThisFirst;
    }

    //ova metoda poravi listu akutelnih karata ikona i postavlja ikone na slikama na odgovarajuce vrednosti
    private void makeArrayListofCards() {
        //this.cards = this.calculate.getDeal().getHand().getPlayerHand();
        for (int i = 0; i < this.calculate.getDeal().getHand().getPlayerHand().size(); i++) {
            Card card = this.calculate.getDeal().getHand().getPlayerHand().get(i);
            this.cards.add(card);
            String iconLocation = card.getImageLocation();
            //napraviti ikone
            Icon icon = getCardImage(iconLocation);
            this.cardIcons.add(icon);
            //postaviti ikone na buttons
            this.cardImageButtons.get(i).setIcon(icon);
        }
        System.out.println("make Array list of cards CALLED: cards are now " + this.cards.toString());

    }

    private void checkListsAndClearThem() {
        if (!this.cards.isEmpty()) {
            this.cards.clear();
        }
        if (!this.cardIcons.isEmpty()) {
            this.cardIcons.clear();
        }
    }

    private void setCardsSelectedToFalse() {
        this.cardsSelected.clear();
        for (int i = 0; i < 5; i++) {
            this.cardsSelected.add(i, false);
            if (!this.cardImageButtons.isEmpty()) {
                this.cardImageButtons.get(i).setBackground(Color.white);
            }
        }
    }

    private void switchCardSelected(int i) {
        if (this.cardsSelected.get(i) == true) {
            this.cardsSelected.remove(i);
            this.cardsSelected.add(i, false);
            this.cardImageButtons.get(i).setBackground(Color.white);
        } else {
            this.cardsSelected.remove(i);
            this.cardsSelected.add(i, true);
            this.cardImageButtons.get(i).setBackground(Color.red);
        }
    }

    private ActionListener getCard1AL() {
        int i = 0;
        ActionListener card1AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switchCardSelected(0);
                System.out.println("Card " + i + " is selected " + cardsSelected.get(i));
            }
        };
        return card1AL;
    }

    private ActionListener getCard2AL() {
        int i = 1;
        ActionListener card1AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switchCardSelected(1);
                System.out.println("Card " + i + " is selected " + cardsSelected.get(i));
            }
        };
        return card1AL;
    }

    private ActionListener getCard3AL() {
        int i = 2;
        ActionListener card1AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switchCardSelected(i);
                System.out.println("Card " + i + " is selected " + cardsSelected.get(i));
            }
        };
        return card1AL;
    }

    private ActionListener getCard4AL() {
        int i = 3;
        ActionListener card1AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switchCardSelected(3);
                System.out.println("Card " + i + " is selected " + cardsSelected.get(i));
            }
        };
        return card1AL;
    }

    private ActionListener getCard5AL() {
        int i = 4;
        ActionListener card1AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switchCardSelected(4);
                System.out.println("Card " + i + " is selected " + cardsSelected.get(i));
            }
        };
        return card1AL;
    }

    private String getReplaceString() {
        StringBuilder sb = new StringBuilder();
        if (cardsSelected.get(0) == true) {
            sb.append('q');
        }
        if (cardsSelected.get(1) == true) {
            sb.append('w');
        }
        if (cardsSelected.get(2) == true) {
            sb.append('e');
        }
        if (cardsSelected.get(3) == true) {
            sb.append('r');
        }
        if (cardsSelected.get(4) == true) {
            sb.append('t');
        }
        String toBeReplacedString = sb.toString();
        System.out.println("String to be replaced is " + toBeReplacedString);
        return toBeReplacedString;
    }

    private ActionListener getComboBetListener() {
        ActionListener comboBetListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String betString = (String)betComboBox.getSelectedItem();
                int betAmount = Integer.valueOf(betString);
                System.out.println("BET Amount is " + betAmount);
                if (betAmount <= calculate.getBalance()) {
                    calculate.setBet(betAmount);
                    System.out.println("BET Changed to " + betComboBox.toString());
                } else {
                    System.out.println("BET Could not be changed to that value");
                    betComboBox.setSelectedIndex(0);
                }

            }
        };
        return comboBetListener;
    }

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

    }

}
