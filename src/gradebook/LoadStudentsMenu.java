package gradebook;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// This class is used to load a 2007 Excel file from the "Class Files" using
// the string provided by the user for the exact file.
public class LoadStudentsMenu extends Menu implements ActionListener
{
	private JTextField classTextField;
	private JButton loadButton, backToMainMenuButton;
	private ArrayList<Menu> menuCollection;
	private ArrayList<Student> studentCollection;
	private ApachePOIExcelRead fileReader;
	
	public LoadStudentsMenu(ArrayList<Menu> menuCollection, ArrayList<Student> studentCollection)
	{
		super("Gradebook");
		guiSetUp();
		this.menuCollection = menuCollection;
		this.studentCollection = studentCollection;
		loadButton.addActionListener(this);
        backToMainMenuButton.addActionListener(this);
        
	}
	private void guiSetUp()
	{
		super.setLayout(new BorderLayout());
		JPanel lablePanel = new JPanel();
		JLabel label = new JLabel("Load Students From a File");
	    label.setFont(new Font("SansSerif", Font.BOLD, 18));
		lablePanel.add(label);
		super.add(lablePanel, BorderLayout.NORTH);
		JPanel classNamePanel = new JPanel();
		classNamePanel.setBorder(BorderFactory.createEmptyBorder( 
								30, // top 
								0, // left 
							    0, // bottom 
								0)); // right 
		JLabel label2 = new JLabel("Class Name:");
		classNamePanel.add(label2);
		classTextField = new JTextField(12);
		classNamePanel.add(classTextField);
		super.add(classNamePanel, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel(new GridLayout(1,2,8,8));
        // To create space between the labels and textfields and the buttons
		// Left and right are set to 250 to center the Create and Cancel buttons
		buttonPanel.setBorder(BorderFactory.createEmptyBorder( 
								0, // top 
								250, // left 
							   40, // bottom 
								250)); // right 
		loadButton = new JButton("Load");
		buttonPanel.add(loadButton);
		backToMainMenuButton = new JButton("Back to Main Menu");
		buttonPanel.add(backToMainMenuButton);	
		super.add(buttonPanel, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == loadButton)
		{
			// Checking if the user provided input. Given an error message if otherwise.
			if(classTextField.getText().length() > 0)
			{
				Iterator<Menu> elements = menuCollection.iterator();
				while(elements.hasNext())
				{
					Menu object = elements.next();
					if(object instanceof MainMenu)
					{
						// Clearing the collection of any previous loaded students
						studentCollection.clear();
						fileReader = new ApachePOIExcelRead(classTextField.getText());
						fileReader.read(studentCollection);
						((MainMenu)object).setClassName(classTextField.getText());
						((MainMenu)object).setNumberOfStudents(studentCollection.size());
						// Resetting the text field to be empty if the user comes back to this menu
						classTextField.setText(null);
						object.setVisible(true);
						object.setLocation(this.getLocation().x, this.getLocation().y);
						this.setVisible(false);
					}
					if(object instanceof ViewAndDeleteStudentsMenu)
					{
						((ViewAndDeleteStudentsMenu)object).resetTable();
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please provide a file name.");
			}
		}
		if(e.getSource() == backToMainMenuButton)
		{
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof MainMenu)
				{
					// Resetting the text field to be empty if the user comes back to this menu
					classTextField.setText(null);
					// Setting the main menu to be visible, giving it the location of the LoadStudentMenu and making
					// LoadStudentMenu invisible
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
	}
}
