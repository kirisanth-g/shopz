package UI;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Tester {
	
	//all the pageID's 
	//just add to here for new pages, 
	public static enum PageID{
		LOGIN,
		ACCOUNT_SETTINGS,
		SEARCH,
		SEARCH_RES,
		SHOPPING_CART,
	}

	//stores all the default pages mapped to the enums
private static Map<PageID, Composite> pages = new HashMap<PageID ,Composite>(); 
//holds the pageIDs of the what level the user is in, INCLUDES current page as top of stack
private static Stack<PageID> prevpages = new Stack<PageID>(); 
private static Composite contentPanel;
private static StackLayout layout;


	public static void main(String[] args){
		init(); 
	}

  public static void init() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setBounds(10, 10, 300, 200);
    // create the composite that the pages will share
    contentPanel = new Composite(shell, SWT.BORDER);
    contentPanel.setBounds(100, 10, 190, 90);
    layout = new StackLayout();
    contentPanel.setLayout(layout);

    // create the first page's content
    final Composite page0 = new SearchUI(contentPanel, SWT.NONE);
    //final Composite 
    
    
    //content panel holds the system level buttons (back button, app runs inside )
    pages.put( PageID.SEARCH , new SearchUI(contentPanel, SWT.NONE)); 
    pages.put( PageID.SEARCH_RES, new SearchResult(contentPanel, SWT.NONE)); 
    
    
   // page0.setLayout(new GridLayout());
   // Label label = new Label(page0, SWT.NONE);
   // label.setText("Label on page 1");
   // label.pack();

    // create the second page's content
   // final Composite page1 = new Composite(contentPanel, SWT.NONE);
   // page1.setLayout(new RowLayout());
    //Button button = new Button(page1, SWT.NONE);
   // button.setText("Button on page 2");
   // button.pack();

    // create the button that will switch between the pages
    Button pageButton = new Button(shell, SWT.PUSH);
    pageButton.setText("Back");
    pageButton.setBounds(10, 10, 80, 25);
    
    //defaults to the search page for now
    switchLayout(PageID.SEARCH);
    
    pageButton.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
    	  goBack();
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
  
  public static void goBack(){
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
  public static void switchLayout(PageID pid){
	  System.out.println(layout);
	  layout.topControl = pages.get(pid);
	  contentPanel.layout(); 
	  prevpages.push(pid); 
  }
}