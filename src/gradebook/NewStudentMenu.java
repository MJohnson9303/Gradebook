package gradebook;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// This class is used to add a new student into the collection. Each time a student is added,
// the table in ViewAndDeleteStudents menu is updated.
public class NewStudentMenu extends Menu implements ActionListener
{
	private JTextField ssnText, nameText, ageText;
	private JComboBox<String> acadLevelComboBox, genderListComboBox;
	private String[] acadLevels = new String[]{"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};;
	private JButton saveButton, backToMainMenuButton;
	private String[] genders = {"Male", "Female"};
	private ArrayList<Menu> menuCollection;
	private ArrayList<Student> studentCollection;
	
	public NewStudentMenu(ArrayList<Menu> menuCollection, ArrayList<Student> studentCollection)
	{
		super("Gradebook");
		guiSetUp();
		this.menuCollection = menuCollection;
		this.studentCollection = studentCollection;
		saveButton.addActionListener(this);
		backToMainMenuButton.addActionListener(this);
	}
	private void guiSetUp()
	{
		 super.setLayout(new BorderLayout());
	  		JPanel lablePanel = new JPanel();
			JLabel label = new JLabel("Enter Student Information");
		    label.setFont(new Font("SansSerif", Font.BOLD, 18));
			lablePanel.add(label);
			super.add(lablePanel, BorderLayout.NORTH);
			
			JPanel ssnAndNamePanel = new JPanel();
			// To center SSN and textfield
			ssnAndNamePanel.setBorder(BorderFactory.createEmptyBorder( 
									15, // top 
									50, // left 
									0, // bottom 
									50)); // right 
			JLabel label3 = new JLabel("SSN:");
			ssnAndNamePanel.add(label3);
			ssnText = new JTextField(18);
			ssnAndNamePanel.add(ssnText);
			JLabel label4 = new JLabel("Name:");
			ssnAndNamePanel.add(label4);
			nameText = new JTextField(18);
			ssnAndNamePanel.add(nameText);
			JPanel genderAgeAndLevelPanel = new JPanel();
			JLabel label5 = new JLabel("Gender:");
			genderAgeAndLevelPanel.add(label5);
			// Create array list for genderList combobox
			// Create a combobox for gender
			genderListComboBox = new JComboBox<String>(genders);
			genderAgeAndLevelPanel.add(genderListComboBox);
			JLabel label6 = new JLabel("Age:");
			genderAgeAndLevelPanel.add(label6);
			ageText = new JTextField(5);
			genderAgeAndLevelPanel.add(ageText);
			JLabel label7 = new JLabel("Academic Level:");
			genderAgeAndLevelPanel.add(label7);
			// Create a combobox for academic levels
			acadLevelComboBox = new JComboBox<String>(acadLevels);
			genderAgeAndLevelPanel.add(acadLevelComboBox);
			ssnAndNamePanel.add(genderAgeAndLevelPanel);
			super.add(ssnAndNamePanel, BorderLayout.CENTER);
			JPanel buttonPanel = new JPanel(new GridLayout(1,2,2,2));
	        // To create space between the labels and textfields and the buttons
			buttonPanel.setBorder(BorderFactory.createEmptyBorder( 
									0, // top 
									180, // left 
									50, // bottom 
									180)); // right 
			saveButton = new JButton("Save & Add Student");
			buttonPanel.add(saveButton);
			backToMainMenuButton = new JButton("Back to Main Menu");
			buttonPanel.add(backToMainMenuButton);
			genderAgeAndLevelPanel.add(buttonPanel);
			super.add(buttonPanel, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == saveButton)
		{
			// Checking if the user provided input. If not, give an error message.
			if(ssnText.getText().length() > 0 &&
			   nameText.getText().length() > 0 &&
			   ageText.getText().length() > 0)
			{
				Student student = new Student();
				student.setID(Integer.parseInt(ssnText.getText()));
				student.setName(nameText.getText());
				student.setAge(ageText.getText());
				student.setGender(genderListComboBox.getSelectedItem().toString().charAt(0));
				student.setClassLevel(acadLevelComboBox.getSelectedItem().toString());
				studentCollection.add(student);
				// Resetting the text fields and combo boxes.
				ssnText.setText(null);
				nameText.setText(null);
				ageText.setText(null);
				genderListComboBox.setSelectedIndex(0);
				acadLevelComboBox.setSelectedIndex(0);
				Iterator<Menu> elements = menuCollection.iterator();
				while(elements.hasNext())
				{
					Menu object = elements.next();
					if(object instanceof MainMenu)
					{
						((MainMenu) object).setNumberOfStudents(studentCollection.size());
					}
					if(object instanceof ViewAndDeleteStudentsMenu)
					{
						((ViewAndDeleteStudentsMenu) object).resetTable();
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please provide information for all fields.");
			}
		}
		if(e.getSource() == backToMainMenuButton)
		{
			// Resetting the text fields and combo boxes.
			ssnText.setText(null);
			nameText.setText(null);
			ageText.setText(null);
			genderListComboBox.setSelectedIndex(0);
			acadLevelComboBox.setSelectedIndex(0);
			
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof MainMenu)
				{
					// Simulating redirection by hiding this frame and revealing the main menu.
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
	}
}
