/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.logic;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import poker.domain.Card;
import poker.domain.Hand;
import poker.domain.Znak;

/**
 *
 * @author Ivan Local
 */
public class WinningHandsTest {

    public WinningHandsTest() {
    }

    /**
     * Test of calculateWinningHand method, of class WinningHands.
     */
    @Test
    public void testCalculateWinningHandPoker() {
        System.out.println("");
        System.out.println("*******************************");
        System.out.println("TESTOVI ZA WINNING HAND KLASU");
        System.out.println("");

        //Deal deal = new Deal();
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);

        System.out.println("calculateWinningHand for poker 5-ica");
        int expResult = 125;
        assertEquals(expResult, wh.calculateWinningHand());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testJackOrHigherYES() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(12, Znak.TREF));
        handCards.add(new Card(3, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Jack or higher YES");
        int expResult = 5;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testJackOrHigherNoNista() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(2, Znak.HERC));
        handCards.add(new Card(3, Znak.TREF));
        handCards.add(new Card(4, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Jack or higher NO");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testJackORHigherNoPoker() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Jack or higher NO Pker 5ica");
        int expResult = 125;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testTwoPairsYes() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(4, Znak.KARO));
        handCards.add(new Card(8, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for two pairs YES");
        int expResult = 10;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testTwoPairsNo() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(2, Znak.HERC));
        handCards.add(new Card(6, Znak.TREF));
        handCards.add(new Card(7, Znak.KARO));
        handCards.add(new Card(9, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for two Pairs NO");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testTwoPairsNoPoker() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for two Pairs NO Poker");
        int expResult = 125;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testThreeOfKindYes() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(6, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Three of Kind YES");
        int expResult = 15;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testThreeOfKindNo() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(11, Znak.HERC));
        handCards.add(new Card(6, Znak.TREF));
        handCards.add(new Card(7, Znak.KARO));
        handCards.add(new Card(9, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for for Three of Kind NO");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testThreeOfKindNoFull() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(8, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for for Three of Kind NO Full");
        int expResult = 45;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFullHouseYes() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(8, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Full House YES");
        int expResult = 45;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFullHouseNoThreeOfKind() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(2, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Full House NO Triling");
        int expResult = 15;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFullHouseNoPoker() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Full House NO Poker");
        int expResult = 125;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFlushRoyalYes() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(10, Znak.HERC));
        handCards.add(new Card(11, Znak.HERC));
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(13, Znak.HERC));
        handCards.add(new Card(14, Znak.HERC));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Flush Royal YES");
        int expResult = 4000;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFlushRoyalYNoStraightFlush() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(10, Znak.HERC));
        handCards.add(new Card(11, Znak.HERC));
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(13, Znak.HERC));
        handCards.add(new Card(9, Znak.HERC));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Flush Royal NO Straight Flush");
        int expResult = 250;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFlushRoyalNo() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(2, Znak.TREF));
        handCards.add(new Card(3, Znak.KARO));
        handCards.add(new Card(4, Znak.PIK));
        handCards.add(new Card(7, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Flush Royal No");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testStraightFlushYes() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(10, Znak.HERC));
        handCards.add(new Card(11, Znak.HERC));
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(13, Znak.HERC));
        handCards.add(new Card(9, Znak.HERC));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Straight Flush YES ");
        int expResult = 250;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testStraightFlushNo() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(5, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Straight Flush NO");
        int expResult = 125;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testStraightFlushNoFlush() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(10, Znak.HERC));
        handCards.add(new Card(7, Znak.HERC));
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(13, Znak.HERC));
        handCards.add(new Card(9, Znak.HERC));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Straight Flush NO Flush");
        int expResult = 30;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testStraightYes() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(6, Znak.TREF));
        handCards.add(new Card(7, Znak.KARO));
        handCards.add(new Card(4, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Straight YES");
        int expResult = 20;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testStraightNoStraightFlush() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(10, Znak.HERC));
        handCards.add(new Card(7, Znak.HERC));
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(13, Znak.HERC));
        handCards.add(new Card(9, Znak.HERC));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for for Straight NO Straight Flush");
        int expResult = 30;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testStraightNo() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(2, Znak.HERC));
        handCards.add(new Card(10, Znak.TREF));
        handCards.add(new Card(12, Znak.KARO));
        handCards.add(new Card(5, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Straight NO");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFlushYes() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(10, Znak.HERC));
        handCards.add(new Card(7, Znak.HERC));
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(13, Znak.HERC));
        handCards.add(new Card(9, Znak.HERC));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Flush Yes");
        int expResult = 30;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFlushNo() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(11, Znak.TREF));
        handCards.add(new Card(13, Znak.KARO));
        handCards.add(new Card(14, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Flush NO");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testFlushNoStraightFlush() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(10, Znak.HERC));
        handCards.add(new Card(7, Znak.HERC));
        handCards.add(new Card(12, Znak.HERC));
        handCards.add(new Card(13, Znak.HERC));
        handCards.add(new Card(9, Znak.HERC));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for NO Straight Flush");
        int expResult = 30;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testNothing() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(13, Znak.TREF));
        handCards.add(new Card(11, Znak.KARO));
        handCards.add(new Card(3, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Nothing");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }

    @Test
    public void testNothingSmallPair() {
        System.out.println("");
        ArrayList<Card> handCards = new ArrayList<>();
        handCards.add(new Card(5, Znak.HERC));
        handCards.add(new Card(5, Znak.TREF));
        handCards.add(new Card(7, Znak.KARO));
        handCards.add(new Card(9, Znak.PIK));
        handCards.add(new Card(8, Znak.TREF));
        Hand hand = new Hand(handCards);
        WinningHands wh = new WinningHands(hand, 5);
        System.out.println("calculateWinningHand for Nothing i fyou get small pair");
        int expResult = 0;
        assertEquals(expResult, wh.calculateWinningHand());
    }
}
