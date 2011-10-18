package VEW.Common.Utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import VEW.Common.StringTools;
import VEW.Common.TableSorter;

public class BinViewer {

	/* GUI components */

	private static final JFrame TheViewer = new JFrame("Bin File Viewer");
	private static final JButton chooseFile = new JButton("Choose File");
	private static final JLabel format = new JLabel("Format (bcBsilfd)");
	private static final JTextField formatString = new JTextField("");
	private static final JLabel from = new JLabel("From Record");
	private static final JTextField fromString = new JTextField("0");
  private static final JTextField colNo = new JTextField("");
  private static final JTextField colVal = new JTextField("");  
  private static final JCheckBox zip = new JCheckBox("Zipped?");
	private static final JLabel to = new JLabel("to Record");
	private static final JTextField toString = new JTextField("10");
	private static final byte[] bits = new byte[] { 1, 16, 8, 16, 32, 64, 32, 64 };

	private static final String formats = new String("bcBsilfd");

	private static final String[] bigFormats = new String[] { "boolean",
			"char", "byte", "short", "int", "long", "float", "double" };
  
  private static final Class[] classes = new Class[] { Boolean.class,
    Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class
  };

  private static final JTable table = new JTable();
	private static final JProgressBar progressBar = new JProgressBar();
	private static final JButton go = new JButton("Go!");

	/* Other components */

	private static final JFileChooser fileChooser = new JFileChooser();

	public void initialise() {
		/* The Events */
		EventHandler eh = new EventHandler();
		chooseFile.addActionListener(eh);
		go.addActionListener(eh);
		TheViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* The GUI setup */

		TheViewer.getContentPane().setLayout(new BorderLayout(2, 2));
		TheViewer.getContentPane().add(new JScrollPane(table),
				BorderLayout.CENTER);
		final JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.add(progressBar);
		bottomPanel.add(go);
		TheViewer.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		final JPanel topPanel = new JPanel(new GridLayout(2, 1));
		final JPanel topPanelA = new JPanel(new FlowLayout());
		topPanelA.add(chooseFile);
    topPanelA.add(zip);
    topPanelA.add(format);
		topPanelA.add(formatString);
		formatString.setPreferredSize(new Dimension(120, 20));
		fromString.setPreferredSize(new Dimension(80, 20));
    colNo.setPreferredSize(new Dimension(20, 20));    
    colVal.setPreferredSize(new Dimension(80, 20));    
		toString.setPreferredSize(new Dimension(80, 20));
		topPanel.add(topPanelA);
		final JPanel topPanelB = new JPanel(new FlowLayout());
		topPanelB.add(from);
		topPanelB.add(fromString);
		topPanelB.add(to);
    topPanelB.add(toString);
    topPanelB.add(new JLabel("where column"));
    topPanelB.add(colNo);
    topPanelB.add(new JLabel("="));
    topPanelB.add(colVal);
    
		
		topPanel.add(topPanelB);
		TheViewer.getContentPane().add(topPanel, BorderLayout.NORTH);
		TheViewer.pack();
		TheViewer.setVisible(true);
	}

	public static int getByteSize() {
		int size = 0;
		for (int i = 0; i < formatString.getText().length(); i++)
			size += bits[formats.indexOf(formatString.getText().charAt(i))];
		if (size % 8 > 0)
			size = (size / 8) + 1;
		else
			size = (size / 8);
		return size;
	}

	public static void main(String[] args) {
		BinViewer bv = new BinViewer();
		bv.initialise();
	}

	class BinFileModel extends AbstractTableModel {

		List[] columnData;
    ArrayList tempList;
		String theFormat;
		int columns;
		int rows;
		int firstIndex;
		
		public BinFileModel(String _format, String file) throws Exception {
			int fromIndex = Integer.parseInt(fromString.getText());
			int toIndex = Integer.parseInt(toString.getText());
			firstIndex = fromIndex;
			rows = toIndex - fromIndex;
			columns = _format.length();
			columnData = new List[columns];
			for (int i = 0; i < columnData.length; i++)
				columnData[i] = new ArrayList(rows);
			tempList = new ArrayList();
			theFormat = _format;
			read(file, fromIndex, toIndex);
		}
		
		public int getRowCount() {
			return rows;
		}

		public int getColumnCount() {
			return columns + 1;
		}

		public String getColumnName(int columnIndex) {
			if (columnIndex == 0)
				return "No.";
			else
				return bigFormats[formats.indexOf(theFormat.charAt(columnIndex - 1))];
		}
		
    public Class getColumnClass(int columnIndex) {
      if (columnIndex == 0)
        return Integer.class;
      else
        return classes[formats.indexOf(theFormat.charAt(columnIndex - 1))];
    }
    
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0)
				return new Integer(rowIndex + firstIndex);
			else if (((columnIndex-1)<columnData.length) && (columnData[columnIndex-1]!=null) && (columnData[columnIndex-1].size()>rowIndex))
				return columnData[columnIndex - 1].get(rowIndex);
      else return null;
		}

		public void ReallySkip(long Bytes, DataInputStream TheStream,
				JProgressBar pb)  {
      try {
			  long BytesToGo = Bytes;
			  while (BytesToGo > Integer.MAX_VALUE) {
  				BytesToGo -= TheStream.skipBytes(Integer.MAX_VALUE);
				  if (pb != null) {
  					pb.setValue(pb.getValue() + Integer.MAX_VALUE);
					  progressBar.paint(progressBar.getGraphics());
				  }
			  }
			  if (BytesToGo > 0) {
  				TheStream.skipBytes((int) BytesToGo);
				  if (pb != null) {
  					pb.setValue(pb.getValue() + (int) BytesToGo);
					  progressBar.paint(progressBar.getGraphics());
				  }
			  }
		  } catch (Exception e) { e.printStackTrace(); }
    }

		public void read(String file, int _from, int _to) {
			int theColNo = -1;
      String theColVal = "";
      if (colNo.getText().length()>0) {
        theColNo = Integer.parseInt(colNo.getText());
        theColVal = colVal.getText();
      }
      int progress = 0;
			int recSize = getByteSize();
			int totalToRead = recSize * (_to + 1);
			int beginningToSkip = recSize * _from;

			if (theColNo==-1) progressBar.setMaximum(totalToRead);
      else progressBar.setMaximum(_to);
			progressBar.setMinimum(0);
			progressBar.setValue(progress);
      DataInputStream theStream = null;
      
      try {
        if (zip.isSelected())
          theStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        else
          theStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
      } catch (Exception e) { e.printStackTrace(); }
			ReallySkip(beginningToSkip, theStream, progressBar);
			if (theColNo==-1) progress = beginningToSkip;
			int i = _from;
      try {
        while (i<=_to) {
          boolean good = false;
          if (theColNo==-1) good = true;
          tempList.clear();
				  for (int j = 0; j < theFormat.length(); j++) {
          
  					if (theFormat.charAt(j)=='b') {
              Boolean b = new Boolean(theStream.readBoolean());
              tempList.add(b);
              if ((theColNo==j) && (b.booleanValue()==StringTools.parseBoolean(theColVal))) good=true;
            } else if (theFormat.charAt(j)=='B') {
              Byte b = new Byte(theStream.readByte());
              tempList.add(b);
              if ((theColNo==j) && (b.byteValue()==Byte.parseByte(theColVal))) good=true;
            } else if (theFormat.charAt(j)=='c') {
              Character c = new Character(theStream.readChar());
              tempList.add(c);
              if ((theColNo==j) && (c.charValue()==theColVal.charAt(0))) good=true;
            } else if (theFormat.charAt(j)=='s') {
              Short s = new Short(theStream.readShort());
              tempList.add(s);
              if ((theColNo==j) && (s.shortValue()==Short.parseShort(theColVal))) good=true;
            } else if (theFormat.charAt(j)=='i') {
              Integer ii = new Integer(theStream.readInt());
              tempList.add(ii);
              if ((theColNo==j) && (ii.intValue()==Integer.parseInt(theColVal))) good=true;
            } else if (theFormat.charAt(j)=='l') {
              Long l = new Long(theStream.readLong());
              tempList.add(l);
              if ((theColNo==j) && (l.longValue()==Long.parseLong(theColVal))) good=true;
            } else if (theFormat.charAt(j)=='f') { 
              Float f = new Float(theStream.readFloat());
              tempList.add(f);
              if ((theColNo==j) && (f.floatValue()==Float.parseFloat(theColVal))) good=true;
            } else if (theFormat.charAt(j)=='d') {
              Double d = new Double(theStream.readDouble());            
              tempList.add(d);
              if ((theColNo==j) && (d.doubleValue()==Double.parseDouble(theColVal))) good=true;
            }
          }
          if (good) {
            for (int j=0; j<theFormat.length(); j++) {
              columnData[j].add(tempList.get(j));
            }
            i++;
          }        
				  if (theColNo==-1) {
            progress += getByteSize();
				    progressBar.setValue(progress);
				    progressBar.paint(progressBar.getGraphics());
          } else {
            if (good) {
              progress++;
              progressBar.setValue(progress);
              progressBar.paint(progressBar.getGraphics());
            }
          }
			  }
			  theStream.close();
      } catch (Exception e) { 
        progressBar.setValue(progressBar.getMaximum());
        toString.setText(String.valueOf(i-1));
        rows=i;
        //e.printStackTrace();
      }
		}
	}

	class EventHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == chooseFile) {
				fileChooser.showOpenDialog(TheViewer);
			} else if (e.getSource() == go) {
				try {
					BinFileModel binFileModel = new BinFileModel(formatString.getText(), fileChooser.getSelectedFile().getAbsolutePath());
          TableSorter tableSorter = new TableSorter(binFileModel);
          table.setModel(tableSorter);
          tableSorter.setTableHeader(table.getTableHeader());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
  }
