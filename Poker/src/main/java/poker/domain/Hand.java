/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.domain;

import java.util.ArrayList;
import poker.domain.Card;
import poker.domain.Znak;

/**
 *
 * @author Ivan Local
 */
public class Hand {

    private ArrayList<Card> playerHand;
    private int noCards;

    //default constructor je predvidjen za poker hand ima uvek maksimum  karata
    public Hand() {
        this.playerHand = new ArrayList<>();
        this.noCards = 5;
    }
    //hand za svrhe testiranja
    public Hand(ArrayList<Card> handCards){
        this();
        for(Card card : handCards){
            playerHand.add(card);
        }
    }
    

    //metoda kojom se dodaje karta u hand posto klasa deal dodeljuje validnu random kartu
    //ova metoda ce tu kartu dodati u hand
    public void addCard(Card card) {
        if (this.playerHand.size() <= 5) {
            this.playerHand.add(card);
        }
    }

    //kada se neka karta zameni drugom kartom klasa user interface salje poziciju karte
    // koju zelimo da zamenimo novom kartom, aklasa deal obezbedjuje validnu novu kartu
    public void switchCards(int position, Card card) {
        if (position >= 0 && position < 5) {
            this.playerHand.set(position, card);
        }
    }

    //metoda koja vraca sve karte u handu
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    //metoda za ispisvanje karata koje ima igrac HAND
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : this.playerHand) {
            sb.append(card.toString()).append("   ***   ");
        }
        return sb.toString();
    }

}
