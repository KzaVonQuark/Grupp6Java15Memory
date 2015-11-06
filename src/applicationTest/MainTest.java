package applicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.Card;
import application.Deck;
import application.Main;

public class MainTest {

	@Test
	public void testCardFrontImage() {
		Card a = new Card(10, "2", 10);
		Card b = new Card(5, "2", 5);
		assertEquals("Card back image must be Nackademin", a.getFrontImage(), b.getFrontImage());
	}

	@Test
	public void testCardValue() {
		Card a = new Card(10, "10", 2);
		Card c = new Card(10, "10", 3);
		assertEquals("Card Value must be same on A and C", a.getValue(), c.getValue());
	}

	@Test
	public void testDeckSize() {
		Deck deck = new Deck(48, "frontimage2");
		assertTrue("Deck size must be 47 initially", deck.getDeckSize() == 48);
		deck.removeCardbyValue(2);
		assertTrue("Deck size must be 47 after removing 1 card (with value 2)", deck.getDeckSize() == 47);
		deck.removeCardbyValue(2);
		assertTrue("Deck size must be 46 after removing its pair card", deck.getDeckSize() == 46);
		deck.removeCardbyValue(2);
		assertTrue("Deck size must remain after attempting to remove a non-existant card", deck.getDeckSize() == 46);
	}

	@Test(timeout = 500)
	public void testMain() {
		// Det får inte ta längre än 500 millisekunder för att starta
		// programmet.
		Main main = new Main();
	}
}
