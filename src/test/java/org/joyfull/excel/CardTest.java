package org.joyfull.excel;

import static org.junit.Assert.*;

import org.joyfull.excel.Card;
import org.joyfull.excel.CardException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardTest {
	private static final Logger logger = LoggerFactory.getLogger(CardTest.class);

	@Test
	public void testEmptyDescription() {
		logger.info("testEmptyDescription");
		Card card = null;
		
		try {
			card = new Card("name", "Name", "", 8);
		}
		catch (CardException e) {
			fail("Caught exception" + e);
		}
		
		assertTrue(card != null);
		logger.debug("card:" + card);
	}

	@Test
	public void testNullDescription() {
		logger.info("testNullDescription");
		Card card = null;
		String desc = null;
		
		try {
			card = new Card("name", "Name", desc, 8);
		}
		catch (CardException e) {
			fail("Caught exception" + e);
		}
		
		assertTrue(card != null);
	}
	
	@Test
	public void testNoDescription() {
		logger.info("testNoDescription");
		Card card = null;
		
		try {
			card = new Card("name", "Name", 8);
		}
		catch (CardException e) {
			fail("Caught exception" + e);
		}
		
		assertTrue(card != null);
	}
	
	@Test
	public void testMaxTextLenTooSmall() {
		logger.info("testMaxTextLenTooSmall");
		Card card = null;
		
		try {
			card = new Card("name", "Name:", "name prompt", 1);
		}
		catch (CardException e) {
			logger.debug("caught exception:" + e);
		}
		
		assertTrue(card == null);
	}
	
	@Test
	public void testMaxTextLenTooBig() {
		logger.info("testMaxTextLenTooBig");
		Card card = null;
		
		try {
			card = new Card("name", "Name:", "name prompt", 81);
		}
		catch (CardException e) {
			logger.debug("caught exception:" + e);
		}
		
		assertTrue(card == null);
	}
	
	@Test
	public void testMaxTextLenEqualsMaximum() {
		logger.info("testMaxTextLenEqualsMaximum");
		Card card = null;
		
		try {
			card = new Card("name", "Name:", "name prompt", 80);
		}
		catch (CardException e) {
			fail("caught exception:" + e);
		}
		
		assertTrue(card != null);
	}
	
	@Test
	public void testTextExceedsMaxLength() {
		logger.info("testTextExceedsMaxLength");
		Card card = null;
		
		try {
			card = new Card("name", "Name:", "name prompt", 4);
		}
		catch (CardException e) {
			logger.debug("caught exception:" + e);
		}
		
		assertTrue(card == null);
	}
	
	@Test
	public void testTextIsNull() {
		logger.info("testTextIsNull");
		Card card = null;
		String text = null;
		
		try {
			card = new Card("name", text, "name prompt", 4);
		}
		catch (CardException e) {
			logger.debug("caught exception:" + e);
		}
		
		assertTrue(card == null);
	}
	
	@Test
	public void testTextIsTooSmall() {
		logger.info("testTextIsTooSmall");
		Card card = null;
		
		try {
			card = new Card("name", ":", "name prompt", 4);
		}
		catch (CardException e) {
			logger.debug("caught exception:" + e);
		}
		
		assertTrue(card == null);
	}
	
	@Test 
	public void testIdIsNull() {
		logger.info("testIdIsNull");
		
		Card card = null;
		String id = null;
		
		try {
			card = new Card(id, "Name:", "name prompt", 7);
		}
		catch (CardException e) {
			logger.debug("caught exception:" + e);
		}
	
		assertTrue(card == null);
	}
}
