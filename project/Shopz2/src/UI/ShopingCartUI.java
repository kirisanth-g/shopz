package UI;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ShopingCartUI extends Composite implements View  {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	private Label lblTitle, total;
	private ScrolledComposite scrolledComposite;
	private Composite composite;
	private Text totalPrice;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ShopingCartUI(Composite parent, int style) {
		super(parent, style);
		//setLayout(new FillLayout());
		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setBounds(10, 10, 105, 14);
		
		total = new Label(this, SWT.NONE);
		total.setBounds(10, ViewController.MAX_HEIGHT-100, 59, 20);
		
		//this allows for scrolling, set adapt 1 composite and allow scroll if that gets too big
		scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 30, ViewController.MAX_WIDTH-25, ViewController.MAX_HEIGHT-45-100);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		//this is the internal composite which does the expanding, in here you put your search entries
		composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));	
		
		totalPrice = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		totalPrice.setBounds(56, 497, 64, 19);
		
		
		
		resetView();
		
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		
		


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public  void resetView(){ 
		lblTitle.setText("Shopping Cart");
		total.setText("TOTAL:");
		totalPrice.setText("$5.00");
		
		
		ArrayList<CartEntryUI> itemList = new ArrayList<CartEntryUI>();
		for (Control child : composite.getChildren()) {
			child.dispose();
		}
		for (int i = 0; i < 10 ; i++){
			CartEntryUI searchItemPanel = new CartEntryUI(composite, SWT.BORDER);
			searchItemPanel.resetView();
			itemList.add(searchItemPanel);
		}
		composite.layout();
		
		//this is where you might need to reference Search.java for the last search term.
		//since you can't transfer data to and from 
	}
}
