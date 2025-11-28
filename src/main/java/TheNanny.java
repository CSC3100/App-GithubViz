import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle the action of loading a URL from the text field.
 *
 * @author javiergs
 * @version 1.0
 */
public class TheNanny implements ActionListener {
	
	private JTextField urlField;
	
	public TheNanny(JTextField field) {
		this.urlField = field;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String url = urlField.getText().trim();
		if (!url.isEmpty()) {
			Blackboard.getInstance().setLoading(true);
			Blackboard.getInstance().loadFromUrl(url);
		}
	}
	
}
