import javax.swing.*;
import java.awt.*;

/**
 * Main class to set up the GUI for the GitHubViz application.
 *
 * @author javiergs
 * @version 1.0
 */
public class Main extends JFrame {
	
	public Main() {
		JTextField urlField = new JTextField();
		JButton okButton = new JButton("OK");
		JPanel gridPanel = new GridPanel();
		TheNanny controller = new TheNanny(urlField);
		JPanel top = new JPanel(new BorderLayout());
		top.add(new JLabel("URL:"), BorderLayout.WEST);
		top.add(urlField, BorderLayout.CENTER);
		top.add(okButton, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(top, BorderLayout.NORTH);
		add(gridPanel, BorderLayout.CENTER);
		okButton.addActionListener(controller);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.setTitle("GitHub Code Visualizer");
		main.setSize(800, 600);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
	}
	
}
