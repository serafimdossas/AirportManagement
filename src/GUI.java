import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	private JPanel panel;
	private JButton button1;
	private JTextField airportField;
	
	public GUI() {
		
		airportField = new JTextField("                                        ");
		panel = new JPanel();
		panel.add(airportField);
		button1 = new JButton("Find");
		panel.add(button1);
		
		this.setContentPane(panel);		
		
		ButtonListener find= new ButtonListener();
		button1.addActionListener(find);
		
		this.setVisible(true);
		this.setSize(400, 200);
		//this.pack();
		this.setTitle("Find Airport");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String city = airportField.getText();
			if (CentralRegistry.exists(city)) {
				dispose();
				new SecondGUI(city);
			}
			else 
				JOptionPane.showMessageDialog(null, city + " does not have an airport");
		}
	}
	
}
