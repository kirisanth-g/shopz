package UI;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Tester {

	
	public static void main(String args[]) 
	  {
	    Display display = new Display();
	    Shell shell = new Shell(display);
	    // ------------------------
	    // Your code comes to here.
	    // ------------------------
	    shell.pack();
	    shell.open();
	    while( !shell.isDisposed())
	    {
	      if(!display.readAndDispatch()) 
	      display.sleep();
	    }
	    display.dispose();
	  }
	
}
