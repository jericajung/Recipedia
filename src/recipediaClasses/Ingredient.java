package recipediaClasses;

public class Ingredient {
	//String units or int units?
	public Ingredient(String nm, String unt, double quant)
	{
		name=nm;
		unit=unt;
		quantity=quant;
	}
	private String name;
	private String unit;
	private double quantity;
	
	public void setName(String nm)
	{
		name=nm;
	}
	
	public String getName()
	{
		return name;
	}
	public void setUnits(String unt)
	{
		unit=unt;
	}
	public String getUnits()
	{
		return unit;
	}
	public void setQuantity(int quant)
	{
		quantity=quant;
	}
	public double getQuantity()
	{
		return quantity;
	}
	public String toStringWrong()
	{
		//insert bs
		return "";
	}
}
