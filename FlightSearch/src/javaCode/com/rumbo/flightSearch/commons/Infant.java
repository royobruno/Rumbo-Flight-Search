package javaCode.com.rumbo.flightSearch.commons;

import java.util.List;
import java.util.Map;

public class Infant extends Passanger
{
	public Number infantPrice;
	public Number getInfantPrice () { return infantPrice; }
	public void setInfantPrice (Number infantPrice) { this.infantPrice = infantPrice; }

	public Infant () { this (0); }
	
	public Infant (Number infantPrice)
	{
		this.setPriceDiscount (0);
		this.infantPrice = infantPrice;
	}
	
	/**
	 * Method that calculates the final price of a flight for a passenger considering days to departure and the discount .
	 * In this case, taking into account also the infant price according to the airline.
	 * @param flightBasePrice
	 * @param daysToDeparture
	 * @param pricingRules
	 * @return
	 */
	public void calculatePrice (Number flightBasePrice, Number daysToDeparture, Map<Number, Number> pricingRules, String iataAirlineCode, List<Airline> airlines) 
	{
		Number infantAirlinePrice = null;
		
		if (airlines != null)
		{
			for (Airline airline : airlines)
			{
				if (airline.getIataCode().equals(iataAirlineCode))
				{
					infantAirlinePrice = airline.getInfantPrice();
				}
			}
		}
		
		if (infantAirlinePrice != null)
		{
			setInfantPrice(infantAirlinePrice);
		}
		else
		{
			System.out.println ("[Infant-calculatePrice]: WARNING: Was not found infant price witj the IATA code: " + iataAirlineCode + " recived");
			infantAirlinePrice = 0;
		}
		
		setCalculatedPrice(infantAirlinePrice);
	}
}
