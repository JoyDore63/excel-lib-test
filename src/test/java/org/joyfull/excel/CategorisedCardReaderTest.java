package org.joyfull.excel;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.poi.ss.SpreadsheetVersion;
import org.joyfull.excel.Card;
import org.joyfull.excel.CategorisedCardReader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CategorisedCardReaderTest {

	private static final Logger logger = LoggerFactory.getLogger(CategorisedCardReaderTest.class);
	
	@Test
	public void testEXCEL2007() {
		logger.info("testEXCEL2007");
		
		List<Card> cardList = null;
		
		CategorisedCardReader reader = CategorisedCardReader.getInstance();
		
		try {
			cardList = reader.readExcel("categorised_cards.xlsx", SpreadsheetVersion.EXCEL2007);
			for(Card card: cardList) {
				logger.info("card in list:" + card);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("caught expection: " + e);
		}
		
		assertTrue(cardList != null && cardList.size() == 4);
	}
}
