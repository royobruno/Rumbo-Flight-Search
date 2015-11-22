package javaCode.com.rumbo.flightSearch.commons;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javaCode.com.rumbo.flightSearch.util.PersistenceManager;

public class FlightSearch 
{
	private PersistenceManager persistenceManager = new PersistenceManager ();
	
	/**
	 * It contains flight information collected from a CVS file.
	 */
	public List<Flight> flightsData = new ArrayList<Flight> ();
	
	/**
	 * It contains information about the rules of the prices (% of base price of the flight).
	 */
	public Map<Number, Number> pricingRules = new LinkedHashMap<Number, Number> ();
	
	/**
	 * It contains airlines information collected from a CVS file.
	 */
	public List<Airline> airlinesData = new ArrayList<Airline> ();
	
	/**
	 * Method that performs the flights search using the number of passengers (distinguishing between adults, child and infant), 
	 * days to departure, origin and destination flight.
	 * @param adults
	 * @param children
	 * @param infants
	 * @param daysToDeparture
	 * @param origin
	 * @param destination
	 * @return
	 */
	public Map<String, FinalPrice> searchFlights (Number adults, Number children, Number infants, Number daysToDeparture, String origin, String destination)
	{
		// Flight data obtained.
		flightsData = persistenceManager.loadFlightsData();
		// Princing rules data are obtained.
		pricingRules = persistenceManager.loadPricingRules();
		// Airlines data obtained.
		airlinesData = persistenceManager.loadAirlinesData();
		
		Map<String, FinalPrice> resultSearch = new TreeMap<String, FinalPrice>();
		
		// First it is found that there is a flight from the source to the destination requested.
		List<Flight> availableFlights = availableFlights (origin, destination);
		if (availableFlights != null && !availableFlights.isEmpty())
		{
			Adult adult = null;
			Child child = null;
			Infant infant = null;
			
			// Always should be at least one adult per trip, but the code is protected against a possible nullPointerException.
			// Only those items required for each search are created.
			// Only those items required for each search are created.
			// It is done this way to optimize the objects created in memory instead of creating one for each flight processing, 
			// work with one object of each type as needed, changing their attributes each time.
			if (adults.intValue() > 0)
			{
				adult = new Adult ();
			}
			if (children.intValue() > 0)
			{
				child = new Child ();
			}
			if (infants.intValue() > 0)
			{
				infant = new Infant ();
			}
			
			for (Flight availableFlight : availableFlights)
			{
				//String availableFlightOrigin = availableFlight.getOrigin();
				//String availableFlightDestination = availableFlight.getDestination();
				String availableFlightAirline = availableFlight.getAirline();
				Number availableFlightBasePrice = availableFlight.getBasePrice();
				
				Number finalPrice = 0;
				
				// It is estimated the adult price.
				if (adult != null)
				{
					adult.calculatePrice(availableFlightBasePrice, daysToDeparture, pricingRules);
					// Once you have calculated the price per passenger , it is multiplied by the total number of passengers.
					Number adultPrice = adult.getCalculatedPrice();
					finalPrice = adultPrice != null ? finalPrice.floatValue() + adultPrice.floatValue() * adults.intValue() : finalPrice;
				}
				if (child != null)
				{
					child.calculatePrice(availableFlightBasePrice, daysToDeparture, pricingRules);
					// Once you have calculated the price per passenger , it is multiplied by the total number of passengers.
					Number childPrice = child.getCalculatedPrice();
					finalPrice = childPrice != null ? finalPrice.floatValue() + child.getCalculatedPrice().floatValue() * children.intValue() : finalPrice;
				}
				if (infant != null)
				{
					// The code of the airline is obtained to calculate the price of infant.
					String iataCode = availableFlightAirline.substring (0, 2);
					infant.calculatePrice(availableFlightBasePrice, daysToDeparture, pricingRules, iataCode, airlinesData);
					// Once you have calculated the price per passenger , it is multiplied by the total number of passengers.
					Number infantPrice = infant.getCalculatedPrice();
					finalPrice = infantPrice != null ? finalPrice.floatValue() + infant.getCalculatedPrice().floatValue() * infants.intValue() : finalPrice;
				}
				
				resultSearch.put(availableFlightAirline, new FinalPrice(finalPrice, null));
				System.out.println ("* " + availableFlightAirline + ", " + finalPrice + " €");
			}
		}
		else
		{
			System.out.println ("No flights available from " + origin + " to " + destination);
		}
		
		return resultSearch;
	}
	
	/**
	 * Method to check the flights available for an origin and destination given (received by parameter).
	 * @param origin
	 * @param destination
	 * @return
	 */
	public List<Flight> availableFlights (String origin, String destination)
	{
		List<Flight> flights = new ArrayList<Flight> ();
		
		if (flightsData != null)
		{
			for (Flight flight : flightsData)
			{
				String flightOrigin = flight.getOrigin();
				String flightDestination = flight.getDestination();
				if (flightOrigin != null && flightOrigin.equals(origin) && flightDestination != null && flightDestination.equals(destination))
				{
					flights.add(flight);
				}
			}
		}
		
		return flights;
	}
}
