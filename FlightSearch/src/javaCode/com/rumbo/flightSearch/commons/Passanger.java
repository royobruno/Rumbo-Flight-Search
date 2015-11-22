package javaCode.com.rumbo.flightSearch.commons;

import java.util.Iterator;
import java.util.Map;

public class Passanger 
{
	private Number priceDiscount;
	public Number getPriceDiscount () { return priceDiscount; }
	public void setPriceDiscount (Number o) { this.priceDiscount = o; }
	
	public Number calculatedPrice;
	public Number getCalculatedPrice() { return calculatedPrice; }
	public void setCalculatedPrice(Number calculatedPrice) { this.calculatedPrice = calculatedPrice; }
	
	/**
	 * Method that calculates the final price of a flight for a passenger considering days to departure and the discount.
	 * Subclasses can override this method according their needs.
	 * @param flightBasePrice
	 * @param daysToDeparture
	 * @param pricingRules
	 * @return
	 */
	public void calculatePrice (Number flightBasePrice, Number daysToDeparture, Map<Number, Number> pricingRules) 
	{
		Number finalPriceWithOutDiscount = null;
		Number finalPrice = null;
		Number basePricePercentage = null;
		
		if (pricingRules != null)
		{
			// For this iteration working properly you need to map the rules get sorted correctly, highest to lowest number of days: 
			// ie: 30 - > 80 %, 16 -> 100 % 3 -> 120 %, 0 - > 150 %.
			Iterator<Number> it = pricingRules.keySet().iterator();
			while(it.hasNext())
			{
			  Number key = it.next();
			  if (daysToDeparture.intValue() >= key.intValue())
			  {
				  basePricePercentage = pricingRules.get (key);
				  break;
			  }
			}
			
			// Another way to iterate over a map, somewhat less efficient than the last.
//			for (Number key : pricingRules.keySet()) 
//			{
//			  if (daysToDeparture.intValue() >= key.intValue())
//			  {
//				  basePricePercentage = pricingRules.get (key);
//			  }
//			}
			
			if (basePricePercentage != null)
			{
				finalPriceWithOutDiscount = flightBasePrice.floatValue() * basePricePercentage.floatValue() / 100;
				finalPrice = finalPriceWithOutDiscount.floatValue() - (finalPriceWithOutDiscount.floatValue() * getPriceDiscount().floatValue() / 100);
			}
			else
			{
				System.out.println ("[Pasaanger-calculatePrice]: WARNING: The number of days to departure received: " + daysToDeparture + " not fit with any value of the rules table , it assumes the most expensive");
			}
			
			setCalculatedPrice(finalPrice);
		}
	}
}
