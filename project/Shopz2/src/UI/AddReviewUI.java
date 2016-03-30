package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import LogicalLayer.Item;
import LogicalLayer.Review;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddReviewUI extends Composite implements View {
	private Text titleEntry;
	private Text descEntry;
	private Text numStars;
	private Button btnSaveChanges;
	private Label lblTitle, lblOfStars, lblDescription;
	
	//the item the review belongs to, called externally
	private Item item; 

	//private Review review;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AddReviewUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		lblTitle = new Label(this, SWT.NONE);

		titleEntry = new Text(this, SWT.BORDER);
		titleEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblOfStars = new Label(this, SWT.NONE);

		numStars = new Text(this, SWT.BORDER);
		numStars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblDescription = new Label(this, SWT.NONE);

		new Label(this, SWT.NONE);

		descEntry = new Text(this, SWT.BORDER);
		GridData gd_descEntry = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_descEntry.heightHint = 53;
		descEntry.setLayoutData(gd_descEntry);

		btnSaveChanges = new Button(this, SWT.NONE);
		btnSaveChanges.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// save all the fields to the db
				saveChanges(); 
				
			}
		});

		new Label(this, SWT.NONE);
	//	resetView();

	}

	public void resetView() {
	//	System.out.println("does reset view get called");
		btnSaveChanges.setText("Save Changes");
		lblDescription.setText("Description");
		lblOfStars.setText("# of stars");
		lblTitle.setText("Title");

		titleEntry.setText("");
		numStars.setText("");
		descEntry.setText("");
	}

	public void saveChanges(){
		Integer stars; 
		String desc =  descEntry.getText();
		String title = titleEntry.getText();
		
		try{
			stars = Integer.parseInt(numStars.getText());
		}
		catch (NumberFormatException e){
			System.out.println("cannot process stars, please retry");
			return;
		}
		//creeate the new review object and save it to the cart
		//save to the db
		///maybe do the pub date internally
		Item.getItemSelected().writeReview(title, "",stars, desc);
		ViewController.prevView();
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
