package UI;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class ViewController  {
	
	//all the pageID's 
	//just add to here for new pages, 
	public static enum ViewID{
		LOGIN,
		ACCOUNT_SETTINGS,
		SEARCH,
		SEARCH_RES,
		SHOPPING_CART,
	}


private static Map<ViewID, Composite> pages = new HashMap<ViewID ,Composite>(); 
private static Stack<ViewID> prevpages = new Stack<ViewID>(); 
private static Composite contentPanel;
private static StackLayout layout;


  public static void init() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setBounds(10, 10, 300, 200);
    contentPanel = new Composite(shell, SWT.BORDER);
    contentPanel.setBounds(100, 10, 190, 90);
    layout = new StackLayout();
    contentPanel.setLayout(layout);


    //content panel holds the system level buttons (back button, app runs inside )
    pages.put( ViewID.SEARCH , new SearchUI(contentPanel, SWT.NONE)); 
    pages.put( ViewID.SEARCH_RES, new SearchResult(contentPanel, SWT.NONE)); 
    
    
  

    // create the button that will switch between the pages
    Button pageButton = new Button(shell, SWT.PUSH);
    pageButton.setText("Back");
    pageButton.setBounds(10, 10, 80, 25);
    
    //defaults to the search page for now
    switchView(ViewID.SEARCH);
    
    pageButton.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
    	  prevView();
    	  //this is the back button
       // pageNum = ++pageNum % 2;
        //layout.topControl = pageNum == 0 ? page0 : page1;
        //contentPanel.layout();
      }
    });

    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
  
  public static void prevView(){
	  //make sure you can go back
	  if(prevpages.size() > 1){
		  //remove the current from running stack
		  prevpages.pop(); 
		  //swith to the second one on the stack but keep it on the top
		  layout.topControl = pages.get(prevpages.peek());
		  contentPanel.layout(); 
	  }
  }
  
  //maybe call this in the other methods
  public static void switchView(ViewID pid){
	  System.out.println(layout);
	  layout.topControl = pages.get(pid);
	  contentPanel.layout(); 
	  prevpages.push(pid); 
  }
}