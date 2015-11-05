package applicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.Card;
import application.Deck;

public class MainTest {

	@Test
	public void testCard() {
		Card a = new Card(10, "10", 2);
		Card b = new Card(5, "5", 2);
		Card c = new Card(10, "10", 3);
		Deck deck = new Deck(48, "frontimage2");
		assertEquals("Card back image must be Nackademin", a.getBackImage(), b.getBackImage());
		assertEquals("Card Value must be same on A and C", a.getValue(), c.getValue());
		assertTrue("Deck size must be 47 initially", deck.getDeckSize() == 48);
		deck.removeCardbyValue(2);
		assertTrue("Deck size must be 47 after removing 1 card (with value 2)", deck.getDeckSize() == 47);
		deck.removeCardbyValue(2);
		assertTrue("Deck size must be 46 after removing its pair card", deck.getDeckSize() == 46);
		deck.removeCardbyValue(2);
		assertTrue("Deck size must remain after attempting to remove a non-existant card", deck.getDeckSize() == 46);
	}

}
