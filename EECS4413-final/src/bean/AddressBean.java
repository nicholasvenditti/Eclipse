package bean;

import org.json.JSONObject;

public class AddressBean{
	
	private int aid;
	private String street;
	private String province;
	private String country;
	private String zip;
	private String phone;
    private int uid;
    private String lname;
    private String fname;
	
	/**
	 * @param aid
	 * @param street
	 * @param province
	 * @param country
	 * @param phone
     * @param zip
	 * @param uid
	 */
	public AddressBean(int aid, String street, String province, String country, String zip, String phone, int uid, String lname, String fname) {
		this.aid= aid;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
        this.phone= phone;
    	this.uid= uid;
    	this.lname= lname;
    	this.fname= fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the aid
	 */
	public int getAid() {
		return aid;
	}



	/**
	 * @param aid the aid to set
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}



	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}


	/**
	 * @param aid the aid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}


	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}


	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}


	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

    /**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public JSONObject toJson() {
		JSONObject jsonObj= new JSONObject();

		jsonObj.put("id", this.aid);
		jsonObj.put("street", this.street);
		jsonObj.put("province", this.province);
		jsonObj.put("country", this.country);
		jsonObj.put("zip", this.zip);
		jsonObj.put("phone", this.phone);
        jsonObj.put("uid", this.uid);
		jsonObj.put("lname", this.lname);
        jsonObj.put("fname", this.fname);
		return jsonObj;
	}
	
	public String toString() {
		return "<AddressBean: aid="+this.aid+"; street="+this.street+"; province="+this.province+
				"; country="+this.country+"; zip="+this.zip+"; phone="+this.phone+";uid="+this.uid+"; lname="+this.lname+"; fname="+this.fname+">";
	}
	
}