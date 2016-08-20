package org.joyfull.excel;

import java.io.Serializable;

/**
 * Simple object to represent a card.
 */
public class Card implements Serializable {

	static final long serialVersionUID = 1L;
	
	// Set what seem to be reasonable limits in the text length, these would be adjusted after feedback from use
	private static final int ABS_MIN_TEXT_LENGTH = 2;
	private static final int ABS_MAX_TEXT_LENGTH = 80;
	
	private String id;
	private String text;
	private String description;
	private int maxTextLen;
	
	/**
	 * Main Constructor
	 * 
	 * @param 	id			Key representing the text
	 * @param	text		Actual string representation in the card-specific language
	 * @param	description	Description/purpose of text - may be blank
	 * @param 	maxTextLen	Maximum length of text allowed, valid range ABS_MIN_TEXT_LENGTH to ABS_MAX_TEXT_LENGTH
	 * 
	 * @throws	CardException	If input data is invalid.
	 */
	public Card(String id, String text, String description, int maxTextLen) throws CardException {
		
		if (id == null) {
			throw new CardException("ERROR: id is null");
		}
		
		if(maxTextLen < ABS_MIN_TEXT_LENGTH  || maxTextLen > ABS_MAX_TEXT_LENGTH) {
			throw new CardException("ERROR: max text length invalid (" + maxTextLen + ")");
		}
		
		if (text == null || text.length() < ABS_MIN_TEXT_LENGTH) {
			throw new CardException("ERROR: text length is less than minimum (" + ABS_MIN_TEXT_LENGTH + ")");
		}
		
		if (text.length() > maxTextLen) {
			throw new CardException("ERROR: text length exceeds max (" + maxTextLen + ")");
		}
		
		if (description == null) {
			description = "";
		}
		this.id = id;
		this.text = text;
		this.description = description;
		this.maxTextLen = maxTextLen;
	}
	
	/**
	 * Alternative constructor, description is optional so this sets an empy value
	 * 
	 * @param	id				Key representing the text
	 * @param	text			Actual string representation in the card-specific language
	 * @param	maxTextLen		Maximum length of text allowed
	 * 
	 * @throws	CardException	If input data is invalid.
	 */
	public Card(String id, String text, int maxTextLen) throws CardException {
		this(id, text, "", maxTextLen);
	}
	
	/**
	 * @return	String representation of Card
	 */
	public String toString() {
		StringBuilder str = new StringBuilder(500);
		str.append("id=" + this.id);
		str.append(", text=" + this.text);
		str.append(", description=" + this.description);
		str.append(", maxTextLen=" + maxTextLen);
		
		return str.toString();
	}
	
	/**
	 * 
	 * @param card
	 * @return		true if this card matches input card, otherwise false
	 */
	public boolean equals(Card card) {
		if (this.id.equals(card.id) && this.text.equals(card.text) 
				&& this.description.equals(card.description) && this.maxTextLen == card.maxTextLen)	{
			return true;
		}
		
		return false;
	}
}
