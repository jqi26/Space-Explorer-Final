package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import main.GameEnvironment;

/**
 * This class implements the window for viewing the status of each crew member
 * @author Jackie Qiu
 *
 */
public class CrewMemberStatusWindow {

	private JFrame window;
	/**
	 * Keeps track of the current state of the game
	 */
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public CrewMemberStatusWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeCrewMemberStatusWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel chooseMemberLabel = new JLabel("Which crew member would you like to view the status of?");
		chooseMemberLabel.setBounds(10, 11, 414, 14);
		window.getContentPane().add(chooseMemberLabel);
		
		JLabel statusLabel = new JLabel("");
		statusLabel.setVerticalAlignment(SwingConstants.TOP);
		statusLabel.setBounds(342, 36, 282, 184);
		window.getContentPane().add(statusLabel);
		
		JButton member1Button = new JButton(environment.getCurrentCrew().getCrew().get(0).getName());
		member1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("<html>" + environment.getCurrentCrew().getCrew().get(0).status().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			}
		});
		member1Button.setBounds(10, 33, 150, 23);
		window.getContentPane().add(member1Button);
		
		if (environment.getCurrentCrew().getCrew().size() > 1) {
			JButton member2Button = new JButton(environment.getCurrentCrew().getCrew().get(1).getName());
			member2Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					statusLabel.setText("<html>" + environment.getCurrentCrew().getCrew().get(1).status().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
				}
			});
			member2Button.setBounds(10, 67, 150, 23);
			window.getContentPane().add(member2Button);
		}
		
		if (environment.getCurrentCrew().getCrew().size() > 2) {
			JButton member3Button = new JButton(environment.getCurrentCrew().getCrew().get(2).getName());
			member3Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					statusLabel.setText("<html>" + environment.getCurrentCrew().getCrew().get(2).status().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
				}
			});
			member3Button.setBounds(10, 101, 150, 23);
			window.getContentPane().add(member3Button);
		}
		
		if (environment.getCurrentCrew().getCrew().size() > 3) {
			JButton member4Button = new JButton(environment.getCurrentCrew().getCrew().get(3).getName());
			member4Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					statusLabel.setText("<html>" + environment.getCurrentCrew().getCrew().get(3).status().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
				}
			});
			member4Button.setBounds(10, 135, 150, 23);
			window.getContentPane().add(member4Button);
		}
		
		JButton finishedButton = new JButton("Done");
		finishedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		finishedButton.setBounds(535, 227, 89, 23);
		window.getContentPane().add(finishedButton);
	}

}
