package UI;

import org.eclipse.swt.widgets.Composite;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import LogicalLayer.Login;
import LogicalLayer.User;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AccountInfoUI extends Composite implements View {
	private Text bName;
	private Text ccNum;
	private Text expDate;
	private Text ccvNum;
	private Text cardType;
	private Text sName;
	private Text streetAddress;
	private Text postalCode;
	private Text city;
	private Text country;
	private Button btnSaveChanges;
	private Button btnEdit;
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AccountInfoUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label lblAccountInfo = new Label(this, SWT.NONE);
		lblAccountInfo.setText("Account info");
		new Label(this, SWT.NONE);
		
		Label lblBillingInfo = new Label(this, SWT.NONE);
		lblBillingInfo.setText("Billing Info:");
		
		Label lblShippingInfo = new Label(this, SWT.NONE);
		lblShippingInfo.setText("Shipping Info:");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setText("Name");
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setText("Name");
		
		bName = new Text(this, SWT.BORDER);
		bName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		sName = new Text(this, SWT.BORDER);
		sName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCreditCard = new Label(this, SWT.NONE);
		lblCreditCard.setText("Credit Card #");
		
		Label lblStreetAddress = new Label(this, SWT.NONE);
		lblStreetAddress.setText("Street Address");
		
		ccNum = new Text(this, SWT.BORDER);
		ccNum.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		streetAddress = new Text(this, SWT.BORDER);
		streetAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblExpDate = new Label(this, SWT.NONE);
		lblExpDate.setText("Exp Date");
		
		Label lblCity = new Label(this, SWT.NONE);
		lblCity.setText("City");
		
		expDate = new Text(this, SWT.BORDER);
		expDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		city = new Text(this, SWT.BORDER);
		city.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCcv = new Label(this, SWT.NONE);
		lblCcv.setText("CCV");
		
		Label lblPostalCode = new Label(this, SWT.NONE);
		lblPostalCode.setText("Postal Code");
		
		ccvNum = new Text(this, SWT.BORDER);
		ccvNum.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		postalCode = new Text(this, SWT.BORDER);
		postalCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCardType = new Label(this, SWT.NONE);
		lblCardType.setText("Card Type");
		
		Label lblCountry = new Label(this, SWT.NONE);
		lblCountry.setText("Country");
		
		cardType = new Text(this, SWT.BORDER);
		cardType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		country = new Text(this, SWT.BORDER);
		country.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnEdit = new Button(this, SWT.NONE);
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editMode();
			}
		});
		btnEdit.setText("Edit");
		
		btnSaveChanges = new Button(this, SWT.NONE);
		btnSaveChanges.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValues();
				viewMode();
			}

		});
		btnSaveChanges.setText("Save Changes");	
		
		
		//set initially to read mod
		//resetView();
	}
	
	public void getValues(){
		//this is where you should get query the db and get the data
		User cuser = Login.getCurrentUser();
		cuser.load();
		bName.setText(cuser.getName());
		ccNum.setText(Integer.toString(cuser.getCardnum()));
		expDate.setText(cuser.getExpDate());
		ccvNum.setText(Integer.toString(cuser.getCcv()));
		cardType.setText(cuser.getCardType());
		sName.setText("This may not actually exist. Q_Q");
		streetAddress.setText(cuser.getAddress());
		postalCode.setText(cuser.getPostal());
		city.setText(cuser.getCity());
		country.setText(cuser.getCountry());
	}
	
	public void setValues(){
		bName.getText();
		ccNum.getText();
		expDate.getText();
		ccvNum.getText();
		cardType.getText();
		sName.getText();
		sName.getText();
		streetAddress.getText();
		postalCode.getText();
		city.getText();
		country.getText();
		
		System.out.println("Changes will be saved");
	}
	
	public void editMode(){
		btnSaveChanges.setEnabled(true);
		btnEdit.setEnabled(false);
		
		bName.setEditable(true);
		ccNum.setEditable(true);
		expDate.setEditable(true);
		ccvNum.setEditable(true);
		cardType.setEditable(true);
		sName.setEditable(true);
		streetAddress.setEditable(true);
		postalCode.setEditable(true);
		city.setEditable(true);
		country.setEditable(true);
	}
	public void viewMode(){
		btnSaveChanges.setEnabled(false);
		btnEdit.setEnabled(true);
		
		bName.setEditable(false);
		ccNum.setEditable(false);
		expDate.setEditable(false);
		ccvNum.setEditable(false);
		cardType.setEditable(false);
		sName.setEditable(false);
		streetAddress.setEditable(false);
		postalCode.setEditable(false);
		city.setEditable(false);
		country.setEditable(false);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
public void resetView(){
	//this is where you pull from the db and fill in all the info
	getValues();
	viewMode();
}

	
}
