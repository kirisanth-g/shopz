package UI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class SearchResult extends Composite implements View {
	private Text txtSearchResults;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchResult(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		txtSearchResults = new Text(this, SWT.BORDER);
		txtSearchResults.setText("Search Results");
		txtSearchResults.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void resetView(){
		return;
	}

}
