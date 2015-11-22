package javaCode.com.rumbo.flightSearch.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javaCode.com.rumbo.flightSearch.commons.Airline;
import javaCode.com.rumbo.flightSearch.commons.Flight;

/**
 * This class simulates the collection of data. Normally data were obtained from a database, but in this case is obtained from cvs 
 * files placed into the path : /res/cvs/...
 * @author Alejandro.alonso.mo1
 *
 */
public class PersistenceManager 
{
	/**
	 * Method to load flights data.
	 * @return
	 */
  public List<Flight> loadFlightsData () 
  {
	String file = "res/cvs/FlightsData.cvs";
	BufferedReader buffer = null;
	String line = "";
	String splitBy = ",";

	List <Flight> flights = new ArrayList <Flight> ();
	
	try 
	{
		buffer = new BufferedReader (new FileReader(file));
		while ((line = buffer.readLine()) != null) 
		{
			String [] data = line.split (splitBy);
			
			String origin = data [0];
			String destination = data [1];
			String airline = data [2];
			Number basePrice = Float.valueOf(data [3]);
			
			Flight flight = new Flight (origin, destination, airline, basePrice);
			flights.add(flight);
		}

	} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	} 
	catch (IOException e) 
	{
		e.printStackTrace();
	}
	finally 
	{
		if (buffer != null) 
		{
			try 
			{
				buffer.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	return flights;
  }
  
  /**
   * Method to load the airlines data.
   * @return
   */
  public List<Airline> loadAirlinesData () 
  {
	String file = "res/cvs/AirlinesData.cvs";
	BufferedReader buffer = null;
	String line = "";
	String splitBy = ",";

	List <Airline> airlines = new ArrayList <Airline> ();
	
	try 
	{
		buffer = new BufferedReader (new FileReader(file));
		while ((line = buffer.readLine()) != null) 
		{
			String [] data = line.split (splitBy);
			
			String iataCode = data [0];
			String name = data [1];
			Number infantPrice = Float.valueOf(data [2]);
			
			Airline airline = new Airline (iataCode, name, infantPrice);
			airlines.add(airline);
		}

	} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	} 
	catch (IOException e) 
	{
		e.printStackTrace();
	}
	finally 
	{
		if (buffer != null) 
		{
			try 
			{
				buffer.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	return airlines;
  }
  
	/**
	 * Method to load the pricing rules.
	 * @return
	 */
	public Map<Number, Number> loadPricingRules ()
	{
		Map<Number, Number> pricingRules = new LinkedHashMap<Number, Number> ();
		
		pricingRules.put(31, 80);
		pricingRules.put(16, 100);
		pricingRules.put(3, 120);
		pricingRules.put(0, 150);
		
		return pricingRules;
	}
}