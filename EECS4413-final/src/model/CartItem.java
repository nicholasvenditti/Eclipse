package model;

import bean.BookBean;

public class CartItem {
	private BookBean book;
	private int quantity;
	
	/**
	 * @param book
	 * @param quantity
	 */
	public CartItem(BookBean book, int quantity) {
		super();
		this.book = book;
		this.quantity = quantity;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void incrementQuantity() {
		this.quantity += 1;
	}

	/**
	 * @return the book
	 */
	public BookBean getBook() {
		return book;
	}
	
	public String toString() {
		return "<CartItem: " + this.book + ", qty:" + this.quantity + ">";
	}
}
