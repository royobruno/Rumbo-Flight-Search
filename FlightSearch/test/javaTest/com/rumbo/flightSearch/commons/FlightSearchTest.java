package javaTest.com.rumbo.flightSearch.commons;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import javaCode.com.rumbo.flightSearch.commons.FinalPrice;
import javaCode.com.rumbo.flightSearch.commons.FlightSearch;

import org.junit.Test;

public class FlightSearchTest {

	@Test
	public void testSearchFlights () 
	{
		FlightSearch flightSearch = new FlightSearch();
		
		System.out.println ("[FlightSearchTest-testSearchFlights]: INFO - Test Case 1: 1 adult, 30 days to the departure date, flying AMS -> FRA");
		Map<String, FinalPrice> result = flightSearch.searchFlights(1, 0, 1, 31, "AMS", "FRA");
		assertTrue (result.containsKey("TK2372"));
		
		System.out.println ("[FlightSearchTest-testSearchFlights]: INFO - Test Case 2: 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
		result = flightSearch.searchFlights(2, 1, 1, 15, "LHR", "IST");
		assertTrue (result.containsKey("TK8891"));
		
		System.out.println ("[FlightSearchTest-testSearchFlights]: INFO - Test Case 3: 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
		result = flightSearch.searchFlights(1, 2, 0, 2, "BCN", "MAD");
		assertTrue (result.containsKey("IB2171"));
		
		System.out.println ("[FlightSearchTest-testSearchFlights]: INFO - Test Case 3: 1 adult, 2 children, 2 days to the departure date, flying CDG -> FRA");
		result = flightSearch.searchFlights(1, 2, 0, 2, "CDG", "FRA");
		assertTrue (result.isEmpty());
	}
}
