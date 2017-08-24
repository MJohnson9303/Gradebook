package gradebook;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

// This class defines a command that implements an exit operation.
public class ExitCommand extends AbstractAction
{
	private static ExitCommand exitCommand;
	
	public ExitCommand()
    {
       super("7. Exit");
    }
    // Method that creates a new instance of ExitCommand and if it is already created, return the same QuitCommand
    // to any object that calls for it
    public static ExitCommand getExit()
    {
    	if(exitCommand == null)
    	{
    		exitCommand = new ExitCommand();
    	}
    	return exitCommand;
    }
    public void actionPerformed(ActionEvent e)
    {
         System.exit(0);
    }
}
