package javaCode.com.rumbo.flightSearch.commons;

public class FinalPrice 
{
	public Number price;
	public Number getPrice () { return price; }
	public void setPrice (Number price) { this.price = price; }
	
	public String priceDetail;
	public String getPriceDetail () { return priceDetail; }
	public void setPriceDetail (String priceDetail) { this.priceDetail = priceDetail; }
	
	public FinalPrice(Number price, String priceDetail) 
	{
		super();
		this.price = price;
		this.priceDetail = priceDetail;
	}	
}
