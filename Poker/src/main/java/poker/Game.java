/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.Scanner;
import poker.GUI.GUI;
import poker.domain.Deck;
import poker.domain.Hand;
import poker.logic.Deal;
import poker.ui.UserInterface;

/**
 *
 * @author Ivan Local
 */
public class Game {

    public static void main(String[] args) {
        //ovaj blok coda radi za igranje pokera iz console
        //System.out.println("Welcome to my first game");
        //System.out.println("");
        //Scanner scanner = new Scanner(System.in);
        //UserInterface ui = new UserInterface(scanner);
        //ui.start();
        
        //ovo je blok za pozivanje GUI Pokera
        GUI gui = new GUI();
        gui.run();
        //new comment added
        //gui pravljen iz buildera
        
    }
}
