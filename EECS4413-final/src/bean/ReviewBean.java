package bean;

import org.json.JSONObject;

public class ReviewBean {
	
	private int rid;
	private String bid;
	private String author;
	private int rating;
	private String review;
	
	
	/**
	 * @param rid
	 * @param bid
	 * @param author
	 * @param rating
	 * @param review
	 */
	public ReviewBean(int rid, String bid, String author, int rating, String review) {
		super();
		this.rid = rid;
		this.bid = bid;
		this.author = author;
		this.rating = rating;
		this.review = review;
	}


	/**
	 * @return the rid
	 */
	public int getRid() {
		return rid;
	}


	/**
	 * @param rid the rid to set
	 */
	public void setRid(int rid) {
		this.rid = rid;
	}


	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}


	/**
	 * @param bid the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}


	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}


	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}


	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}


	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}


	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}


	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	public JSONObject toJson() {
		JSONObject jsonObj= new JSONObject();

		jsonObj.put("rid", this.rid);
		jsonObj.put("bid", this.bid);
		jsonObj.put("author", this.author);
		jsonObj.put("rating", this.rating);
		jsonObj.put("review", this.review);
		
		return jsonObj;
	}
	
	public String toString() {
		return "<ReviewBean: rid="+this.rid+"; bid="+this.bid+"; author="+this.author+"; rating="+this.rating+"; review="+review+">";
	}
	
}
