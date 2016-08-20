package org.joyfull.excel;

/**
 * Extended Card object to represent a card that has a category.
 */
public class CategorisedCard extends Card {
	
	static final long serialVersionUID = 2L;
	
	private CardCategory category;
	
	/**
	 * Main Constructor
	 * 
	 * @param 	id			Key representing the text
	 * @param	text		Actual string representation in the card-specific language
	 * @param	description	Description/purpose of text - may be blank
	 * @param 	maxTextLen	Maximum length of text allowed, valid range ABS_MIN_TEXT_LENGTH to ABS_MAX_TEXT_LENGTH
	 * @param	category	Valid card category - CardCategory value
	 * 
	 * @throws	CardException	If input data is invalid.
	 */
	public CategorisedCard(String id, String text, String description, int maxTextLen, CardCategory category) throws CardException {
			
		super(id, text, description, maxTextLen);
		
		this.category = category;
	}
	
	public String toString(){
		String cardString = super.toString();
		StringBuilder str = new StringBuilder(500);
		str.append(cardString);
		str.append(", category=" + this.category);
		
		return str.toString();
	}

}
