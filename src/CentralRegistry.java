import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CentralRegistry {

	private static ArrayList<Airport> airports = new ArrayList<Airport>();
	private static ArrayList<Flight> flights = new ArrayList<Flight>();
	
	public CentralRegistry() {
		
	}
	
	public static void addAirport(Airport anAirport) {
		airports.add(anAirport);
	}
	
	public static void addFlight(Flight aFlight) {
		flights.add(aFlight);
	}
	
	public static Airport getAirport(String cityName) {
		for (Airport airports: airports) {
			if (airports.getCity().equals(cityName)) {
				return airports;
			}
		}
		return null;
	}
	
	public static String getAirlines(Airport a) {
		
		ArrayList<Flight> airlines = new ArrayList<Flight>();	
		ArrayList<String> companies = new ArrayList<String>();
		Set<String> hs = new HashSet<>();
		
		String ans = "";
		
		for (Flight flight: flights) {
			if (flight.getAirportA2().equals(a) || flight.getAirportA1().equals(a)) {
					airlines.add(flight);
			}
			else if(flight.getAirportA1().equals(a) || flight.getAirportA2().equals(a)) { 
					airlines.add(flight);
			}
		}
		
		for (Flight airlines1: airlines) {
			companies.add(airlines1.getAirline());
		}
		hs.addAll(companies);
		companies.clear();
		companies.addAll(hs);
		Collections.sort(companies);
		
		for (String companies1: companies) {
			ans += companies1 + "\n";
		}
		
		return ans;
	}
	

	public static String getDirectFlightsDetails(Airport a, Airport b) {

		ArrayList<Flight> companies = new ArrayList<Flight>();	
		String ans = "";
		int i=0;
		
		for (Flight flight: flights) {
			if (flight.getAirportA2().equals(b)) {
				if (flight.getAirportA1().equals(a)) 
					companies.add(flight);
			}
			else if(flight.getAirportA1().equals(a)) {
					if (flight.getAirportA2().equals(b)) 
					companies.add(flight);
			}
		}
		if (companies.size()>0)
			ans = "DIRECT FLIGHT DETAILS" + "\n";
		else 
			ans = "No direct flights between the 2 airports";
		
		for (Flight companies1: companies) {
			i++;
			ans += "[" + i + "] " + companies1.toString() + "\n";
		}
		
		return ans;
		
	}

	public static String getInDirectFlightsDetails(Airport a, Airport b) {

		Airport stop;
		String ans="INDIRECT FLIGHTS through.. \n";
		int i=0;
		
		for (Flight flightOut: flights) {
			if (flightOut.getAirportA1().equals(a)) {
				stop=flightOut.getAirportA2();
				if (CentralRegistry.directly(stop, b.getName())) {
					i++;
					ans += "[" + i + "] " + stop.getCity() + ", " + stop.getCode() + " airport \n";
				}
			}
			else if (flightOut.getAirportA2().equals(b)) {
				stop=flightOut.getAirportA1();
				if (CentralRegistry.directly(stop, a.getName())) {
					i++;
					ans += "[" + i + "] " + stop.getCity() + ", " + stop.getCode() + " airport \n";
				}
			}
		}
		
		if (i==0)
			ans="No indirect flights between the 2 airports";
		
		
		return ans;
		
	}
	
	public static Airport getLargestHub() {
		Airport largest;
		int[] hub = new int[6];
		
		for (int i=0;i<6;i++)
			hub[i]=0;
		
		int k=0;
		
		for (Flight flights: flights) {
			if (flights.getA2().equals("Orly"))
				hub[k]++;
		}
		k++;
		
		for (Flight flights: flights) {
			if (flights.getA2().equals("Fiumicino"))
				hub[k]++;
		}
		k++;
		
		for (Flight flights: flights) {
			if (flights.getA2().equals("Venizelos"))
				hub[k]++;
		}
		k++;
		
		for (Flight flights: flights) {
			if (flights.getA2().equals("Macedonia"))
				hub[k]++;
		}
		k++;
		
		for (Flight flights: flights) {
			if (flights.getA2().equals("MunichAirport"))
				hub[k]++;
		}
		k++;
		
		for (Flight flights: flights) {
			if (flights.getA2().equals("Charleroi"))
				hub[k]++;
		}
		
		int maxHub = hub[0];
		int maxPos = 0;
		
		for (int j=1;j<6;j++) {
			if (hub[j]>maxHub) {
				maxHub = hub[j];
				maxPos = j;
			}
		}
		
		if (maxPos==0)
			largest =  new Airport("Orly", "ORY", "Paris", "France");
		else if (maxPos==1)
			largest = new Airport("Fiumicino", "FCO", "Rome", "Italy");
		else if (maxPos==2)
			largest = new Airport("Venizelos", "ATH", "Athens", "Greece");
		else if (maxPos==3)
			largest = new Airport("Macedonia", "SKG", "Thessaloniki", "Greece");
		else if (maxPos==4)
			largest = new Airport("MunichAirport", "MUC", "Munich", "Germany");
		else
			largest = new Airport("Charleroi", "CRL", "Brussels", "Belgium");
		
		return largest;
	
	}

	public static Flight getLongestFlight() {
		int maxMinutes = 0;
		Flight f;
		
		for(Flight flights: flights) {
			if (flights.getMinutes() > maxMinutes)
				maxMinutes = flights.getMinutes();				
		}
		
		for (Flight flights: flights) {
			if (flights.getMinutes() == maxMinutes) {
				f = new Flight(flights.getAirportA1(), flights.getAirportA2(), flights.getMinutes(), flights.getAirline());
				return f;
			}
		}
		
		return null;
	}
	
	
	public static void printCompanies(Airport anAirport) {
		for (Flight flights: flights) {
			if (flights.getAirportA1().equals(anAirport) || flights.getAirportA2().equals(anAirport))
				System.out.println(flights.getAirline() + "\n");
		}
	}
	
	public static ArrayList<Airport> commonConnections(Airport anAirport, String aName) {
		ArrayList<Airport> venizelos = new ArrayList<>();
		ArrayList<Airport> fiumicino = new ArrayList<>();
		ArrayList<Airport> common = new ArrayList<>();
		
		for (Flight flight: flights) {
			if (flight.getA2().equals(aName)) {
				venizelos.add(flight.getAirportA1());
			}
			else if (flight.getA1().equals(aName)) {
				venizelos.add(flight.getAirportA2());
			}
		}
		
		for (Flight flight: flights) {
			if (flight.getAirportA1().equals(anAirport)) {
				fiumicino.add(flight.getAirportA2());				
			}
			else if (flight.getAirportA2().equals(anAirport)) {
				fiumicino.add(flight.getAirportA1());	
			}
		}
		
		for (Airport veni: venizelos) {
			for (Airport fiumi: fiumicino) {
				if (veni.getName().equals(fiumi.getName())) {
					common.add(veni);
					
				}
			}
		}
		
		return common;
		
	}
	
	public static boolean directly(Airport anAirport, String aName) {
		
		for (Flight flight: flights) {
			if (flight.getA2().equals(aName)) {
				if (flight.getAirportA1().equals(anAirport)) 
					return true;
			}
			else if(flight.getA1().equals(aName)) {
				if (flight.getAirportA2().equals(anAirport)) 
					return true;
			}
		}
		
		return false;
	}
	
	public static boolean inDirectly(Airport anAirport, String aName) {
		
		for (Flight flight: flights) {
			if (flight.getA1().equals(aName)) {
					if (CentralRegistry.directly(anAirport, flight.getA2())) {
						return true;
					}
			}
			else if (flight.getA2().equals(aName)) {
				if (CentralRegistry.directly(anAirport, flight.getA1())) {
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	public static boolean exists(String aCity) {
		for (Airport airport: airports) {
			if (airport.getCity().equals(aCity))
				return true;
		}
		return false;
	}
	
}
