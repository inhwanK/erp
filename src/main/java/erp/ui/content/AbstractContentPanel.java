package erp.ui.content;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractContentPanel<T> extends JPanel {


	public abstract void setItem(T item);

	public abstract T getItem();

	public abstract void validCheck();

	public abstract void clearTf();

	

}
