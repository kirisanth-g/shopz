package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class CartEntryUI extends Composite {
	private Text numberOrdered;
	private Label itemName, price, quant;
	private Button btnRemove;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CartEntryUI(Composite parent, int style) {
		super(parent, style);
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


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void resetView(){
		itemName.setText("Item Name");
		price.setText("price");
		quant.setText("#");
		numberOrdered.setText("5");
		btnRemove.setText("Remove");
		
	}

}
