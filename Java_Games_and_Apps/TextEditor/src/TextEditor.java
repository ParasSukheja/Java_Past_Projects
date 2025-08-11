import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener{

	JTextArea textarea;
	JScrollPane scrollpane;
	JLabel fontlabel;
	JSpinner fontsizespinner;
	JButton fontcolorbutton;
	JComboBox fontbox;
	
	JMenuBar menubar;
	JMenu filemenu;
	JMenuItem openitem;
	JMenuItem saveitem;
	JMenuItem exititem;
	
	TextEditor(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Paras Text Editor");
		this.setSize(500,500);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		
		textarea = new JTextArea();
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setFont(new Font("Arial",Font.PLAIN,20));
		
		scrollpane = new JScrollPane(textarea);
		scrollpane.setPreferredSize(new Dimension(450,450));
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		fontlabel = new JLabel("Font :");
		
		fontsizespinner = new JSpinner();
		fontsizespinner.setPreferredSize(new Dimension(50,25));
		fontsizespinner.setValue(20);
		fontsizespinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				textarea.setFont(new Font(textarea.getFont().getFamily(),Font.PLAIN,(int) fontsizespinner.getValue()));

			}
			
		});
		
		fontcolorbutton = new JButton("Color");
		fontcolorbutton.addActionListener(this);
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		fontbox = new JComboBox(fonts);
		fontbox.addActionListener(this);
		fontbox.setSelectedItem("Arial");
		
		// ------------------ menubar --------------
		
		menubar = new JMenuBar();
		filemenu = new JMenu("File");
		openitem = new JMenuItem("Open");
		saveitem = new JMenuItem("Save");
		exititem = new JMenuItem("Exit");

		openitem.addActionListener(this);
		saveitem.addActionListener(this);
		exititem.addActionListener(this);
		
		filemenu.add(openitem);
		filemenu.add(saveitem);
		filemenu.add(exititem);
		menubar.add(filemenu);
		
		// ------------------/menubar --------------
		this.setJMenuBar(menubar);
		this.add(fontlabel);
		this.add(fontsizespinner);
		this.add(fontcolorbutton);
		this.add(fontbox);
		this.add(scrollpane);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//-------------font color------------
		if(e.getSource() == fontcolorbutton) {
			JColorChooser colorchooser = new JColorChooser();
			
			Color color = colorchooser.showDialog(null, "Choose a color", Color.black);
			
			textarea.setForeground(color);
		}
		//----------- font color-------------
		
		//---------- for font size------------
		if(e.getSource() == fontbox) {
			textarea.setFont(new Font((String)fontbox.getSelectedItem(),Font.PLAIN,textarea.getFont().getSize()));
			
		}
		//----------for font size------------
		
		//--------------for menu items-------
		
		if(e.getSource() == openitem) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files","txt");
			filechooser.setFileFilter(filter);
			
			int response = filechooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(filechooser.getSelectedFile().getAbsolutePath());
				Scanner filein = null;
				
				try {
					filein = new Scanner(file);
					if(file.isFile()) {
						while(filein.hasNextLine()) {
							String line = filein.nextLine()+"\n";
							textarea.append(line);
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					filein.close();
				}
			}
			
		}
		if(e.getSource() == saveitem) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File(".")); //'.' dot for default directory
			
			int response = filechooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileout = null;
				
				file = new File(filechooser.getSelectedFile().getAbsolutePath());// this is use to store with in our file
				try {
					fileout = new PrintWriter(file);
					fileout.println(textarea.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					fileout.close();
				}
				
			}
		}
		if(e.getSource() == exititem) {
		System.exit(ABORT);	
		}
		//--------------for menu items-------
		
	}

}



