package bean;

import org.json.JSONObject;

public class OrderBean
{
    private int orderID;
    private String bid;
    private double price;

    /**
	 * @param orderID
	 * @param bid
	 * @param price
	 */
     public OrderBean(int orderID, String bid, double price)
     {
         this.orderID=orderID;
         this.bid=bid;
         this.price=price;
     }

     /**
	 * @return the orderID
	 */
    public int getOrderID()
    {
        return orderID;
    }
    /**
	 * @return the bid
	 */
    public String getBid()
    {
        return bid;
    }
    /**
	 * @return the price
	 */
    public double getPrice()
    {
        return price;
    }

    /**
	 * @param orderID the orderID to set
	 */
    public void setOrderID(int oID)
    {
        orderID = oID;
    }

    /**
	 * @param bid the bid to set
	 */
     public void setBid(String b)
     {
         bid=b;
     }

      /**
	 * @param price the price to set
	 */
    public void setPrice(double p)
    {
        price=p;
    }
    public JSONObject toJson() 
    {
		JSONObject jsonObj= new JSONObject();
		
		jsonObj.put("id", this.orderID);
		jsonObj.put("bid", this.bid);
		jsonObj.put("price", this.price);
		
		return jsonObj;
	}	
}