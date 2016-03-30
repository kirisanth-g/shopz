package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import LogicalLayer.Item;
import UI.ViewController.ViewID;

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
	private Button btnViewReviews;
	private Button btnAddReview;
	
	private Item item;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ItemEntryUI(Composite parent, int style, Item item)  {
		super(parent, style);
		this.item = item; 
		setLayout(new GridLayout(6, false));
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 6, 1);
		gd_label.widthHint = 658;
		label.setLayoutData(gd_label);

		itemId = new Label(this, SWT.NONE);

		category = new Label(this, SWT.NONE);
				new Label(this, SWT.NONE);
				
				btnViewReviews = new Button(this, SWT.NONE);
				btnViewReviews.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						ViewController.switchView(ViewID.REVIEWS_LIST);
					}
				});
				btnViewReviews.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				
				btnAddReview = new Button(this, SWT.NONE);
				btnAddReview.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						ViewController.switchView(ViewController.ViewID.ADD_REVIEW);
						
					}
				});
			
		
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
				new Label(this, SWT.NONE);
				new Label(this, SWT.NONE);
		
				price = new Label(this, SWT.NONE);
				price.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		manufacturer = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		desc = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_text = new GridData(SWT.FILL, SWT.TOP, true, false, 4, 1);
		gd_text.widthHint = 119;
		gd_text.heightHint = 52;
		desc.setLayoutData(gd_text);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		// sample call
		resetView();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void resetView() {
		itemId.setText(item.getItemID());
		itemName.setText(item.getName());
		manufacturer.setText(item.getManu());
		category.setText(item.getCateg());
		price.setText("$" + String.format("%.2f", item.getPrice()));
		desc.setText(item.getDesc());
		btnAddToCart.setText("Add to Cart");
		btnViewReviews.setText("Show Reviews");
		btnAddReview.setText("Add Review");
	}

}
