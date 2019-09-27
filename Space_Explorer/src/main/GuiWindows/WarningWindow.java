package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import main.GameEnvironment;

import javax.swing.JScrollPane;

/**
 * This class implements the window for warning the user about various obstacles in the game such as
 * a random event or when a crew members hunger or tiredness gets too high
 * @author Jackie Qiu
 *
 */
public class WarningWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public WarningWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeWarningWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel randomEventLabel = new JLabel("");
		randomEventLabel.setVerticalAlignment(SwingConstants.TOP);
		randomEventLabel.setBounds(10, 11, 614, 102);
		window.getContentPane().add(randomEventLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel adjustmentsLabel = new JLabel("");
		scrollPane.setViewportView(adjustmentsLabel);
		adjustmentsLabel.setVerticalAlignment(SwingConstants.TOP);
		
		if (environment.getRandomEventMessage() != "") {
			randomEventLabel.setText("<html>" + environment.getRandomEventMessage().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		}
		
		if (environment.getAdjustmentsMessage().trim().length() > 0) {
			if (environment.getRandomEventMessage() != "") {
				scrollPane.setBounds(10, 127, 614, 89);
			}
			else {
				scrollPane.setBounds(10, 11, 614, 102);
			}
			window.getContentPane().add(scrollPane);
			adjustmentsLabel.setText("<html>" + environment.getAdjustmentsMessage().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		}
		
		JButton btnBack = new JButton("OK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnBack.setBounds(535, 227, 89, 23);
		window.getContentPane().add(btnBack);
		
	}
}
