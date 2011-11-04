package VEW.UIComponents;

import java.util.ArrayList;

public class SyntaxHighlighter {

	/* Colours used by default:
	 * Comments ("green") = 33CC00
	 * Keywords ("blue") = 3333FF
	 * Incorrect Syntax ("red") = FF0000
	 * Functions ("purple") = 660066
	 * Rule Names ("light blue") = 3399CC
	 * */
	
	// An array of all keywords to be recognised
	private String[] keywords;
	private String[] functions;
	private ArrayList<Integer> flagged_lines;
	
	// The colours to be used for syntax highlighting is hex form
	private String keyword_colour = "3333FF";
	private String comment_colour = "33CC00";
	private String incorrect_colour = "FF0000";
	private String function_colour = "660066";
	private String rule_colour = "3399CC";
	
	public SyntaxHighlighter(String [] _keywords,String[] _functions) {
		// Set up a new SyntaxPane given a list of keywords and functions
		this.keywords = _keywords;
		this.functions = _functions;
		this.flagged_lines = new ArrayList<Integer>();
	}
	
	public SyntaxHighlighter() {
		// Set up a new SyntaxPane which uses default BACON keywords and functions
		String[] keys = {"if","then","else","with"};
		this.keywords = keys;
		String[] funs = {"uptake","release","ingest","pchange","change","divide","integrate",
				"create","densityAt","depthForFI","depthForVI","fullIrradAt","salinityAt",
				"temperatureAt","UVIrradAt","varhist"};
		this.functions = funs;
		this.flagged_lines = new ArrayList<Integer>();
	}
	
	public String getKeywordColour() {
		return this.keyword_colour;
	}
	
	public void setKeywordColour(String col) {
		// Can set the color used for keywords. Onus is on caller to
		// ensure the string is a recognised color
		this.keyword_colour = col;
	}
	
	public String getCommentColour() {
		return this.comment_colour;
	}
	
	public void setCommentColour(String col) {
		// Can set the color used for comments. Onus is on caller to
		// ensure the string is a recognised color
		this.comment_colour = col;
	}
	
	public String getIncorrectColour() {
		return this.incorrect_colour;
	}
	
	public void setIncorrentColour(String col) {
		// Can set the color used for icorrect syntax. Onus is on caller to
		// ensure the string is a recognised color
		this.incorrect_colour = col;
	}
	
	public String getFunctionColour() {
		return this.function_colour;
	}
	
	public void setFunctionColour(String col) {
		// Can set the color used for function names. Onus is on caller to
		// ensure the string is a recognised color
		this.function_colour = col;
	}
	
	public String getRuleNameColour() {
		return this.rule_colour;
	}
	
	public void setRuleNameColour(String col) {
		// Can set the color used for rule names. Onus is on caller to
		// ensure the string is a recognised color
		this.rule_colour = col;
	}
	
	// Do all the syntax highlighting
	public String highlight(String _text) {
		_text = getPreText(_text);
		String text = remove_highlight_tags(_text);
		// Highlight all keywords
		for (int i = 0; i < keywords.length; i++) {
			text = text.replaceAll(keywords[i], 
					"<font color=#" + keyword_colour + ">" + keywords[i] + "</font>");
		}
		// Highlight all function names
		for (int i = 0; i < functions.length; i++) {
			text = text.replaceAll(functions[i], 
					"<font color=#" + function_colour + ">" + functions[i] + "</font>");
		}
		// Highlight all rule names
		text = text.replaceAll(":", "</font>:");
		char[] chars = text.toCharArray();
		String partial_text = "";
		boolean colon_found = false;
		for (int i = chars.length - 1; i >= 0; i--) {
			if (chars[i] == ':') {
				colon_found = true;
			} else if (colon_found && chars[i] == '\n') {
				colon_found = false;
				partial_text += "%";
			}
			partial_text += chars[i];
		}
		if (colon_found) {
			partial_text += "%";
		}
		partial_text = new StringBuffer(partial_text).reverse().toString();
		partial_text = partial_text.replaceAll("%","<font color=#" + rule_colour + ">");
		/*
		boolean in_quotes = false;
		for (int i = 0; i < chars.length; i++) {
			if (!in_quotes && (i < chars.length - 2) && chars[i] == '&' && chars[i+1] == 'q') {
				in_quotes = true;
				partial_text += "<font color=#" + rule_colour + ">\"";
				i += 5;
			} else if (in_quotes && (i < chars.length - 2) && chars[i] == '&' && chars[i+1] == 'q') {
				in_quotes = false;
				partial_text += "\"</font>";
				i += 5;
			} else {
				partial_text += chars[i];
			}
		}*/
		chars = partial_text.toCharArray();
		String final_text = "";
		// Highlight all comments
		//String final_text = "";
		boolean in_comment = false;
		for (int i = 0; i < chars.length; i++) {
			if (i < (chars.length - 1) && chars[i] == '/' && chars[i+1] == '/') {
				in_comment = true;
				final_text += "<font color=#" + comment_colour + ">/";
			} else if (in_comment && chars[i] == '\n') {
				in_comment = false;
				final_text += "</font>\n";
			} else {
				final_text += chars[i];
			}
		}
		// Flag all error lines
		for(int flag : flagged_lines) {
			final_text = highlight_error(flag,final_text);
		}
		final_text = remove_inner_tags(final_text);
		return("<html><head></head><PRE>\n" + final_text + "</PRE></html>");
	}
	
	// Flags a line of text containing an error
	private String highlight_error(int line, String text) {
		if (line <= 0) {
			return text;
		}
		String flagged_text = "";
		line--;
		boolean in_pre = false;
		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((i < chars.length - 5) && chars[i] == '<' && chars[i+1] == 'p'
				&& chars[i+2] == 'r' && chars[i+3] == 'e' && chars[i+4] == '>') {
				in_pre = true;
				i += 5;
				flagged_text += "<pre>";
			} else if (in_pre && line == -1 && chars[i] == '\n') {
				in_pre = false;
				flagged_text += "</font>";
			} else if (in_pre && line == 0) {
				flagged_text += "<font color=#" + incorrect_colour + ">";
				line--;
			} else if (in_pre && chars[i] == '\n') {
				line--;
			}
			flagged_text += chars[i];
		}
		return flagged_text;
	}
	
	// Add a flag to the list of error lines
	public void flag_line(int i) {
		this.flagged_lines.add(i);
	}
	
	// Clear the flag list
	public void clear_flags() {
		this.flagged_lines.clear();
	}
	
	// Remove all <font> tags inside other <font> tags
	private String remove_inner_tags(String tag_text) {
		String final_text = "";
		char[] chars = tag_text.toCharArray();
		boolean in_font_tag = false;
		boolean ignore = false;
		int inner_tags = 0;
		for (int i = 0; i < chars.length; i++) {
			if (!in_font_tag && (i < chars.length - 2) && chars[i] == '<' && chars[i+1] == 'f') {
				// This is the start of a '<font>' tag
				in_font_tag = true;
				final_text += chars[i];
			} else if (in_font_tag && (i < chars.length - 2) && chars[i] == '<' && chars[i+1] == 'f') {
				// Ignore this tag
				ignore = true;
				inner_tags++;
			} else if (in_font_tag && (i < chars.length - 3) && chars[i] == '<' && chars[i+1] == '/'
				 && chars[i+2] == 'f') {
				// The tag has ended
				if (inner_tags == 0) {
					in_font_tag = false;
					final_text += chars[i];
				} else {
					ignore = true;
					inner_tags--;
				}
			} else if (chars[i] == '>') {
				if (!ignore)
					final_text += chars[i];
				ignore = false;
			} else if (!ignore) {
				final_text += chars[i];
			}
		}
		return final_text;
	}
	
	// Remove all syntax highlighting tags from the text
	private String remove_highlight_tags(String tag_text) {
		String text = "";
		char[] chars = tag_text.toCharArray();
		boolean in_tag = false;
		for (int i = 0; i < chars.length; i++) {
			if ((i < chars.length - 2) && chars[i] == '<' && (chars[i+1] == 'f'
				 || chars[i+1] == '/' && chars[i+2] == 'f')) {
				// This is the start of a '<font>' or '</font>' tag, so remove it
				in_tag = true;
			} else if (!in_tag) {
				// Add the char to the text string
				text += chars[i];
			} else if (chars[i] == '>') {
				// The tag has ended
				in_tag = false;
			}
		}	
		return text;
	}
	
	private String remove_all_tags(String tag_text) {
		String text = "";
		char[] chars = tag_text.toCharArray();
		boolean in_tag = false;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '<') {
				// This is the start of a tag, so remove it
				in_tag = true;
			} else if (!in_tag) {
				// Add the char to the text string
				text += chars[i];
			} else if (chars[i] == '>') {
				// The tag has ended
				in_tag = false;
			}
		}	
		return text;
	}
	
	// Return the text without any HTML tags (for parsing, etc.)
	public String getPlainText(String text) {
		//String plain_text = remove_all_tags(text);
		String plain_text = getPreText(text);
		plain_text = remove_highlight_tags(plain_text);
		plain_text = plain_text.replaceAll("&quot;", "\"");
		plain_text = plain_text.replaceAll("&lt;", "<");
		plain_text = plain_text.replaceAll("&gt;", ">");
		return plain_text;
	}

	private String getPreText(String text) {
		boolean in_pre = false;
		char[] chars = text.toCharArray();
		String plain_text = "";
		for (int i = 0; i < chars.length; i++) {
			if ((i < chars.length - 2) && chars[i] == '<' && chars[i+1] == 'p') {
				in_pre = true;
				i += 4;
			} else if (in_pre && (i < chars.length - 3) 
					&& chars[i] == '<' && chars[i+1] == '/' && chars[i+2] == 'p') {
				in_pre = false;
			} else if (in_pre) {
				plain_text += chars[i];
			}
		}
		return plain_text;
	}


}
