package gradebook;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class BackUpStudentsCommand extends AbstractAction
{
	private static ArrayList<Menu> menuCollection;
	private static ArrayList<Student> studentCollection;
	private static BackUpStudentsCommand backUpCommand;
	private ApachePOIExcelWrite fileWriter;
	
	public BackUpStudentsCommand(ArrayList<Menu> menuCollection, ArrayList<Student> studentCollection)
    {
       super("6. Back Up Students to File");
       BackUpStudentsCommand.menuCollection = menuCollection;
	   BackUpStudentsCommand.studentCollection = studentCollection;
    }
    // Method that creates a new instance of ExitCommand and if it is already created, return the same QuitCommand
    // to any object that calls for it
    public static BackUpStudentsCommand getBackUp()
    {
    	if(backUpCommand == null)
    	{
    		backUpCommand = new BackUpStudentsCommand(menuCollection, studentCollection);
    	}
    	return backUpCommand;
    }
    public void actionPerformed(ActionEvent e)
    {
         Iterator<Menu> elements = menuCollection.iterator();
         while(elements.hasNext())
         {
        	 Menu object = elements.next();
        	 if(object instanceof MainMenu)
        	 {
        		String className = (((MainMenu) object).getClassName());
        		if(className.length() > 0 && studentCollection.size() > 0)
        		{
        			fileWriter = new ApachePOIExcelWrite(className);
        			fileWriter.write(studentCollection);
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null, "Please make sure you have created a class and added at least 1 student.");
        		}
        	 }
         }
    }
}
