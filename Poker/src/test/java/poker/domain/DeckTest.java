/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.domain;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Ivan Local
 */
public class DeckTest {

    public DeckTest() {
    }

    /**
     * Test of getDeck method, of class Deck.
     */
    
            
    
    @Test
    public void testGetDeck() {
        System.out.println("*******************************");
        System.out.println("TESTOVI ZA DECK KLASU");
        System.out.println("");
        System.out.println("");
        System.out.println("getDeck");
        Deck instance = new Deck(4);
        int expResult = 56;
        int result = instance.deckSize();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getJokerNo method, of class Deck.
     */
    @Test
    public void testGetJokerNo() {
        System.out.println("getJokerNo");
        Deck instance = new Deck(4);
        int expResult = 4;
        int result = instance.getJokerNo();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deckSize method, of class Deck.
     */
    @Test
    public void testDeckSize() {
        System.out.println("deckSize");
        Deck instance = new Deck(0);
        int expResult = 52;
        int result = instance.deckSize();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDEfualtDeckConstructor() {
        System.out.println("testing constructor Deck()");
        Deck instance = new Deck();
        int expResult = 52;
        int result = instance.deckSize();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Deck.
     */
    //@Test
    //public void testToString() {
    //    System.out.println("toString");
    //    Deck instance = new Deck(4);
    //    String expResult = "PIK 2";
    //    String result = instance.getDeck().get(0).toString();
    //    assertEquals(expResult, result);
    //fail("The test case is a prototype.");
    //}
}
