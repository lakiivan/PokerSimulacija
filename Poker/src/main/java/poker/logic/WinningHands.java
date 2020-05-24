/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import poker.domain.Card;
import poker.domain.Hand;
import poker.domain.Znak;

/**
 *
 * @author Ivan Local
 */
public class WinningHands {

    private int winningAmount;
    private final Hand hand;
    private int bet;
    private final int jacksOrBetter;
    private final int twoPairs;
    private final int threeOfKind;
    private final int straight;
    private final int flush;
    private final int fullHouse;
    private final int poker;
    private final int straightFlush;
    private final int royalFlush;

    private HashMap<Integer, Integer> handMap;
    private ArrayList<Card> sortedCards;

    public WinningHands(Hand hand, int bet) {
        this.winningAmount = 0;
        this.hand = hand;
        this.jacksOrBetter = 5;
        this.twoPairs = 10;
        this.threeOfKind = 15;
        this.straight = 20;
        this.flush = 30;
        this.fullHouse = 45;
        this.poker = 125;
        this.straightFlush = 250;
        this.royalFlush = 4000;

        this.bet = bet;

        //pravljenje sortiranog niza karata po velicini
        //System.out.println("Sortirani HAND: ");
        ArrayList<Card> unsortedCards = hand.getPlayerHand();
        this.sortedCards = new ArrayList<>();
        unsortedCards.stream().sorted().forEach(card -> this.sortedCards.add(card));
        //System.out.println("UNSORTED HAND CARDS");
        //System.out.println(hand.getPlayerHand().toString());
        System.out.println("SORTED CARDS FROM HAND");
        System.out.println(this.sortedCards);
        createHandMap(hand);
        System.out.println("HAND MAP IS " + handMap.toString());
    }

    private void createHandMap(Hand hand) {
        this.handMap = new HashMap<>();
        for (Card card : hand.getPlayerHand()) {
            int currValue = card.getValue();
            if (!handMap.containsKey(card.getValue())) {
                handMap.put(currValue, 1);
            } else {
                int value = handMap.get(currValue);
                //System.out.println("Hand map value before adding one " + value);
                value++;
                //System.out.println("Hand map value after adding one " + value);
                handMap.put(currValue, value);
            }
        }
    }

    private int calculatingFactor() {
        return this.bet / 5;
    }

    //pomocna metoda za proveru da li su sve karte u istom znaku
    private boolean isFlush() {
        Znak znak = this.sortedCards.get(0).getZnak();
        for (Card card : this.sortedCards) {
            if (!card.getZnak().equals(znak)) {
                return false;
            }
        }
        return true;
    }

    //pomocna metoda koja proverava da li imamo kentu
    private boolean isStraight() {

        int currCardValue = this.sortedCards.get(0).getValue();
        if (straightIsFirstCard2(currCardValue)) {
            for (int i = 1; i < this.sortedCards.size() - 1; i++) {
                if (this.sortedCards.get(i).getValue() != currCardValue + 1) {
                    return false;
                }
                currCardValue++;
            }
            if (this.sortedCards.get(4).getValue() == 6
                    || this.sortedCards.get(4).getValue() == 14) {
                return true;
            }
            return false;
        } else {
            for (int i = 1; i < 5; i++) {
                if (this.sortedCards.get(i).getValue() != currCardValue + 1) {
                    return false;
                }
                currCardValue++;
            }
            return true;
        }
    }

    private boolean straightIsFirstCard2(int currCardValue) {
        return (currCardValue == 2);
    }

    //pomocna metoda koja proveraca da li imamo kentu u boji
    private boolean isStraightFlush() {
        int firstCard = this.sortedCards.get(0).getValue();
        return isStraight() && isFlush();
    }

    //pomocna metoda koja proverava da li imamo flush royal
    private boolean isFlushRoyal() {
        int firstCard = this.sortedCards.get(0).getValue();
        return firstCard == 10 && isStraightFlush();
    }

    private boolean isPoker() {
        return handMap.keySet().stream().anyMatch((key) -> (handMap.get(key) == 4));
    }

    private boolean isThreeOfKind() {
        for (int i : handMap.keySet()) {
            if (handMap.get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isJackOrHigher() {
        int pairValue = 0;
        for (int key : handMap.keySet()) {
            if (handMap.get(key) == 2) {
                pairValue = key;
            }
        }
        return pairValue > 10;
    }

    public int calculateWinningHand() {
        //System.out.println("Calculating winning hand: ");
        //System.out.println("CURR SIZE IS " + handMap.size());
        if (handMap.size() == 5) {
            //napraviti metodu koja trazi da li ima flush royal
            if (isFlushRoyal()) {
                System.out.println("*******************");
                System.out.println("FLUSH ROYAL");
                System.out.println("*******************");
                return this.royalFlush * calculatingFactor();
            }
            //napraviti metodu koja trazi da li ima straight flush
            if (isStraightFlush()) {
                System.out.println("STRAIGHT FLUSH");
                return this.straightFlush * calculatingFactor();
            }
            //napraviti metodu koja trazi da li ima flush
            if (isFlush()) {
                System.out.println("Flush");
                return this.flush * calculatingFactor();
            }
            //napraviti metodu koja trazi da li ima straight
            if (isStraight()) {
                System.out.println("Straight");
                return this.straight * calculatingFactor();
            }
            return 0;
        } else if (handMap.size() == 4) {
            if (isJackOrHigher()) {
                System.out.println("One pair Jack or better");
                return this.jacksOrBetter * calculatingFactor();
            } else {
                return 0;
            }

        } else if (handMap.size() == 3) {
            if (isThreeOfKind()) {
                System.out.println("Three of Kind");
                return this.threeOfKind * calculatingFactor();
            }
            //dva para
            System.out.println("Two Pairs");
            return this.twoPairs * calculatingFactor();
        } else if (handMap.size() == 2) {
            //poker
            if (isPoker()) {
                System.out.println("POKER");
                return this.poker * calculatingFactor();
            } else {
                System.out.println("Full House");
                return this.fullHouse * calculatingFactor();
            }

        }
        //nista nije pronadjeno znaci da nema pogotka vratiti nulu
        return 0;
    }

}
