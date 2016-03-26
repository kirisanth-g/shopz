package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class AccountInfoUI extends Composite implements View {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AccountInfoUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
public void resetView(){
	//this is where you pull from the db and fill in all the info
}
	
}
