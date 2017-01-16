package br.com.restaurant.model;

@SuppressWarnings("serial")
public class ClientModel extends AbstractModel {
	
	private String name;
	private String phone;
	private String email;
	private String document;
	private String login;
	private String password;
	private String address_name;
	private Integer address_number;
	private String address_complement;
	private String zip_code;
	private String creditcard_number;
	private String creditcard_name;
	private String creditcard_flag;
	private String creditcard_security_code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public Integer getAddress_number() {
		return address_number;
	}
	public void setAddress_number(Integer address_number) {
		this.address_number = address_number;
	}
	public String getAddress_complement() {
		return address_complement;
	}
	public void setAddress_complement(String address_complement) {
		this.address_complement = address_complement;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getCreditcard_number() {
		return creditcard_number;
	}
	public void setCreditcard_number(String creditcard_number) {
		this.creditcard_number = creditcard_number;
	}
	public String getCreditcard_name() {
		return creditcard_name;
	}
	public void setCreditcard_name(String creditcard_name) {
		this.creditcard_name = creditcard_name;
	}
	public String getCreditcard_flag() {
		return creditcard_flag;
	}
	public void setCreditcard_flag(String creditcard_flag) {
		this.creditcard_flag = creditcard_flag;
	}
	public String getCreditcard_security_code() {
		return creditcard_security_code;
	}
	public void setCreditcard_security_code(String creditcard_security_code) {
		this.creditcard_security_code = creditcard_security_code;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
