/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.logic;

import java.util.ArrayList;
import poker.domain.Card;

/**
 *
 * @author Ivan Local
 */
public class VecaManja {

    private boolean isVeca;
    private Card randomCard;
    private int amount;
    private boolean isSeven;

    public VecaManja(boolean isVeca, Card randomCard, int amount) {
        this.isVeca = isVeca;
        this.randomCard = randomCard;
        this.amount = amount;

        setIsSeven(randomCard);

    }

    private boolean isRandomCardVeca(Card randomCard) {
        return randomCard.getValue() > 7 && randomCard.getValue() < 14;
    }

    private void setIsSeven(Card randomCard) {
        if (randomCard.getValue() == 7) {
            this.isSeven = true;
        } else {
            this.isSeven = false;
        }
    }

    private boolean isGuessRight() {
        if (isSeven == true) {
            return true;
        }
        if (isVeca == isRandomCardVeca(randomCard)) {
            System.out.println("Player gadjao Vecu - " + isVeca);
            System.out.println("Random karta je bila " + randomCard.toString());
            return true;
        } else {
            System.out.println("Player gadjao Vecu - " + isVeca);
            System.out.println("Random karta je bila " + randomCard.toString());
            return false;
        }
    }

    //glavna metoda izvozi rezultat ruke veca manja nazad
    public int calculateAmount() {
        if (isSeven == true) {
            System.out.println("7 PONOVO");
            return this.amount;
        }
        if (isGuessRight()) {
            System.out.println("POGODAK");
            return this.amount * 2;
        } else {
            System.out.println("PROMASAJ");
            return 0;
        }
    }
}
