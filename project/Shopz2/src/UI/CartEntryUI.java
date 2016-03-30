package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import LogicalLayer.Item;
import LogicalLayer.Login;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CartEntryUI extends Composite {
	private Text numberOrdered;
	private Label itemName, price, quant;
	private Button btnRemove;
	
	private Item itemOrdered;
	private Integer quantity;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CartEntryUI(Composite parent, int style, Item itemOrdered, Integer quantity) {
		super(parent, style);
		
		this.itemOrdered = itemOrdered;
		this.quantity = quantity;
		setLayout(new GridLayout(7, false));
		
		itemName = new Label(this, SWT.NONE);
		
		new Label(this, SWT.NONE);
		
		price = new Label(this, SWT.NONE);
		
		new Label(this, SWT.NONE);
		
		numberOrdered = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		numberOrdered.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		
		quant = new Label(this, SWT.NONE);
		quant.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		btnRemove = new Button(this, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//WRITE THE REMOVE BUTTON HERE 
				Login.getCurrentUser().removeFromCart(itemOrdered.getItemID(), 1);
				resetView();
			}
		});


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void resetView(){
		itemName.setText(itemOrdered.getName());
		price.setText("$" + String.format("%.2f", itemOrdered.getPrice()));
		quant.setText("#");
		numberOrdered.setText(quantity.toString());
		//STILL HAVE TO IMPLEMENT THIS
		btnRemove.setText("Remove");
		
	}

}
