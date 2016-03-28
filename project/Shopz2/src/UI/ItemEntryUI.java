package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ItemEntryUI extends Composite implements View {
	private Label itemName, manufacturer, category, itemId, price;
	private Text desc;
	private Button btnAddToCart;
	private Label label;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ItemEntryUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(4, false));
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1);
		gd_label.widthHint = 658;
		label.setLayoutData(gd_label);

		itemId = new Label(this, SWT.NONE);

		category = new Label(this, SWT.NONE);
				new Label(this, SWT.NONE);
		
				btnAddToCart = new Button(this, SWT.NONE);
				btnAddToCart.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				btnAddToCart.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						// put add to cart function here
					}
				});

		itemName = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
				new Label(this, SWT.NONE);
		
				price = new Label(this, SWT.NONE);
				price.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		manufacturer = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		desc = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_text = new GridData(SWT.FILL, SWT.TOP, true, false, 3, 1);
		gd_text.widthHint = 119;
		gd_text.heightHint = 52;
		desc.setLayoutData(gd_text);
		new Label(this, SWT.NONE);

		// sample call
		resetView();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void resetView() {
		itemId.setText("Item ID");
		itemName.setText("itemName");
		manufacturer.setText("Manufacturer");
		category.setText("category");
		price.setText("price");
		desc.setText("This is supposed to be a really long line of text. Supports auto "
				+ "wrapping and scrolling But i don't if this wokrs, I guess I have to type"
				+ " a little bit more to see what will happen on this text bar. I'm running out of"
				+ " energy at this rate. all i neeed now is a little more text");
		btnAddToCart.setText("Add to Cart");
	}

}
