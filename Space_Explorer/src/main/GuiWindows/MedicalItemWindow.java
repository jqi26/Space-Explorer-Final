package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import main.GameEnvironment;
import main.MedicalItem;
import main.MedicalItems.Bandage;
import main.MedicalItems.MedPack;
import main.MedicalItems.SpacePlagueCure;

/**
 * This class implements the window for allowing the user to view and purchase medical items
 * @author Jackie Qiu
 *
 */
public class MedicalItemWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public MedicalItemWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeMedicalItemWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new MigLayout("", "[264.00px][409.00][]", "[1px][][231.00][]"));
		
		JLabel chosenItemLabel = new JLabel("");
		chosenItemLabel.setVerticalAlignment(JLabel.TOP);
		window.getContentPane().add(chosenItemLabel, "cell 0 2");
		
		JLabel newInventoryLabel = new JLabel("");
		newInventoryLabel.setVerticalAlignment(JLabel.TOP);
		window.getContentPane().add(newInventoryLabel, "cell 1 2");
		
		JComboBox<MedicalItem> medicineList = new JComboBox<MedicalItem>();
		window.getContentPane().add(medicineList, "cell 0 1");
		Bandage newBandage = new Bandage();
		MedPack newMedPack = new MedPack();
		SpacePlagueCure newSpacePlagueCure = new SpacePlagueCure();
		
		medicineList.addItem(newBandage);
		medicineList.addItem(newMedPack);
		medicineList.addItem(newSpacePlagueCure);
		
		MedicalItem chosenMedicine = (MedicalItem)medicineList.getSelectedItem();
		chosenItemLabel.setText("<html>Name: " + chosenMedicine + "<br>Increases health by: " + chosenMedicine.getHealAmount() + "%<br> Price: $" + chosenMedicine.getPrice() + "<br>Would you like to buy it?</html>");
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicalItem chosenMedicine = (MedicalItem)medicineList.getSelectedItem();
				if (environment.getCurrentCrew().getBalance() >= chosenMedicine.getPrice()) {
					environment.getCurrentCrew().buyItem(chosenMedicine);
					newInventoryLabel.setText("<html>" + environment.getCurrentCrew().showInventory().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
				}
				else {
					newInventoryLabel.setText("You can't afford this!");
				}
			}
		});
		window.getContentPane().add(buyButton, "cell 0 3");	
		
		JButton exitButton = new JButton("Back");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		window.getContentPane().add(exitButton, "cell 2 3");
		
		medicineList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicalItem chosenMedicine = (MedicalItem)medicineList.getSelectedItem();
				chosenItemLabel.setText("<html>Name: " + chosenMedicine + "<br>Increases health by: " + chosenMedicine.getHealAmount() + "%<br> Price: $" + chosenMedicine.getPrice() + "<br>Would you like to buy it?</html>");
			}
		});
	}

}
