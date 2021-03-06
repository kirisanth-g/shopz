package UI;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import LogicalLayer.Item;
import LogicalLayer.Review;

public class ReviewListUI extends Composite implements View {
	private Label title;
	private Composite composite;
	ArrayList<ReviewEntryUI> itemList = new ArrayList<ReviewEntryUI>();

	public ReviewListUI(Composite parent, int style) {
		super(parent, style);
		// setLayout(new FillLayout());
		title = new Label(this, SWT.NONE);
		title.setBounds(10, 10, 105, 14);

		// this allows for scrolling, set adapt 1 composite and allow scroll if
		// that gets too big
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 30, ViewController.MAX_WIDTH - 25, ViewController.MAX_HEIGHT - 45);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		// this is the internal composite which does the expanding, in here you
		// put your search entries
		composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));

		//resetView();
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

	@Override
	public void resetView() {
		Item citem = Item.getItemSelected();
		title.setText("Reviews for: " + citem.getName());

		List<Review> reviewList = Item.getItemSelected().getReviews(); 
		ArrayList<ReviewEntryUI> panelList = new ArrayList<ReviewEntryUI>();
		// normally grab all the Item objects for logical layer and add them in
		// here
		
		// this should remove all the old children before rebuilding the new
		// ones
		for (Control child : composite.getChildren()) {
			child.dispose();
		}
		for (Review review : reviewList){
			ReviewEntryUI reviewItemPanel = new ReviewEntryUI(composite, SWT.NONE, review);
			reviewItemPanel.resetView();
			panelList.add(reviewItemPanel);
		}
		//for (int i = 0; i < 10; i++) {
		//	ReviewEntryUI reviewEntry = new ReviewEntryUI(composite, SWT.NONE);
		//	System.out.println("rebuilding Review NEtry ");
		//	reviewEntry.resetView();
		//	itemList.add(reviewEntry);
		//}
		composite.layout();
		//System.out.println(composite.getChildren());

	}
}
