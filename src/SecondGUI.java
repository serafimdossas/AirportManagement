import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SecondGUI extends JFrame{
	
	private JPanel panel;
	private JButton button, button2;
	private JTextField destinationAirportField;
	private JTextArea airportArea, codeArea, cityArea, countryArea, companies, direct, indirect;
	private String firstCity, secondCity;
	
	public SecondGUI(String aCity) {
		firstCity = aCity;
		panel = new JPanel();
		
		airportArea = new JTextArea(1, 8);
		codeArea =	   new JTextArea(1, 8);
		cityArea =    new JTextArea(1, 8);
		countryArea = new JTextArea(1, 8);
		destinationAirportField = 
					   new JTextField("                            ");
		
		companies = new JTextArea(5,10);
		direct = new JTextArea(8,30);
		indirect = new JTextArea(8,30);
		
		
		panel.add(airportArea);
		panel.add(codeArea);
		panel.add(cityArea);
		panel.add(countryArea);
		panel.add(companies);
		
		panel.add(destinationAirportField);
		
		button = new JButton("    Find Flights    ");
		panel.add(button);
		
		button2 = new JButton("   Back to search screen   ");
		
		panel.add(direct);
		panel.add(indirect);

		panel.add(button2);
		

		airportArea.append(CentralRegistry.getAirport(aCity).getName());
		codeArea.append(CentralRegistry.getAirport(aCity).getCode());
		cityArea.append(CentralRegistry.getAirport(aCity).getCity());
		countryArea.append(CentralRegistry.getAirport(aCity).getCountry());
		companies.append(CentralRegistry.getAirlines(CentralRegistry.getAirport(aCity)));
		
		
		
		this.setContentPane(panel);	
		
		ButtonListenerDirect findFlights= new ButtonListenerDirect();
		button.addActionListener(findFlights);
		OtherButtonListener backToSearch = new OtherButtonListener();
		button2.addActionListener(backToSearch);
		
		this.setVisible(true);
		this.setSize(520, 500);
		//this.pack();
		this.setTitle("Airport Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class ButtonListenerDirect implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (firstCity.equals(destinationAirportField.getText())) {
				JOptionPane.showMessageDialog(null, "Arrival and departure city cannot be the same!");
			}
			else 
			{
				direct.append(CentralRegistry.getDirectFlightsDetails(CentralRegistry.getAirport(firstCity), CentralRegistry.getAirport(destinationAirportField.getText())) + "\n");
				indirect.append(CentralRegistry.getInDirectFlightsDetails(CentralRegistry.getAirport(firstCity), CentralRegistry.getAirport(destinationAirportField.getText())) + "\n");
			
			}
		}
	}
	
	class OtherButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new GUI();
		}
	}
}
	


