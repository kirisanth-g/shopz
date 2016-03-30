package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import LogicalLayer.Login;
import LogicalLayer.Search;
import UI.ViewController.ViewID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class SearchUI extends Composite implements View {
	private Text text;
	private Button btnSearch, btnShoppingCart, btnAccountInfo;
	private Button btnAddToShop;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		new Label(this, SWT.NONE);
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		
		btnSearch = new Button(this, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//please print out what I have typed in the bar
				Search.search(text.getText());
				//call LLayer.search query to preload into
				ViewController.switchView(ViewController.ViewID.SEARCH_RES);
			}
		});
		new Label(this, SWT.NONE);
		

		
		btnShoppingCart = new Button(this, SWT.NONE);
		btnShoppingCart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//switch to the shopping cart view
				ViewController.switchView(ViewController.ViewID.SHOPPING_CART);
			}
		});
		new Label(this, SWT.NONE);
		
		btnAccountInfo = new Button(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		btnAddToShop = new Button(this, SWT.NONE);
		btnAddToShop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//go into the edit shop view
				ViewController.switchView(ViewID.ADMIN_ITEM_ADD);
			}
		});
		
		btnAccountInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ViewController.switchView(ViewController.ViewID.ACCOUNT_SETTINGS);
			}
		});
		
		//resetView();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void resetView() {

		// enable the admin button

		btnAddToShop.setEnabled(Login.getCurrentUser().isAdmin());

		text.setText("Search Term");
		btnShoppingCart.setText("Shopping Cart");
		btnAccountInfo.setText("Account Info");
		btnSearch.setText("Search");

		btnAddToShop.setText("Add to Shop");

	}

}
