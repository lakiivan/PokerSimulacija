/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.domain;

import java.util.ArrayList;

/**
 *
 * @author Ivan Local
 */
public class Deck {

    private ArrayList<Card> deck;
    private int jokerNo;

    public Deck(int jokerNo) {
        this.deck = new ArrayList<>();
        this.jokerNo = jokerNo;
        this.cardsInitialization();
        this.jokerInitialization(jokerNo);
    }

    //default constructor no joker
    public Deck() {
        this(0);
    }

    //create card obejcts of all cards in a deck
    private void cardsInitialization() {
        for (Znak znak : Znak.values()) {
            if (znak.ordinal() == 4) {
                break;
            } else {
                for (int value = 2; value < 15; value++) {
                    Card card = new Card(value, znak);
                    this.deck.add(card);
                }
            }

        }
    }

    //inicijalizacija jokera
    private void jokerInitialization(int jokerNo) {
        int value = 15;
        for (int i = 0; i < jokerNo; i++) {
            Card joker = new Card(value, Znak.JOKER);
            deck.add(joker);
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public int getJokerNo() {
        return jokerNo;
    }

    public int deckSize() {
        return this.deck.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : deck) {
            sb.append(card.toString() + "\n");
        }
        return sb.toString();
    }
}
