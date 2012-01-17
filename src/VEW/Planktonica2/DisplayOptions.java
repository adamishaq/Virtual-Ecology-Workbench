package VEW.Planktonica2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import VEW.Planktonica2.Model.UnitChecker;

public class DisplayOptions {

	private static DisplayOptions options;
	
	public static DisplayOptions getOptions() {
		if (options == null)
			options = new DisplayOptions();
		return options;
	}
	
	public String config_path = "";
	public boolean MAXIMISED = true;
	public int FRAME_WIDTH = 500;
	public int FRAME_HEIGHT = 500;
	public int FRAME_X = 0;
	public int FRAME_Y = 0;
	public boolean LAYOUT_VERTICAL = true;
	public boolean PREVIEW_UNITS = true;
	public boolean PREVIEW_RULE_NAMES = true;
	public boolean SOURCE_IN_LATEX = false;
	public boolean ATTEMPT_TYPE_CONVERSION = true;
	public boolean ATTEMPT_TYPE_SCALING = false;
	public int EDITOR_PANEL_SIZE = 300;
	public int ERROR_PANEL_SIZE = 400;
	public int SIDE_PANEL_SIZE = 170;
	
	public void write_config() {
		
		File config_file = new File(DisplayOptions.getOptions().config_path);
		try {
			FileWriter writer = new FileWriter(config_file);
			String file = "Maximised - " + (DisplayOptions.getOptions().MAXIMISED ? 1 : 0) + "\n"
					+ "Frame Width - " + (DisplayOptions.getOptions().FRAME_WIDTH) + "\n"
					+ "Frame Height - " + (DisplayOptions.getOptions().FRAME_HEIGHT) + "\n"
					+ "Frame X - " + (DisplayOptions.getOptions().FRAME_X) + "\n"
					+ "Frame Y - " + (DisplayOptions.getOptions().FRAME_Y) + "\n"
					+ "Layout Vertical - " + (DisplayOptions.getOptions().LAYOUT_VERTICAL ? 1 : 0) + "\n"
					+ "LaTeX Units - " + (DisplayOptions.getOptions().PREVIEW_UNITS ? 1 : 0) + "\n"
					+ "LaTeX Names - " + (DisplayOptions.getOptions().PREVIEW_RULE_NAMES ? 1 : 0) + "\n"
					+ "Source in LaTeX - " + (DisplayOptions.getOptions().SOURCE_IN_LATEX ? 1 : 0) + "\n"
					+ "Editor Pane - " + DisplayOptions.getOptions().EDITOR_PANEL_SIZE + "\n"
					+ "Error Pane - " + DisplayOptions.getOptions().ERROR_PANEL_SIZE + "\n"
					+ "Side Pane - " + DisplayOptions.getOptions().SIDE_PANEL_SIZE + "\n"
					+ "Convert Types - " + (DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION ? 1 : 0) + "\n"
					+ "Scale Types - " + (DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING ? 1 : 0) + "\n"
					+ UnitChecker.getUnitChecker().getOutputEquivalence();
			writer.write(file);
			writer.flush();
			writer.close();
		} catch (IOException e) {
		}
		
	}
	
}
