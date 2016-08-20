package org.joyfull.excel;

import java.util.List;


import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class allows the user to read data from excel spreadsheets into categorised card entity data.
 * 
 */
public class CategorisedCardReader extends CardReader implements Reader {

private static final Logger logger = LoggerFactory.getLogger(CategorisedCardReader.class);
	
	public static CategorisedCardReader getInstance() {
		return new CategorisedCardReader();
	}
	
	CategorisedCardReader(){
		this.MIN_ROW_LEN = 4;
	}

	/**
	 * Create a card from the row data
	 */
	@Override
	protected Card createCardFromRow(Row row) throws CardException {
		
		// Get the row data and leave the validation to the Card class
		String id = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		String cat = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		String text = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		String description = row.getCell(32, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		int maxTextLen = (int) row.getCell(4, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
		
		return new CategorisedCard(id, text, description, maxTextLen, CardCategory.valueOf(cat.toUpperCase()));
	}
	
	/**
	 * Main method allows for command line run of this class
	 * 
	 * @param   args  filename SpreadsheetVersion.EXCEL97 | SpreadsheetVersion.EXCEL2007 [-c]
	 */
	public static void main(String args[]) {
		List<Card> cardList;
		Reader reader;
		
		if (args.length < 2) {
			System.out.println("Invalid invocation, usage: CategorisedCardReader <filename> [-c]" + SpreadsheetVersion.EXCEL97 + " | " + SpreadsheetVersion.EXCEL2007);
			return;
		}
		
		String filename = args[0];
		String version = args[1];
		
		// Look for -c arg, for category
		if (args.length == 3 && args[2].equalsIgnoreCase("-c")) {
			reader = CategorisedCardReader.getInstance();
		}
		else {
			reader = CardReader.getInstance();
		}
		
		try {
			if (version.equals(SpreadsheetVersion.EXCEL97.toString())) {
				logger.info("Format xls");
				cardList = reader.readExcel(filename, SpreadsheetVersion.EXCEL97);
				if (cardList != null) {
					for(Card card: cardList) {
						logger.info("card in list:" + card);
					}
				}
			}
			else if (version.equals(SpreadsheetVersion.EXCEL2007.toString())) {
				logger.info("Format xlsx");
				cardList = reader.readExcel(filename, SpreadsheetVersion.EXCEL2007);
				if (cardList != null) {
					for(Card card: cardList) {
						logger.info("card in list:" + card);
					}
				}
			}
			else {
				System.out.println("Invalid version, usage: CategorisedCardReader <filename> [-c] " + SpreadsheetVersion.EXCEL97 + " | " + SpreadsheetVersion.EXCEL2007);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
