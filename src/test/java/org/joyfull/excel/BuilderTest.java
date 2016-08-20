package org.joyfull.excel;

import static org.junit.Assert.*;

import java.util.List;

import org.joyfull.excel.Builder;
import org.joyfull.excel.Card;
import org.joyfull.excel.CardException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BuilderTest.class);
	
	@Test
	public void testNoMaxLen() {
		logger.info("TEST: testNoMaxLen");
		
		String[][] inputData = {{"fish", "Fish:", "fish prompt"}};
		
		List<Card> cardList = null;
		
		Builder builder = Builder.getInstance();
		
		cardList = builder.buildCardList(inputData);
		
		assertTrue(cardList.size() == 0);
	}
	
	@Test
	public void testInvalidMaxLen() {
		logger.info("TEST: testInvalidMaxLen");
		
		String[][] inputData = {{"fish", "Fish:","fish prompt", "six"}};
		
		List<Card> cardList = null;
				
		Builder builder = Builder.getInstance();
		
		cardList = builder.buildCardList(inputData);
		
		assertTrue(cardList.size() == 0);
	}
	
	@Test
	public void testLongText() {
		logger.info("TEST: testLongText");
		
		String[][] inputData = {{"name", "Name:", "name prompt", "4"}};
		
		List<Card> cardList = null;
		
		Builder builder = Builder.getInstance();
		
		cardList = builder.buildCardList(inputData);
		
		assertTrue(cardList.size() == 0);
	}

	@Test
	public void testNull() {
		logger.info("TEST: testNull");
		
		List<Card> cardList = null;
		
		Builder builder = Builder.getInstance();
		
		cardList = builder.buildCardList(null);
		
		assertTrue(cardList.size() == 0);
	}
	
	@Test
	public void testvalidOne() {
		logger.info("TEST: testvalidOne");
		
		String[][] inputData = {{"date", "Date:", "date prompt", "6"}};
		
		List<Card> cardList = null;
		Card expected = null;
		
		try {
			expected = new Card("date", "Date:", "date prompt", 6);

			Builder builder = Builder.getInstance();

			cardList = builder.buildCardList(inputData);
		}
		catch (CardException e) {
			fail("caught exception" + e);
		}
		
		logger.debug("expected: " + expected);
		logger.debug("got: " + cardList.get(0));
		
		assertTrue(cardList.size() == 1 && cardList.get(0).equals(expected));
	}

	@Test
	public void testvalidEmptyDesc() {
		logger.info("TEST: testvalidEmptyDesc");
		
		String[][] inputData = {{"date", "Date:", "", "6"}};
		
		List<Card> cardList = null;
		Card expected = null;
		
		try {
			expected = new Card("date", "Date:", "", 6);

			Builder builder = Builder.getInstance();

			cardList = builder.buildCardList(inputData);
		}
		catch (CardException e) {
			fail("caught exception" + e);
		}
		
		logger.debug("expected: " + expected);
		logger.debug("got: " + cardList.get(0));
		
		assertTrue(cardList.size() == 1 && cardList.get(0).equals(expected));
	}
}
