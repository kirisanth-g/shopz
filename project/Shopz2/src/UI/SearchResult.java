package UI;

import org.eclipse.swt.widgets.Composite;

public class SearchResult extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchResult(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
