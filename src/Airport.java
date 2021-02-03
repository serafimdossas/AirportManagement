import java.util.ArrayList;

public class Airport {

	private String name;
	private String code;
	private String city;
	private String country;
	
	public Airport(String aName, String aCode, String aCity, String aCountry) {
		this.name = aName;
		this.code = aCode;
		this.city = aCity;
		this.country = aCountry;
	}
	
	public void Connected(Airport anAirport) {
		
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	
	public boolean isDirectlyConnectedTo(Airport anAirport) {
		return CentralRegistry.directly(anAirport, this.name);
	}
	
	public boolean isInDirectlyConnectedTo(Airport anAirport) {
		if (CentralRegistry.directly(anAirport, this.name))
			return false;
		else 
			return CentralRegistry.inDirectly(anAirport, this.name);
	}
	
	public void printCompanies(Airport anAirport) {
		CentralRegistry.printCompanies(anAirport);
	}
	
	public ArrayList<Airport> getCommonConnections(Airport anAirport){
		return CentralRegistry.commonConnections(anAirport, this.name);
	}

}
