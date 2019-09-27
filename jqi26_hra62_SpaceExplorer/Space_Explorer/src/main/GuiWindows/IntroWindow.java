package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import main.GameEnvironment;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements the start up screen to introduce the player to the game
 * @author Jackie Qiu
 *
 */
public class IntroWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;

	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public IntroWindow(GameEnvironment incomingEnvironment) {
		environment = incomingEnvironment;
		initialize();
		window.setVisible(true);
	}
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		environment.closeIntroWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblSpaceExplorer = new JLabel("Space Explorer");
		lblSpaceExplorer.setForeground(new Color(186, 85, 211));
		lblSpaceExplorer.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		lblSpaceExplorer.setBounds(219, 11, 242, 44);
		window.getContentPane().add(lblSpaceExplorer);
		
		JLabel lblyouAndYour = new JLabel("<html>You and your crew are lost in space with your spaceship. Your spaceship has been broken and its pieces are scattered throughout the surrounding planets. You will need to find the missing pieces of your spaceship so that you can repair it and get home.");
		lblyouAndYour.setBounds(10, 58, 614, 71);
		window.getContentPane().add(lblyouAndYour);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setIcon(new ImageIcon(IntroWindow.class.getResource("/images/planet.jpg")));
		label.setBounds(10, 124, 242, 126);
		window.getContentPane().add(label);
		
		JButton btnPlay = new JButton("Play!");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnPlay.setForeground(new Color(186, 85, 211));
		btnPlay.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnPlay.setBounds(382, 124, 242, 126);
		window.getContentPane().add(btnPlay);
	}
}
