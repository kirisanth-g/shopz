package UI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import LogicalLayer.Item;

public class AdminAddItemUI extends Composite implements View {
	private Label itemName, manufacturer, category, price;
	private Text descBody;

	private Button btnAddToShop;
	private Label label;
	private Label lblDescription;
	private Text itemNameEntry;
	private Text categoryEntry;
	private Text ManufacturerEntry;
	private Text priceEntry;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AdminAddItemUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(4, false));

		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1);
		gd_label.widthHint = 658;
		label.setLayoutData(gd_label);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		itemName = new Label(this, SWT.NONE);

		itemNameEntry = new Text(this, SWT.BORDER);
		itemNameEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		category = new Label(this, SWT.NONE);

		categoryEntry = new Text(this, SWT.BORDER);
		categoryEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		manufacturer = new Label(this, SWT.NONE);

		ManufacturerEntry = new Text(this, SWT.BORDER);
		ManufacturerEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		price = new Label(this, SWT.NONE);

		priceEntry = new Text(this, SWT.BORDER);
		priceEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		lblDescription = new Label(this, SWT.NONE);

		descBody = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_text = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
		gd_text.widthHint = 403;
		gd_text.heightHint = 52;
		descBody.setLayoutData(gd_text);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		btnAddToShop = new Button(this, SWT.NONE);
		btnAddToShop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// put add to shop thing here
				createItem();
				// then exit the view yourself

			}
		});
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		// sample call
		//resetView();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void resetView() {
		itemName.setText("itemName");
		manufacturer.setText("Manufacturer");
		category.setText("category");
		price.setText("price");
		btnAddToShop.setText("Add to Shop");
		lblDescription.setText("Description");

		descBody.setText("");
		itemNameEntry.setText("");
		categoryEntry.setText("");
		ManufacturerEntry.setText("");
		priceEntry.setText("");
	}

	public void createItem() {
		float tempPrice;
		try {
			tempPrice = Float.parseFloat(priceEntry.getText());
		} catch (NumberFormatException e) {
			System.out.println("INvalid price");
			return;
		}
		Item.insertItem(itemName.getText(), manufacturer.getText(), descBody.getText(), categoryEntry.getText(),
				tempPrice);
		ViewController.prevView();
	}
}
