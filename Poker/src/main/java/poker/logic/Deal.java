/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.logic;

import java.util.ArrayList;
import java.util.Random;
import poker.domain.Card;
import poker.domain.Deck;
import poker.domain.Hand;
import poker.domain.Znak;

/**
 *
 * @author Ivan Local
 */
public class Deal {

    private Deck deck;
    private Hand hand;
    private ArrayList<Integer> alreadyDealed;
    private Random random;
    
    public Deal (Random random){
        this.random = random;
        this.deck = new Deck();
        this.hand = new Hand();
        //polje koje prati koje su sve karte do sada upotrebljene kako ne bi bile dodeljene ponovo u hand
        this.alreadyDealed = new ArrayList<>();
        //makeFirstHand();
    }
    
    public Deal() {
        this(new Random());
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getHand() {
        return hand;
    }

    public ArrayList<Integer> getAlreadyDealed() {
        return alreadyDealed;
    }

    //metoda koja sluzi za deljenje prvih pet karata za igraca pa je private
    //ona u startu pravi hand
    public void makeFirstHand() {
        //lista koja belezi koi su brojevi vec bili kako se ne bi podelile dve iste karte
        for (int i = 0; i < 5; i++) {
            Card card = getRandomCard();
            this.hand.addCard(card);
        }
    }

    //helper metoda za dobijanje random broja velicine deck size u ovom slucaju to je od 0 do 51
    private int getRndNum() {
        return this.random.nextInt(this.deck.deckSize());
    }

    //helper metoda za odredjivanje znaka na osnovu random broja
    private Znak getZnak(int rnd) {
        //uslov za jokera
        if (rnd == 53) {
            return Znak.JOKER;
        }
        //uslovi za ostale znakove
        if (rnd / 13 == 0) {
            return Znak.PIK;
        } else if (rnd / 13 == 1) {
            return Znak.KARO;
        } else if (rnd / 13 == 2) {
            return Znak.HERC;
        } else {
            return Znak.TREF;
        }
    }

    //helper meotda za odredjivanje broja karte na osnovu random broja
    private int getNumberForHand(int rnd) {
        return rnd % 13 + 2;
    }

    //helper metoda koja proverava da li je broj vec bio izvucen
    //odnosno da li je karta vec podeljena
    private boolean isNumberOk(int rnd) {
        if (!alreadyDealed.contains(rnd)) {
            this.alreadyDealed.add(rnd);
            return true;
        } else {
            return false;
        }
    }

    public Card getRandomCard() {
        int rnd = getRndNum();
        while (true) {
            if (isNumberOk(rnd)) {
                Znak znak = getZnak(rnd);
                int cardNumber = getNumberForHand(rnd);
                Card card = new Card(cardNumber, znak);
                return card;
            }
            rnd = getRndNum();
        }
    }

    public void replaceCard(int position) {
        Card card = getRandomCard();
        //System.out.println("New Card is " + card.toString());
        this.hand.switchCards(position, card);
    }

    @Override
    public String toString() {
        return this.getHand().toString();
    }
}
