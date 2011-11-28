package VEW.Planktonica2.UIComponents;

import java.util.ArrayList;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.ASTNodes.TreeWalkerException;
import VEW.XMLCompiler.ASTNodes.SemanticCheckException;


public class SyntaxHighlighter {

	/* Colours used by default:
	 * Comments ("green") = 33CC00
	 * Keywords ("blue") = 3333FF
	 * Incorrect Syntax ("red") = FF0000
	 * Functions ("light purple") = 660066
	 * Rule Names ("light blue") = 3399CC
	 * Variables ("purple") = 660099
	 * */
	
	// An array of all keywords to be recognised
	private String[] keywords;
	private String[] functions;
	private String[] variables;
	private ArrayList<Exception> exceptions;
	
	// The colours to be used for syntax highlighting is hex form
	private String keyword_colour = "8B1C62";//"3333FF";
	private String comment_colour = "3F7F5F";//"33CC00";
	private String incorrect_colour = "FF0000";
	private String function_colour = "8B1C62";//"660066";
	private String rule_colour = "0000C0";//"3399CC";
	private String variable_colour = "0000C0";//"660099";
	
	public SyntaxHighlighter(String [] _keywords,String[] _functions) {
		// Set up a new SyntaxPane given a list of keywords and functions
		this.keywords = _keywords;
		this.functions = _functions;
		this.exceptions = new ArrayList<Exception>();
	}
	
	public SyntaxHighlighter() {
		// Set up a new SyntaxPane which uses default BACON keywords and functions
		String[] keys = {"if","then","else","with"};
		this.keywords = keys;
		String[] funs = {"uptake","release","ingest","pchange","change","divide","integrate",
				"create","densityAt","depthForFI","depthForVI","fullIrradAt","salinityAt",
				"temperatureAt","UVIrradAt","varhist"};
		this.functions = funs;
		this.variables = AmbientVariableTables.getTables().getAllVariableNames();
		this.exceptions = new ArrayList<Exception>();
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
					"<font color=#" + keyword_colour + "><b>" + keywords[i] + "</b></font>");
		}
		// Highlight all function names
		for (int i = 0; i < functions.length; i++) {
			text = text.replaceAll(functions[i], 
					"<font color=#" + function_colour + "><b>" + functions[i] + "</b></font>");
		}
		// Highlight all variable names
		for (int i = 0; i < variables.length; i++) {
			text = text.replaceAll(variables[i], 
					"<font color=#" + variable_colour + ">" + variables[i] + "</font>");
		}
		// Highlight all rule names
		if (text.contains(":")) {
			text = text.replaceAll(":", "</font>:");
			char[] chars = text.toCharArray();
			text = "";
			boolean colon_found = false;
			for (int i = chars.length - 1; i >= 0; i--) {
				if (chars[i] == ':') {
					colon_found = true;
				} else if (colon_found && chars[i] == '\n') {
					colon_found = false;
					text += "%";
				}
				text += chars[i];
			}
			if (colon_found) {
				text += "%";
			}
			text = new StringBuffer(text).reverse().toString();
			text = text.replaceAll("%","<font color=#" + rule_colour + ">");
		}
		text = remove_non_word_tags(text);
		// Highlight all comments
		if (text.contains("//")) {
			char[] chars = text.toCharArray();
			text = "";
			boolean in_comment = false;
			for (int i = 0; i < chars.length; i++) {
				if (i < (chars.length - 1) && chars[i] == '/' && chars[i+1] == '/') {
					in_comment = true;
					text += "<font color=#" + comment_colour + ">/";
				} else if (in_comment && chars[i] == '\n') {
					in_comment = false;
					text += "</font>\n";
				} else {
					text += chars[i];
				}
			}
		}
		// Flag all error lines
		for(Exception e : exceptions) {
			if (e instanceof TreeWalkerException) {
				TreeWalkerException twe = (TreeWalkerException) e;
				text = highlight_error(twe.getLine(),twe.getChar_pos(),text);
			} else if (e instanceof SemanticCheckException) {
				SemanticCheckException sce = (SemanticCheckException) e;
				text = highlight_error(sce.getLine(),0,text);
			}
		}
		text = remove_inner_tags(text);
		// Incorrect keywords should be highlighted
		text = text.replaceAll("<%>", "<font color=#" + incorrect_colour + ">");
		text = text.replaceAll("</%>", "</font>");
		return("<html><head></head><PRE>\n" + text + "</PRE></html>");
	}
	
	// Flags a line of text containing an error
	private String highlight_error(int line, int char_pos, String text) {
		String flagged_text = getPreText(text);
		boolean ignore = false;
		boolean error_found = false;
		char[] chars = text.toCharArray();
		// This uses '%', which is later replaced with the actual font tag
		for (int i = 0; i < chars.length; i++) {
			if (error_found && (chars[i] == '\n' || chars[i] == ' ' || chars[i] == '\t'
				             || chars[i] == ')' || chars[i] == '(')) {
				flagged_text += "</%>";
				error_found = false;
			} else if (!ignore && line == 1 && chars[i] == '<') {
				ignore = true;
			} else if (!ignore && line == 1 && chars[i] == '&') {
				// This is an &quot; tag or something
				ignore = true;
				char_pos--;
			} else if (ignore && (chars[i] == '>' || chars[i] == ';')) {
				ignore = false;
			} else if (!ignore && line == 1 && char_pos > 0) {
				char_pos--;
			} else if (!ignore && line == 1 && char_pos == 0) {
				if (!(chars[i] == ' ' || chars[i] == '\n' || chars[i] == '\t')) {
					// Highlight the error if it s an actual token
					error_found = true;
					flagged_text += "<%>";
				}
				line--;
			} else if (chars[i] == '\n') {
				line--;
			}
			flagged_text += chars[i];
		}
		return flagged_text;
	}
	
	// Add a flag to the list of error lines
	public void flag_line(Exception t) {
		this.exceptions.add(t);
	}
	
	// Clear the flag list
	public void clear_flags() {
		this.exceptions.clear();
	}
	
	// Remove all highlighted words which are part of other words (must be called
	// before comments are highlighted)
	private String remove_non_word_tags(String tag_text) {
		tag_text = remove_nwt_forward(tag_text);
		tag_text = remove_nwt_backward(tag_text);
		return tag_text;
	}

	private String remove_nwt_forward(String tag_text) {
		String final_text = "";
		char[] chars = tag_text.toCharArray();
		boolean ignore = false;
		boolean in_tag = false;
		for (int i = 0; i < chars.length; i++) {
			if ((i < chars.length - 3) 
					&& (isLetterOrDigit(chars[i]) || chars[i] == '<' ||  chars[i] == '>')
					&& chars[i+1] == '<' && chars[i+2] == 'f') {
				// This is the start of a '<font>' tag
				ignore = true;
				in_tag = true;
				final_text += chars[i];
			} else if (ignore && chars[i] == '>') {
				ignore = false;
			} else if (in_tag && (i < chars.length - 3) && chars[i] == '<' && chars[i+1] == '/') {
				// Skip over the font tag
				in_tag = false;
				ignore = true;
			} else if (!ignore) {
				final_text += chars[i];
			}
		}
		return final_text;
	}
	
	private String remove_nwt_backward(String tag_text) {
		String final_text = "";
		char[] chars = tag_text.toCharArray();
		boolean ignore = false;
		boolean in_tag = false;
		for (int i = chars.length - 1; i >= 0; i--) {
			if ((i > 2) 
				  && (isLetterOrDigit(chars[i]) || chars[i] == '<' || chars[i] == '>') 
				  && chars[i-1] == '>' && chars[i-2] == 't') {
				// This is the end of a '<font>' tag
				ignore = true;
				in_tag = true;
				final_text += chars[i];
			} else if (ignore && chars[i] == '<') {
				ignore = false;
			} else if (in_tag && (i > 2) && chars[i] == '>' && chars[i-1] != 't') {
				// Skip over the font tag
				in_tag = false;
				ignore = true;
			} else if (!ignore) {
				final_text += chars[i];
			}
		}
		// The text is the wrong way round, so reverse it
		return new StringBuffer(final_text).reverse().toString();
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
			if ((i < chars.length - 2) && chars[i] == '<' /*&& (chars[i+1] == 'f'
				 || chars[i+1] == '/' && chars[i+2] == 'f')*/) {
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
	
	/*
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
	*/
	
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

	private boolean isLetterOrDigit(char c) {
		return (Character.isLetterOrDigit(c) || c == '_');
	}

}
