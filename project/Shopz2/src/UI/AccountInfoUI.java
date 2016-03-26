package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class AccountInfoUI extends Composite implements View {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AccountInfoUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Label lblAccountInfo = new Label(this, SWT.NONE);
		lblAccountInfo.setText("Account info");
		
		Label lblBillingInfo = new Label(this, SWT.NONE);
		lblBillingInfo.setText("Billing Info:");
		new Label(this, SWT.NONE);
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setText("Name");
		
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCreditCard = new Label(this, SWT.NONE);
		lblCreditCard.setText("Credit Card #");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblExpDate = new Label(this, SWT.NONE);
		lblExpDate.setText("Exp Date");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCcv = new Label(this, SWT.NONE);
		lblCcv.setText("CCV");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCardType = new Label(this, SWT.NONE);
		lblCardType.setText("Card Type");
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
public void resetView(){
	//this is where you pull from the db and fill in all the info
}
	
}
