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
import poker.domain.Znak;

/**
 *
 * @author Ivan Local
 */
public class VecaManjaTest {

    public VecaManjaTest() {
    }

    @Test
    public void testVecaCalculatedYes() {
        System.out.println("");
        System.out.println("*******************************");
        System.out.println("TESTOVI ZA VECA MANJA KLASU");
        System.out.println("");
        System.out.println("Veca Manja player gadjao vecu i bila veca");
        Card card = new Card(12, Znak.HERC);
        int amount = 5;
        boolean vecaPlayer = true;

        VecaManja vm = new VecaManja(vecaPlayer, card, 5);
        int expectedValue = 10;
        int calculatedAmount = vm.calculateAmount();
        assertEquals(expectedValue, calculatedAmount);
    }

    @Test
    public void testVecaCalculatedNo() {
        System.out.println("");
        System.out.println("Veca Manja player gadjao vecu, a bila manja");
        Card card = new Card(12, Znak.HERC);
        int amount = 5;
        boolean vecaPlayer = false;

        VecaManja vm = new VecaManja(vecaPlayer, card, 5);
        int expectedValue = 0;
        int calculatedAmount = vm.calculateAmount();
        assertEquals(expectedValue, calculatedAmount);
    }

    @Test
    public void testManjaCalculatedYes() {
        System.out.println("");
        System.out.println("Veca Manja player gadjao manju i bila manja");
        Card card = new Card(4, Znak.HERC);
        int amount = 5;
        boolean vecaPlayer = false;

        VecaManja vm = new VecaManja(vecaPlayer, card, amount);
        int expectedValue = 10;
        int calculatedAmount = vm.calculateAmount();
        assertEquals(expectedValue, calculatedAmount);
    }

    @Test
    public void testManjaCalculatedNo() {
        System.out.println("");
        System.out.println("Veca Manja player gadjao manju, a bila veca");
        Card card = new Card(12, Znak.HERC);
        int amount = 5;
        boolean vecaPlayer = false;

        VecaManja vm = new VecaManja(vecaPlayer, card, amount);
        int expectedValue = 0;
        int calculatedAmount = vm.calculateAmount();
        assertEquals(expectedValue, calculatedAmount);
    }

    @Test
    public void testManjaSeven() {
        System.out.println("");
        System.out.println("Veca Manja player gadjao manju, a bila sedmica");
        Card card = new Card(7, Znak.HERC);
        int amount = 5;
        boolean vecaPlayer = false;

        VecaManja vm = new VecaManja(vecaPlayer, card, amount);
        int expectedValue = 5;
        int calculatedAmount = vm.calculateAmount();
        assertEquals(expectedValue, calculatedAmount);
    }

    @Test
    public void testVecaSeven() {
        System.out.println("");
        System.out.println("Veca Manja player gadjao vecu, a bila sedmica");
        Card card = new Card(7, Znak.HERC);
        int amount = 5;
        boolean vecaPlayer = true;

        VecaManja vm = new VecaManja(vecaPlayer, card, amount);
        int expectedValue = 5;
        int calculatedAmount = vm.calculateAmount();
        assertEquals(expectedValue, calculatedAmount);
    }

}
