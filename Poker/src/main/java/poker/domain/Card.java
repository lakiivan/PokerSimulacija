/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.domain;

import javax.swing.Icon;

/**
 *
 * @author Ivan Local
 */
public class Card implements Comparable<Card> {

    private int value;
    private Znak znak;
    private String cardName;
    private String imageLocation;

    public Card(int value, Znak znak) {
        if (value < 2 || value > 15) {
            throw new IllegalArgumentException("Card value must be in range 2...14.");
        }
        this.value = value;
        this.znak = znak;
        this.cardName = this.toString();
        this.imageLocation = getImageLocation(value, znak);
    }

    @Override
    public String toString() {
        String cardValue = "" + value;
        if (value == 11) {
            cardValue = "J";
        } else if (value == 12) {
            cardValue = "Q";
        } else if (value == 13) {
            cardValue = "K";
        } else if (value == 14) {
            cardValue = "A";
        } else if (value == 15) {
            cardValue = "JOKER";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cardValue);
        if (sb.length() == 1) {
            return cardValue + "  " + znak;
        } else {
            return cardValue + " " + znak;
        }
    }

    public int getValue() {
        return value;
    }

    public Znak getZnak() {
        return znak;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    //meotda za autoamtsko dodeljivanje naziva fajla na osnovu vrednosti karte i njenog znaka
    private String getImageLocation(int value, Znak znak) {
        return imageLocation = "Pictures/" + stringOdValue(value) + stringOdZnaka(znak) + ".png";
    }

    //helper metod za vracanje stringa od vrednosti 
    private String stringOdValue(int value) {
        if (value > 10 && value < 16) {
            if (value == 11) {
                return "J";
            } else if (value == 12) {
                return "Q";
            } else if (value == 13) {
                return "K";
            } else if (value == 14) {
                return "A";
            } else {
                return "JOKER";
            }
        } else {
            return String.valueOf(value);
        }

    }

    //helper metod za vracanje stringa od znaka
    private String stringOdZnaka(Znak znak) {
        if (znak == Znak.HERC) {
            return "H";
        } else if (znak == Znak.KARO) {
            return "D";
        } else if (znak == Znak.PIK) {
            return "S";
        } else {
            return "C";
        }
    }

    @Override
    public int compareTo(Card other) {
        if (this.value < other.getValue()) {
            return -1;
        } else if (this.value > other.getValue()) {
            return +1;
        } else {
            if (this.znak.ordinal() < other.getZnak().ordinal()) {
                return -1;
            } else if (this.znak.ordinal() > other.getZnak().ordinal()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
