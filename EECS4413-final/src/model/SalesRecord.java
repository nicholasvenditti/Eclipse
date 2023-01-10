package model;

public class SalesRecord {
	private int rank;
	private String bid;
	private int sales;
	
	/**
	 * @param rank
	 * @param bid
	 * @param sales
	 */
	public SalesRecord(int rank, String bid, int sales) {
		super();
		this.rank = rank;
		this.bid = bid;
		this.sales = sales;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
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
	 * @return the sales
	 */
	public int getSales() {
		return sales;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setSales(int sales) {
		this.sales = sales;
	}
	
	
}
