
public class Flight {
	private Airport a1;
	private Airport a2;
	private int minutes;
	private String Airline;
	
	public Flight(Airport anAirport, Airport aSecondAirport, int time, String anAirline) {
		this.a1 = anAirport;
		this.a2 = aSecondAirport;
		this.minutes = time;
		this.Airline = anAirline;
	}

	public String getA1() {
		return a1.getName();
	}

	public String getA2() {
		return a2.getName();
	}
	
	public Airport getAirportA1() {
		return a1;
	}

	public Airport getAirportA2() {
		return a2;
	}

	public int getMinutes() {
		return minutes;
	}

	public String getAirline() {
		return Airline;
	}
	
	public String toString() {
		return "Flight operated by " + Airline + ", duration " + minutes + " minutes";
	}
	
	public void printFlighData() {
		System.out.println("From " + this.a1.getName() + "/" + this.a1.getCity() + 
				" to " + this.a2.getName() + "/" + this.a2.getCity() + 
				" in " + this.minutes + " minutes with " + this.Airline);		
	}

}
