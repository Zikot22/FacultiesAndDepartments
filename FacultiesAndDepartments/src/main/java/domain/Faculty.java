package domain;

public class Faculty {
	private Long id;
	
	private String name;
	
	private String shortName;
	
	private String dean;
	
	private String phoneNumber;
	
	public Faculty(String name, String shortName, String dean, String phoneNumber) {
		this.name = name;
		this.shortName = shortName;
		this.dean = dean;
		this.phoneNumber = phoneNumber;
	}
	
	public Faculty(Long id, String name, String shortName, String dean, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.dean = dean;
		this.phoneNumber = phoneNumber;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getDean() {
		return dean;
	}
	
	public void setDean(String dean) {
		this.dean = dean;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "Faculty { Id = " + id + " Name = " + name + " ShortName = " + shortName 
				+ " Dean = " + dean + " PhoneNumber = " + phoneNumber + " }";
	}
}
