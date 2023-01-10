package bean;

import org.json.JSONObject;

public class UserBean {
	
	private int uid;
	private String uname;
	private int salt;
	private String hashpass;
	private short utype;
	private int defaultAddress;
	
	/**
	 * @param uid
	 * @param uname
	 * @param salt
	 * @param hashpass
	 * @param utype
	 * @param address
	 */
	public UserBean(int uid, String uname, int salt, String hashpass, short utype, int defaultAddress) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.salt = salt;
		this.hashpass = hashpass;
		this.utype = utype;
		this.defaultAddress= defaultAddress;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setId(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the salt
	 */
	public int getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(int salt) {
		this.salt = salt;
	}

	/**
	 * @return the hashpass
	 */
	public String getHashpass() {
		return hashpass;
	}

	/**
	 * @param hashpass the hashpass to set
	 */
	public void setHashpass(String hashpass) {
		this.hashpass = hashpass;
	}

	/**
	 * @return the utype
	 */
	public short getUtype() {
		return utype;
	}

	/**
	 * @param utype the utype to set
	 */
	public void setUtype(short utype) {
		this.utype = utype;
	}

	/**
	 * @return the defaultAddress
	 */
	public int getDefaultAddress() {
		return defaultAddress;
	}

	/**
	 * @param defaultAddress the defaultAddress to set
	 */
	public void setDefaultAddress(int defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	
	public JSONObject toJson() {
		JSONObject jsonObj= new JSONObject();
		
		jsonObj.put("uid", this.uid);
		jsonObj.put("uname", this.uname);
		jsonObj.put("salt", this.salt);
		jsonObj.put("hashpass", this.hashpass);
		jsonObj.put("utype", this.utype);
		jsonObj.put("defaultAddress", this.defaultAddress);
		
		return jsonObj;
	}	
	
}
