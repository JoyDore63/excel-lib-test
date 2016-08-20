package org.joyfull.excel;

import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

/**
 * Class allows the user to read data from excel spreadsheets into card entity data.
 * 
 */
public class CardReader implements Reader {
	private static final Logger logger = LoggerFactory.getLogger(CardReader.class);
	
	private static int INITAL_LIST_SIZE = 10;
	protected int MIN_ROW_LEN;
	
	public static CardReader getInstance() {
		return new CardReader();
	}
	
	CardReader(){
		this.MIN_ROW_LEN = 3;
	}
	
	/**
	 * Reads card data from excel spreadsheet.
	 * A report is created via log output.  The report shows: the total number input rows,
	 *                                                        the total number of cards created,
	 *                                                        an ERROR output for any invalid data row.
	 * 
	 * @param  filename  Input file
	 * @param  version   Spreadsheet type, supported versions SpreadsheetVersion.EXCEL97, SpreadsheetVersion.EXCEL2007
	 * 
	 * @return           A List<Card> of Card entities
	 * 
	 */
	public List<Card> readExcel(String filename, SpreadsheetVersion version) throws ReaderException, IOException {
		List<Card> cardList = null;
		FileInputStream is = null;
		Workbook wb = null;
		
		try {
			is = new FileInputStream(filename);
			if (version == SpreadsheetVersion.EXCEL97) {
				wb = new HSSFWorkbook(is);
				logger.info("HSSFWorkbook");
			}
			else if (version == SpreadsheetVersion.EXCEL2007){
				wb = new XSSFWorkbook(is);
				logger.info("XSSFWorkbook");
			}
			else {
				logger.error("UNSUPPORTED SPREADSHEET VERSION:" + version);
				throw new ReaderException("UNSUPPORTED SPREADSHEET VERSION:" + version);
			}
			logger.info("number of sheets = " + wb.getNumberOfSheets());
			cardList = getCards(wb);
		}
		finally {
			if (is != null) {
				is.close();
			}
			if (wb != null) {
				wb.close();
			}
		}
		return cardList;
	}
	
	/**
	 * Read the first sheet of the workbook and convert any rows with 4 or more cells to Card entities
	 */
	private List<Card> getCards(Workbook wb) {
		List<Card> cardList = new ArrayList<Card>(INITAL_LIST_SIZE);
		
		// In this version we only expect one sheet and ignore any others
		if (wb.getNumberOfSheets() > 0) {
			Sheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			logger.info("number of rows = " + rowCount);
			
			// Each row in the sheet corresponds to one card
			for (int i = 0; i < rowCount; i++) {
				logger.info("Row " + (i + 1));
				Row row = sheet.getRow(i);
				int cellCount = row.getPhysicalNumberOfCells();
				logger.debug("cell count = " + cellCount);
				
				// Expect a minimum of cells in the row, additional cells are ignored
				if (cellCount >= MIN_ROW_LEN) {
					try {
						Card card = createCardFromRow(row);
						logger.info("created card:" + card);
						cardList.add(card);
					}
					catch (CardException e) {
						logger.error("ERROR: Caught card exception: " + e);
					}
				}
			}
		}
		
		return cardList;
	}
	
	/**
	 * Create a card from the row data
	 */
	protected Card createCardFromRow(Row row) throws CardException {
		
		// Get the row data and leave the validation to the Card class
		String id = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		String text = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		String description = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		int maxTextLen = (int) row.getCell(3, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
		
		return new Card(id, text, description, maxTextLen);
	}
	
	/**
	 * Main method allows for command line run of this class
	 * 
	 * @param   args  filename SpreadsheetVersion.EXCEL97 | SpreadsheetVersion.EXCEL2007
	 */
	public static void main(String args[]) {
		List<Card> cardList;
		
		if (args.length < 2) {
			System.out.println("Invalid invocation, usage: Reader <filename> " + SpreadsheetVersion.EXCEL97 + " | " + SpreadsheetVersion.EXCEL2007);
			return;
		}
		
		String filename = args[0];
		String version = args[1];
		
		CardReader reader = CardReader.getInstance();
		
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
				System.out.println("Invalid version, usage: Reader <filename> " + SpreadsheetVersion.EXCEL97 + " | " + SpreadsheetVersion.EXCEL2007);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
