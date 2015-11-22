package javaCode.com.rumbo.flightSearch.commons;

public class Flight 
{
	private String origin;
	public String getOrigin() { return origin; }
	
	private String destination;
	public String getDestination() { return destination; }
	
	private String airline;
	public String getAirline() { return airline; }
	
	private Number basePrice;
	public Number getBasePrice() { return basePrice; }

	public Flight (String origin, String destination, String airline, Number basePrice) 
	{
		super();
		this.origin = origin;
		this.destination = destination;
		this.airline = airline;
		this.basePrice = basePrice;
	}
}
