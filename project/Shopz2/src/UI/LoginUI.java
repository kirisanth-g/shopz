package UI;
import LogicalLayer.Login;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class LoginUI extends Composite implements View {
	private Text textUsername;
	private Text txtPassword;
	private Label lblShopzLogin;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LoginUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		lblShopzLogin = new Label(this, SWT.NONE);
		lblShopzLogin.setText("Shopz Login ");
		
		textUsername = new Text(this, SWT.BORDER);
		//textUsername.setText("Username");
		textUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		
		txtPassword = new Text(this, SWT.PASSWORD |SWT.BORDER);
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtPassword.setText("");
			}
		});
		//txtPassword.setText("Password");
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		resetView();
		Button btnLogin = new Button(this, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//when the login button is presse
				if (Login.authenticate(textUsername.getText(),txtPassword.getText())){
					ViewController.switchView(ViewController.ViewID.SEARCH);
				}
			}
		});
		btnLogin.setText("Login");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void resetView(){
		textUsername.setText("Username");
		txtPassword.setText("Passowrd");
	}

}
