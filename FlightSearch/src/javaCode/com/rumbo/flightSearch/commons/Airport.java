package javaCode.com.rumbo.flightSearch.commons;

import java.util.Map;
import java.util.TreeMap;

public class Airport 
{
	public String iataCode;
	public String city;
	
	private Map<String, String> airports = new TreeMap<String, String> ();
	public Map<String, String> getAirports () { return airports; }
	
	public Airport (String iataCode, String city) 
	{
		super();
		this.iataCode = iataCode;
		this.city = city;
	}
}
