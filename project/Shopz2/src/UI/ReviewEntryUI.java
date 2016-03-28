package UI;
import LogicalLayer.Login;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ReviewEntryUI extends Composite {
	int stars;
	private Text reviewBody;
	private Button btneditAdmin;
	private Label lblTitle;
	private Label lblStarsOut;
	private Button btnSaveChanges;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ReviewEntryUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
				
				lblTitle = new Label(this, SWT.NONE);
				lblTitle.setText("Title");
				
				lblStarsOut = new Label(this, SWT.NONE);
				lblStarsOut.setText("__ stars out of 5");
		
				
		
				reviewBody = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
				GridData gd_text = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
				gd_text.widthHint = 119;
				gd_text.heightHint = 52;
				reviewBody.setLayoutData(gd_text);
		
				btneditAdmin = new Button(this, SWT.NONE);
				btneditAdmin.setEnabled(false);
				
				btnSaveChanges = new Button(this, SWT.NONE);
				btnSaveChanges.setText("Save Changes");
				btneditAdmin.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						editMode();
						reviewBody.setEditable(true);
					}
				});

		// sample call
		resetView();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void editMode(){ 
		//toggles the buttons and makes the 
		reviewBody.setEditable(true);
		btneditAdmin.setEnabled(false);
		btnSaveChanges.setEnabled(true);
	}

	public void resetView() {
		btneditAdmin.setEnabled(Login.getCurrentUser().isAdmin());
		reviewBody.setText("This is supposed to be a really long line of text. Supports auto "
				+ "wrapping and scrolling But i don't if this wokrs, I guess I have to type"
				+ " a little bit more to see what will happen on this text bar. I'm running out of"
				+ " energy at this rate. all i neeed now is a little more text");
		btneditAdmin.setText("Edit Review");
	}

}
