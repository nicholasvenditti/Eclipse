package bean;

import org.json.JSONObject;

public class BookBean {

	private String bid;
	private String title;
	private double price;
	private String category;
	
	/**
	 * @param bid
	 * @param title
	 * @param price
	 * @param category
	 */
	public BookBean(String bid, String title, double price, String category) {
		super();
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = category;
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	public JSONObject toJson() {
		JSONObject jsonObj= new JSONObject();
		
		jsonObj.put("bid", this.bid);
		jsonObj.put("title", this.title);
		jsonObj.put("price", this.price);
		jsonObj.put("category", this.category);
		
		return jsonObj;
	}
	
	public String toString() {
		return "<BookBean: "+ this.title + " (" + this.bid + ")>";
	}
	
	public boolean equals(Object o) {
		if (o.getClass().equals(this.getClass())) {
			BookBean other= (BookBean) o;
			return ( this.bid.equals(other.bid) && this.title.equals(other.title) && this.price==other.price && this.category.equals(other.category) );
		}
		return false;
	}
		
}
