package br.com.restaurant.model;

@SuppressWarnings("serial")
public class EmployeeModel extends AbstractModel {
	
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
	private Integer access_level;
	private RoleModel role;
	private EmployeeModel boss;
	
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
	public Integer getAccess_level() {
		return access_level;
	}
	public void setAccess_level(Integer access_level) {
		this.access_level = access_level;
	}
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
	public EmployeeModel getBoss() {
		return boss;
	}
	public void setBoss(EmployeeModel boss) {
		this.boss = boss;
	}
	
	
	
	
}
