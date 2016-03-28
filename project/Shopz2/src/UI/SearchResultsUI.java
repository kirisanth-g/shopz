package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SearchResultsUI extends Composite implements View{

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchResultsUI(Composite parent, int style) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 59, 14);
		lblNewLabel.setText("Search Results");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 30, 430, 260);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Label lblTest = new Label(scrolledComposite, SWT.NONE);
		Label lblTest2 = new Label(scrolledComposite, SWT.NONE);
		Label lblTest3 = new Label(scrolledComposite, SWT.NONE);
		Label lblTest4 = new Label(scrolledComposite, SWT.NONE);
		lblTest4.setAlignment(SWT.CENTER);
		lblTest.setText("test");
		lblTest2.setText("test");
		lblTest3.setText("test");
		lblTest4.setText("test");
		scrolledComposite.setContent(lblTest);
		scrolledComposite.setContent(lblTest2);
		scrolledComposite.setContent(lblTest3);
		scrolledComposite.setContent(lblTest4);
		scrolledComposite.setMinSize(lblTest.computeSize(SWT.DEFAULT, SWT.DEFAULT));

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
