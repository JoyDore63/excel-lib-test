package org.joyfull.excel;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.SpreadsheetVersion;

/**
 * Interface for excel reading 
 *
 */
public interface Reader {
	public List<Card> readExcel(String filename, SpreadsheetVersion version) throws ReaderException, IOException;
}
