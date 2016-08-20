package org.joyfull.excel;

import static org.junit.Assert.*;

import org.joyfull.excel.CardCategory;
import org.joyfull.excel.CardException;
import org.joyfull.excel.CategorisedCard;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategorisedCardTest {

	private static final Logger logger = LoggerFactory.getLogger(CategorisedCardTest.class);
	
	@Test
	public void testCategoriseCard() {
		CategorisedCard cCard = null;
		try {
			cCard = new CategorisedCard("fish", "fish:", "fish text", 8, CardCategory.DEFAULT);
		}
		catch (CardException e) {
			fail("Caught exception" + e);
		}
		
		
		assertTrue(cCard != null);
		logger.debug("Categorised Card =" + cCard);
	}
}
