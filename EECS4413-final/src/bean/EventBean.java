package bean;

enum EventType {
	VIEW,
	CART,
	PURCHASE
}

public class EventBean {

	private int eid;
	private String day;
	private String bid;
	private EventType eventtype;
	/**
	 * @param eid
	 * @param day
	 * @param bid
	 * @param eventtype
	 */
	public EventBean(int eid, String day, String bid, int typeindex) {
		super();
		this.eid = eid;
		this.day = day;
		this.bid = bid;
		this.eventtype = EventType.values()[typeindex-1];
	}
	/**
	 * @return the eid
	 */
	public int getEid() {
		return eid;
	}
	/**
	 * @param eid the eid to set
	 */
	public void setEid(int eid) {
		this.eid = eid;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
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
	 * @return the eventtype
	 */
	public EventType getEventtype() {
		return eventtype;
	}
	/**
	 * @param eventtype the eventtype to set
	 */
	public void setEventtype(int typeindex) {
		this.eventtype = EventType.values()[typeindex-1];
	}
	
	
}
