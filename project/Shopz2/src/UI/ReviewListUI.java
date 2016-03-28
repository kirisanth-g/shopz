package UI;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ReviewListUI extends Composite implements View{
	private Label title;

	public ReviewListUI(Composite parent, int style) {
		super(parent, style);
		//setLayout(new FillLayout());
		title = new Label(this, SWT.NONE);
		title.setBounds(10, 10, 105, 14);
		
		
		//this allows for scrolling, set adapt 1 composite and allow scroll if that gets too big
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 30, ViewController.MAX_WIDTH-25, ViewController.MAX_HEIGHT-45);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		//this is the internal composite which does the expanding, in here you put your search entries
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
		
		
		ArrayList<ReviewEntryUI> itemList = new ArrayList<ReviewEntryUI>();
		for (int i = 0; i < 10 ; i++){
			//adds it to a list so we can track the item 
			//becuase you are creating the entyies here, feed all the params via constructor
			itemList.add(new ReviewEntryUI(composite, SWT.NONE));
		}
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		resetView(); 
	}
	@Override
	public void resetView() {
		title.setText("Reviews for ______");
		

	}
}

