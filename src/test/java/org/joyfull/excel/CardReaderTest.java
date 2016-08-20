package org.joyfull.excel;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.poi.ss.SpreadsheetVersion;
import org.joyfull.excel.Card;
import org.joyfull.excel.CardReader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardReaderTest {
	private static final Logger logger = LoggerFactory.getLogger(CardReaderTest.class);
	
	@Test
	public void testEXCEL2007() {
		logger.info("testEXCEL2007");
		
		List<Card> cardList = null;
		
		CardReader reader = CardReader.getInstance();
		
		try {
			cardList = reader.readExcel("cards.xlsx", SpreadsheetVersion.EXCEL2007);
			for(Card card: cardList) {
				logger.info("card in list:" + card);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("caught expection: " + e);
		}
		
		assertTrue(cardList != null && cardList.size() == 3);
	}

	@Test
	public void testEXCEL97() {
		logger.info("testEXCEL97");
		
		List<Card> cardList = null;
		
		CardReader reader = CardReader.getInstance();
		
		try {
			cardList= reader.readExcel("cards.xls", SpreadsheetVersion.EXCEL97);
			for(Card card: cardList) {
				logger.info("card in list:" + card);
			}
		}
		catch (Exception e) {
			fail("caught expection: " + e);
		}
		
		assertTrue(cardList != null && cardList.size() == 2);
	}
	
	@Test
	public void testEmptyEXCEL97() {
		logger.info("testEmptyEXCEL97");
		
		List<Card> cardList = null;
		
		CardReader reader = CardReader.getInstance();
		
		try {
			cardList= reader.readExcel("empty.xls", SpreadsheetVersion.EXCEL97);
			for(Card card: cardList) {
				logger.info("card in list:" + card);
			}
		}
		catch (Exception e) {
			fail("caught expection: " + e);
		}
		
		assertTrue(cardList != null && cardList.size() == 0);
	}
}
