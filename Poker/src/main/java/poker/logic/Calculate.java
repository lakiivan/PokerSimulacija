/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.logic;

/**
 *
 * @author Ivan Local
 */
public class Calculate {

    private int balance;
    private int bet;
    private Deal deal;
    private int maxAmount;

    public Calculate(int initAmount, int bet) {
        this.balance = initAmount;
        this.bet = bet;
        this.deal = new Deal();
        this.maxAmount = 120000;
    }

    public Calculate(int initAmount) {
        this(initAmount, 5);
    }

    public void setBet(int bet) {
        if (this.balance >= bet) {
            this.bet = bet;
        }
    }

    public void setBalance(int amount) {
        if (amount > 99 && amount < 12001) {
            this.balance = amount;
        } else {
            System.out.println("Balance was not set correctly should be between 100 and 12000");
        }
    }

    public void addToBalance(int amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public int getBalance() {
        return balance;
    }

    public int getBet() {
        return bet;
    }

    public Deal getDeal() {
        return deal;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public boolean isBetPossible(int newBet) {
        if (newBet > 4 && newBet <= this.balance && newBet < 1201) {
            return true;
        } else {
            return false;
        }
    }

    //if bet is bigger than balance make bet to be the same as balance
    public void makeBetPossible() {
        if (this.balance < this.bet) {
            this.bet = this.balance;
        }
    }

    public void makeFirstHand() {
        if (balance >= bet) {
            deal = new Deal();
            this.deal.makeFirstHand();
            this.balance -= this.bet;
            System.out.println("Your Cards are: ");
            System.out.println(deal.toString());
        } else {
            System.out.println("You do not have enough credit");
        }
    }

    public void replaceCards(String input) {
        int[] positions = stringToIntPosition(input);
        for (int position : positions) {
            this.deal.replaceCard(position);
        }
    }

    //helper metod za replaceCards prebacuje string u niz integera koji oznacavaju pozicije karata koje bi trebalo da budu zamenjene
    private int[] stringToIntPosition(String input) {
        int length = input.length();
        int[] positions = new int[length];
        char[] chs = input.toCharArray();
        int i = 0;
        for (char ch : chs) {
            if (ch == 'q') {
                positions[i] = 0;
            } else if (ch == 'w') {
                positions[i] = 1;
            } else if (ch == 'e') {
                positions[i] = 2;
            } else if (ch == 'r') {
                positions[i] = 3;
            } else if (ch == 't') {
                positions[i] = 4;
            }
            i++;
        }
        //System.out.println("Poisitons are ");
        //for (int j : positions) {
        //    System.out.print(j);
        //}
        //System.out.println("");
        return positions;
    }

    @Override
    public String toString() {
        return this.deal.toString();
    }

}
