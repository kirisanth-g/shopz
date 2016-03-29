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
	
	public static final int MAX_WIDTH = 800;
	public static final int MAX_HEIGHT = 600;
	//all the pageID's 
	//just add to here for new pages, 
	public static enum ViewID{
		LOGIN,
		ACCOUNT_SETTINGS,
		SEARCH,
		SEARCH_RES,
		SHOPPING_CART,
		REVIEWS_LIST,
		ADMIN_ITEM_ADD,
		ADD_REVIEW,
	}
	
private static Map<ViewID, Composite> pages = new HashMap<ViewID ,Composite>(); 
private static Stack<ViewID> viewStack = new Stack<ViewID>(); 
private static Composite contentPanel;
private static StackLayout layout;
	public static void loadContent(){
		pages.put( ViewID.SEARCH , new SearchUI(contentPanel, SWT.NONE)); 
	    pages.put( ViewID.SEARCH_RES, new SearchResultsUI(contentPanel, SWT.NONE)); 
	    pages.put( ViewID.LOGIN, new LoginUI(contentPanel,SWT.NONE)); 
	    pages.put( ViewID.ACCOUNT_SETTINGS, new AccountInfoUI(contentPanel, SWT.NONE));
	    pages.put( ViewID.REVIEWS_LIST, new ReviewListUI(contentPanel, SWT.NONE));
	    pages.put( ViewID.SHOPPING_CART, new ShopingCartUI(contentPanel,SWT.NONE));
	    pages.put( ViewID.ADMIN_ITEM_ADD, new AdminAddItemUI(contentPanel, SWT.NONE));
	    pages.put( ViewID.ADD_REVIEW, new AddReviewUI(contentPanel, SWT.NONE));
	  
	}

  public static void init() {
    Display display = new Display();
    Shell shell = new Shell(display);
    //gives us 10 px margins on the ends 
    shell.setBounds(10, 10, MAX_WIDTH+120, MAX_HEIGHT+90);
    contentPanel = new Composite(shell, SWT.BORDER);
    contentPanel.setBounds(100, 10, MAX_WIDTH, MAX_HEIGHT);
    layout = new StackLayout();
    contentPanel.setLayout(layout);


    //content panel holds the system level buttons (back button, app runs inside )
   // pages.put( ViewID.SEARCH , new SearchUI(contentPanel, SWT.NONE)); 
    //pages.put( ViewID.SEARCH_RES, new SearchResultsUI(contentPanel, SWT.NONE)); 
    pages.put( ViewID.LOGIN, new LoginUI(contentPanel,SWT.NONE)); 
   // pages.put( ViewID.ACCOUNT_SETTINGS, new AccountInfoUI(contentPanel, SWT.NONE));
   // pages.put( ViewID.REVIEWS_LIST, new ReviewListUI(contentPanel, SWT.NONE));
   // pages.put( ViewID.SHOPPING_CART, new ShopingCartUI(contentPanel,SWT.NONE));
   // pages.put( ViewID.ADMIN_ITEM_ADD, new AdminAddItemUI(contentPanel, SWT.NONE));
  

    // create the button that will switch between the pages
    Button pageButton = new Button(shell, SWT.PUSH);
    pageButton.setText("Back");
    pageButton.setBounds(10, 10, 80, 25);
    
    //defaults to the search page for now
    switchView(ViewID.LOGIN);
    
    pageButton.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
    	  prevView();
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
	  if(viewStack.size() > 1){
		  //remove the current from running stack
		  viewStack.pop(); 
		  load(viewStack.peek());

	  }
  }
  
  private static void load(ViewID pid){
	  View view = (View) pages.get(pid);
	  view.resetView();
	  layout.topControl = (Composite) view;
	  contentPanel.layout();
  }
  
  //maybe call this in the other methods
  public static void switchView(ViewID pid){
	  //puts the new page to visit on top of the stack
	  viewStack.push(pid); 
	  load(pid);

	  	  
  }
  

}