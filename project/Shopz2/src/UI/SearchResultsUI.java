package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FillLayout;

public class SearchResultsUI extends Composite implements View{
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchResultsUI(Composite parent, int style) {
		super(parent, style);
		//setLayout(new FillLayout());
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 105, 14);
		lblNewLabel.setText("Search Results");
		
		//this allows for scrolling, set adapt 1 composite and allow scroll if that gets too big
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 30, ViewController.MAX_WIDTH-25, ViewController.MAX_HEIGHT-45);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		//this is the internal composite which does the expanding, in here you put your search entries
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
		
		
		ArrayList<ItemEntryUI> itemList = new ArrayList<ItemEntryUI>();
		for (int i = 0; i < 10 ; i++){
			//adds it to a list so we can track the item
			itemList.add(new ItemEntryUI(composite, SWT.NONE));
		}
		
		
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public  void resetView(){ 
		return; 
		
		//this is where you might need to refernce Search.java for the last search term.
		//since you can't transfer data to and from 
	}
}
