package javaCode.com.rumbo.flightSearch.commons;

public class Airline 
{
	private String iataCode;
	public String getIataCode() { return iataCode; }

	public String name;
	public String getName() { return name; }

	public Number infantPrice;
	public Number getInfantPrice() { return infantPrice; }
	public void setInfantPrice(Number infantPrice) { this.infantPrice = infantPrice; }
	
	public Airline (String iataCode, String name, Number infantPrice) 
	{
		super();
		this.iataCode = iataCode;
		this.name = name;
		this.infantPrice = infantPrice;
	}
}
