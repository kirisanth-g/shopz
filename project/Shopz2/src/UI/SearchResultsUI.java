package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;

import LogicalLayer.Item;
import LogicalLayer.Login;
import LogicalLayer.Search; 



import org.eclipse.swt.layout.FillLayout;

public class SearchResultsUI extends Composite implements View{
	private Label lblTitle;
	private ScrolledComposite scrolledComposite;
	private Composite composite;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchResultsUI(Composite parent, int style) {
		super(parent, style);
		//setLayout(new FillLayout());
		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setBounds(10, 10, 105, 14);
		
		
		//this allows for scrolling, set adapt 1 composite and allow scroll if that gets too big
		scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 30, ViewController.MAX_WIDTH-25, ViewController.MAX_HEIGHT-45);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		//this is the internal composite which does the expanding, in here you put your search entries
		composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));	
		resetView();
		
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));


	}

	@Override
	protected void checkSubclass() {
		
		// Disable the check that prevents subclassing of SWT components
	}
	
	public  void resetView(){ 
		lblTitle.setText("Search Results");
		
		//get the data from search
		 List<Item> results = Search.getResults();
		 
		 for (Item item : results){ 
			 System.out.println(item.getName());
		 }
		
		
		 //have it so that you insert the Item in to build the ui... makes a lot more sense,and it makes update eaiser
		ArrayList<ItemEntryUI> itemList = new ArrayList<ItemEntryUI>();
		for (Control child : composite.getChildren()) {
			child.dispose();
		}
		for (int i = 0; i < 10 ; i++){
			ItemEntryUI searchItemPanel = new ItemEntryUI(composite, SWT.NONE);
			searchItemPanel.resetView();
			itemList.add(searchItemPanel);
		}
		composite.layout();
		
		//this is where you might need to refernce Search.java for the last search term.
		//since you can't transfer data to and from 
	}
}
