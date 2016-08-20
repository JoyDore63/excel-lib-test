package org.joyfull.excel;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class allows the caller to build a list of card entities from rows of input data.
 */
public class Builder {
	private static final Logger logger = LoggerFactory.getLogger(Builder.class); 
	
	// Arbitrary initial size, this would be tuned in use.
	private static int INITIAL_LIST_SIZE = 5;
	
	private static String[][] testData = {
		{"name", "Name:", "name prompt", "10"},
		{"age", "Age:", "age prompt", "3"},
		{"amount", "Amount:", "amount prompt", "12"},
		{"foo", "foo", "1"},
		{"tax", "Tax:", "tax prompt", "8", "foo"}};
	
	/**
	 * Get an instance of the Builder class
	 */
	public static Builder getInstance() {
		return new Builder();
	}

	/**
	 * This method converts data in the form of 2d String array into a List of Card entities.
	 * Each row in the array must represent the data for a single Card (id, text, description, maximum text length).
	 * Rows having less than 4 columns are reported and skipped.
	 * Rows having more than 4 columns are processed, the additional columns are ignored.
	 * A report is created via log output.  The report shows: the total number input rows,
	 *                                                        the total number of cards created,
	 *                                                        an ERROR output for any invalid data row.
	 * 
	 * @param inputData			2d array of Card data
	 * 
	 * @return					List<Card> of Card entities
	 * 
	 */
	public List<Card> buildCardList(String[][] inputData) {
		List<Card> cardList = new ArrayList<Card>(INITIAL_LIST_SIZE);
		Card card;
		String[] row;
		
		if (inputData == null) {
			logger.error("INVALID INPUT - null data");
			return cardList;
		}
		
		logger.info("Total input rows = " + inputData.length);
		
		for (int i = 0; i < inputData.length; i++) {
			logger.info("Row: " + (i + 1));
			if (inputData[i].length < 4) {
				logger.error("INVALID INPUT - Data length is less than 4");
			}
			else {
				row = inputData[i];
				try {
					card = new Card(row[0], row[1], row[2], Integer.valueOf(row[3]));
					cardList.add(card);
					logger.info("Created Card: " + i + " " + card);
				}
				catch (CardException e){
					logger.error("ERROR: Caught card exception: " + e);
				}
				catch (NumberFormatException e) {
					logger.error("ERROR: Value of maxTextLen is invalid: " + e);
				}
			}
		}
		
		logger.info("Total cards built = " + cardList.size());
		
		return cardList;
	}
	
	/**
	 * Class main method allows for command line run/test of this class.
	 * If the first argument is "test", then internal test data is used having 5 rows, 3 valid
	 * If the first argument is not test, then args is treated as a single row to represent one card
	 * 
	 * @param args  <id> <text> <description> <max text length>
	 *                
	 */
	public static void main(String args[]) throws CardException {
		List<Card> cardList = null;
		String[][] cmdLineData = new String[1][0];
		
		Builder builder = Builder.getInstance();
		
		if (args.length == 0) {
			System.out.println("Invalid invocation, usage: Builder <id> <text> <description> <max text length>");
			System.out.println("Invalid invocation, alternative usage: Builder test"); 
			return;
		}
		
		// Use own test data
		if ("test".equalsIgnoreCase(args[0])) {
			cardList = builder.buildCardList(testData);
		}
		else {
			// Use command line arguments
			if (args.length < 4) {
				logger.error("Invalid number of arguments, 4 required");
			}
			else {
				cmdLineData[0] = args;
				cardList = builder.buildCardList(cmdLineData);
			}
		}
		
		// Report Cards obtained, if any
		if (cardList != null && cardList.size() > 0) {
			for (int i = 0; i < cardList.size(); i++) {
				logger.debug("Card:" + i + " " + cardList.get(i));
			}
		}
	}
}
