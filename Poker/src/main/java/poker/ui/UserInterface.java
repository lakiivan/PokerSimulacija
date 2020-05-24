/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.ui;

import java.util.Scanner;
import poker.domain.Card;
import poker.domain.Hand;
import poker.logic.Calculate;
import poker.logic.Deal;
import poker.logic.VecaManja;
import poker.logic.WinningHands;

/**
 *
 * @author Ivan Local
 */
public class UserInterface {

    private Scanner scanner;
    private Calculate calculate;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.calculate = new Calculate(0);
    }

    public void start() {
        startDealer();
        startPlaying();
    }

    private void startDealer() {
        System.out.println("Welcome to game");
        System.out.println("Enter money credit");
        int credit = Integer.valueOf(scanner.nextLine());
        calculate.setBalance(credit);
        System.out.println("CREDIT - " + credit);
        //System.out.println();
    }

    private void startPlaying() {
        while (true) {
            System.out.println("****************************************************");
            System.out.println("");
            System.out.println("Current balance is " + calculate.getBalance());
            System.out.println("");
            System.out.println("****************************************************");
            System.out.println("Press Enter to Deal \"b\" to change bet  or q to quit");
            System.out.print("> ");
            String answer = scanner.nextLine();
            if (answer.equals("q") || calculate.getBalance() == 0) {
                System.out.println("Amount of credit for pay is " + calculate.getBalance());
                System.out.println("Thank you for playing with us");
                break;
            }
            if (answer.isEmpty()) {
                //call deal procedure prava ruka + zamena karata
                int winningAmount = calculateHandWinningAmount();
                //ukoliko je nesto dobijeno poziva se metoda za igranje veca manja
                if (winningAmount > 0) {
                    winningAmount = vecaManjaPlay(winningAmount);
                }
                //dobitak ukoliko postoji se dodaje na trenutni balance 
                System.out.println("Winning amount is " + winningAmount);
                this.calculate.addToBalance(winningAmount);
                System.out.println("Current balance is " + calculate.getBalance());

            } else if (answer.equals("b")) {
                System.out.println("Set new Bet");
                try {
                    int newBet = Integer.valueOf(scanner.nextLine());
                    if (calculate.isBetPossible(newBet)) {
                        this.calculate.setBet(newBet);
                    } else {
                        System.out.println("You did not enter number greater than 4 less then 1201 or less than balance");
                    }
                } catch (Exception e) {
                    System.out.println("You did not enter positive number");
                }
            } else {
                System.out.println("You did not entered valid command");
            }
        }
    }

    //helper metod za zamenu karata provrava da li je string input korektan 
    //da li sadrzi samo karaktere koji oznacavaju samo pozicije koje ce biti zamenjene
    private boolean validateReplaceInput(String input) {
        char[] characters = input.toCharArray();
        for (char ch : characters) {
            if (ch == 'q' || ch == 'w' || ch == 'e' || ch == 'r' || ch == 't') {

            } else {
                return false;
            }
        }
        return true;
    }

    int calculateHandWinningAmount() {
        calculate.makeBetPossible();
        calculate.makeFirstHand();
        System.out.println("To replace first card press q, second w, third e, fourth r, fifth t");
        String input = scanner.nextLine();
        //System.out.println("Input was " + this.validateReplaceInput(input));
        if (this.validateReplaceInput(input)) {
            calculate.replaceCards(input);
            //System.out.println(calculate.toString());
        }
        WinningHands wh = new WinningHands(this.calculate.getDeal().getHand(), this.calculate.getBet());
        return wh.calculateWinningHand();
    }

    private int vecaManjaPlay(int winningAmount) {
        //metodi su potrebni Deal klasa da bi se znalo sta je sve do sada deljeno
        //i amount koji je trenutni dobitak
        //otvara while petlju 
        while (true) {
            System.out.println("");
            System.out.println("Credit to play with - " + winningAmount);
            //postavlja pred igraca izbor da sacuva dobijeno ili da igra veca manja sa mogucnoscu da duplira dobitak ili ostane bez njega
            //igrac pomocu z pa ent igra vecu m pa ent igra manju q prestanak igre amount se dodaje na balance
            System.out.println("Veca \"z\" Manja \"m\" ili \"q\" za prekid");
            String inputVecaManja = scanner.nextLine();
            //ukoliko nece vise izlazi se iz petlje i donijena lova se dodaje na balance
            if (inputVecaManja.equals("q")) {
                return winningAmount;
            } else if (inputVecaManja.equals("z") || inputVecaManja.equals("m")) {
                boolean isVecaGuessed;
                //pozivanje metode za veca manja koja vraca boolean da li je igrano na vecu
                isVecaGuessed = isVecaGuessed(inputVecaManja);
                // poziva deal klasu da posalje novi radnom broj
                Card randomCard = this.calculate.getDeal().getRandomCard();
                //sa random brojem i trebuntim betom poziva Veca manja klasu koja govori da li je bio pogodak
                VecaManja vm = new VecaManja(isVecaGuessed, randomCard, winningAmount);
                //ukoliko je bio pogotak duplira se amount (to vec radi Veca manja klasa
                int currAmount = vm.calculateAmount();
                //ukoliko se dostigne max limit balance automat se blokira kraj igre sa porukom dobili sete max npr. 120000
                if (currAmount > this.calculate.getMaxAmount()) {
                    return currAmount;
                    // proveriti da li ima para da se igra jos, ako je winning amount 0 izlazi se sa 0
                } else {
                    winningAmount = currAmount;
                    if (winningAmount == 0) {
                        return 0;
                    }
                }
            } else {
                System.out.println("You did not entered valid command");
            }
        }
    }

    private boolean isVecaGuessed(String guess) {
        return guess.equals("z");
    }
}
