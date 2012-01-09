package VEW.Planktonica2.UIComponents;

import java.util.ArrayList;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.ASTNodes.BACONCompilerException;
import VEW.XMLCompiler.ASTNodes.TreeWalkerException;
import VEW.XMLCompiler.ASTNodes.SemanticCheckException;

/**
 * This class takes text (as html) and adds tags to highlight the text
 * appropriately.
 *
 */
public class SyntaxHighlighter {

	/* Colours used by default:
	 * Comments (green) = 3F7F5F
	 * Keywords (purple) = 8B1C62
	 * Incorrect Syntax (red) = FF0000
	 * Functions (purple) = 8B1C62
	 * Rule Names (blue) = 0000C0
	 * Variables (blue) = 0000C0
	 * */
	
	// An array of all keywords to be recognised
	private String[] keywords;
	private String[] functions;
	private String[] variables;
	private ArrayList<Exception> exceptions;
	
	// The colours to be used for syntax highlighting in hex form
	private String keyword_colour = "8B1C62";
	private String comment_colour = "3F7F5F";
	private String incorrect_colour = "FF0000";
	private String function_colour = "8B1C62";
	private String rule_colour = "0000C0";
	private String variable_colour = "0000C0";
	
	/**
	 * Create a new <code>SyntaxHighlighter</code> that does not use <code>BACON</code> as its base language.
	 * @param keys - an array of Strings containing all the keywords of the language
	 * @param funcs - an array of Strings containing all the function names
	 * @param vars - an array of Strings containing all global variable names
	 */
	public SyntaxHighlighter(String [] keys,String[] funcs,String[] vars) {
		// Set up a new SyntaxPane given a list of keywords and functions
		this.keywords = keys;
		this.functions = funcs;
		this.variables = vars;
		this.exceptions = new ArrayList<Exception>();
	}
	
	/**
	 * Creates a <code>SyntaxHighlighter</code> that uses standard <code>BACON</code> syntax
	 */
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
	
	/**
	 * Mark up an input text with <code>&lt;font&gt;</code> tags to mark syntax
	 * @param text - The text to be highlighted (must be html)
	 * @return - The input text with additional tags to highlight syntax
	 */
	public String highlight(String text) {
		// Clear all <html>/<PRE> tags
		text = getPreText(text);
		// Remove any tags from previous highlightings
		text = remove_highlight_tags(text);
		// Highlight all keywords
		text = highlight_words(text,keywords,keyword_colour);
		// Highlight all function names
		text = highlight_words(text,functions,function_colour);
		// Highlight all variable names
		text = highlight_words(text,variables,variable_colour);
		// Highlight all rule names
		text = highlight_rule_names(text);
		// Remove all tags which are not full words
		text = remove_non_word_tags(text);
		// Highlight all comments
		text = highlight_comments(text);
		// Flag all error lines
		text = highlight_errors(text);
		return("<html><head></head><PRE>\n" + text + "</PRE></html>");
	}

	/**
	 * Surrounds all instances of a word in a given text with <code>&lt;font&gt;</code> tags to
	 * change the word colour
	 * @param text - Text containing words to be highlighter
	 * @param words - Array of Strings containing all words to highlight
	 * @param colour - The colour (in String form) to highlight
	 * @return - Text with all words marked up
	 */
	private String highlight_words(String text,String[] words,String colour) {
		for (int i = 0; i < words.length; i++) {
			text = text.replaceAll(words[i], 
					"<font color=#" + colour + ">" + words[i] + "</font>");
		}
		return text;
	}
	
	/**
	 * Highlights all <code>BACON</code> rule names
	 * @param text - Text to highlight
	 * @return - Text with all rule names (ie. everything on a line before a colon) highlighted
	 */
	private String highlight_rule_names(String text) {
		if (text.contains(":")) {
			// This works by inserting a </font> tag just before the colon and then working
			// backwards through the text until it finds a newline (or the start of the text)
			// and adding in a token which will be replaced at the end by a <font> tag.
			text = text.replaceAll(":", "</font>:");
			char[] chars = text.toCharArray();
			text = "";
			boolean colon_found = false;
			for (int i = chars.length - 1; i >= 0; i--) {
				if (chars[i] == ':') {
					colon_found = true;
				} else if (colon_found && chars[i] == '\n') {
					colon_found = false;
					// This will be reversed into <%>
					text += ">%<";
				}
				text += chars[i];
			}
			if (colon_found) {
				text += ">%<";
			}
			// As the text was parsed backwards, it needs to be reversed
			text = new StringBuffer(text).reverse().toString();
			text = text.replaceAll("<%>","<font color=#" + rule_colour + ">");
		}
		return text;
	}
	
	/**
	 * Highlights <code>BACON</code> line comments from the // to the next newline
	 * @param text - Text containing comments
	 * @return - Text with all comments highlighted
	 */
	private String highlight_comments(String text) {
		if (text.contains("//")) {
			char[] chars = text.toCharArray();
			text = "";
			boolean in_comment = false;
			// Upon finding "//", search the text until the next '\n' is found and add
			// a </font> tag
			for (int i = 0; i < chars.length; i++) {
				if (!in_comment && i < (chars.length - 1) && chars[i] == '/' && chars[i+1] == '/') {
					in_comment = true;
					text += "<font color=#" + comment_colour + ">/";
				} else if (in_comment && chars[i] == '\n') {
					in_comment = false;
					text += "</font>\n";
				} else {
					text += chars[i];
				}
			}
			if (in_comment)
				text += "</font>";
		}
		return text;
	}
	
	/**
	 * Highlights all error tokens in a given text
	 * @param text - Text containing lines with errors
	 * @return - Text with error tokens highlighted
	 */
	private String highlight_errors(String text) {
		for(Exception e : exceptions) {
			if (e instanceof TreeWalkerException) {
				TreeWalkerException twe = (TreeWalkerException) e;
				text = highlight_error(twe.getLine(),twe.getChar_pos(),text);
			} else if (e instanceof SemanticCheckException) {
				BACONCompilerException sce = (BACONCompilerException) e;
				text = highlight_error(sce.getLine(),0,text);
			}
		}
		// Remove highlighting within error tags
		text = remove_inner_tags(text);
		// Replace the tokens with actual font tags
		text = text.replaceAll("<%>", "<font color=#" + incorrect_colour + ">");
		text = text.replaceAll("</%>", "</font>");
		return text;
	}
	
	/**
	 * Highlights a single error, given a line and a position in that line
	 * @param line - Line with an error
	 * @param char_pos - Actual position in the line of the error
	 * @param text - Text containing the error
	 * @return - Text with error highlighted
	 */
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
	
	/**
	 * Adds a compiler exception to the list of stored errors to highlight
	 * @param t - Exception to add
	 */
	public void flag_line(Exception t) {
		this.exceptions.add(t);
	}
	
	/**
	 * Removes all stored exceptions from the highlight list
	 */
	public void clear_flags() {
		this.exceptions.clear();
	}
	
	/**
	 * Remove all highlighted words which are part of other words <br>
	 * eg. <br>
	 *   <code>cl&lt;font&gt;if&lt;/font&gt;f -> cliff</code>
	 * @param tag_text - Text containing tags
	 * @return - Text with incorrect highlighting removed
	 */
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
	
	/**
	 * Remove all <code>&lt;font&gt;</code> tags inside other <code>&lt;font&gt;</code> tags
	 * @param tag_text
	 * @return
	 */
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
	
	/**
	 * Remove all syntax highlighting tags from the text
	 * @param tag_text
	 * @return
	 */
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
	
	/**
	 * Return the text without any <code>HTML</code> tags (for parsing, etc.)
	 * @param text - Text containing <code>HTML</code> tags
	 * @return - Plaintext version of input text
	 */
	public String getPlainText(String text) {
		String plain_text = getPreText(text);
		plain_text = remove_highlight_tags(plain_text);
		// Replace HTML escape sequences with actual characters
		plain_text = plain_text.replaceAll("&quot;", "\"");
		plain_text = plain_text.replaceAll("&lt;", "<");
		plain_text = plain_text.replaceAll("&gt;", ">");
		plain_text = plain_text.replaceAll("&percnt;", "%");
		return plain_text;
	}

	/**
	 * Get the text within the <code>&lt;html&gt;&lt;PRE&gt;&lt;/PRE&gt;&lt;/html&gt;</code> tags
	 * @param text
	 * @return - The text between the <code>&lt;PRE&gt;&lt;/PRE&gt;</code> tags
	 */
	private String getPreText(String text) {
		boolean in_pre = false;
		//text = text.replaceAll("</pre>\n   <pre>", "AAA");
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
