import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Vector;

/**
 * Blackboard class to manage squares and notify listeners about state changes.
 *
 * @author javiergs
 * @version 1.0
 */
public class Blackboard extends PropertyChangeSupport {
	
	private static Blackboard instance;
	private Vector<Square> squares;
	private boolean ready = false;
	private boolean loading = false;
	
	private Blackboard() {
		super(new Object());
		squares = new Vector<>();
	}
	
	public static Blackboard getInstance() {
		if (instance == null) {
			instance = new Blackboard();
		}
		return instance;
	}
	
	public void loadFromUrl(String url) {
		try {
			DelegateMetrics delegate = new DelegateMetrics(url);
			Thread t = new Thread(delegate);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addSquare(Square square) {
		squares.add(square);
	}
	
	public void setReady() {
		ready = true;
		firePropertyChange("blackboardReady", false, true);
	}
	
	public void setLoading(boolean loading) {
		ready = loading;
		firePropertyChange("blackboardLoading", false, true);
	}
	
	public List<Square> getSquares() {
		return squares;
	}
	
	public void clear() {
		squares.clear();
		ready = false;
		loading = false;
	}
}
