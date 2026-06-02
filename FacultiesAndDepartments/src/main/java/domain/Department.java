package domain;

public class Department {
	private Long id;

	private String name;

	private String shortName;

	private String head;

	private String phoneNumber;

	private Long facultyId;

	private Faculty faculty;

	public Department(String name, String shortName, String head, String phoneNumber, Faculty faculty) {
		this.name = name;
		this.shortName = shortName;
		this.head = head;
		this.phoneNumber = phoneNumber;
		this.faculty = faculty;
	}

	public Department(String name, String shortName, String head, String phoneNumber, Long facultyId, Faculty faculty) {
		this.name = name;
		this.shortName = shortName;
		this.head = head;
		this.phoneNumber = phoneNumber;
		this.facultyId = facultyId;
		this.faculty = faculty;
	}

	public Department(String name, String shortName, String head, String phoneNumber, Long facultyId) {
		this.name = name;
		this.shortName = shortName;
		this.head = head;
		this.phoneNumber = phoneNumber;
		this.facultyId = facultyId;
	}
	
	public Department(Long id, String name, String shortName, String head, String phoneNumber, Long facultyId) {
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.head = head;
		this.phoneNumber = phoneNumber;
		this.facultyId = facultyId;
	}

	public Department(Long id, String name, String shortName, String head, String phoneNumber, Long facultyId,
			Faculty faculty) {
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.head = head;
		this.phoneNumber = phoneNumber;
		this.facultyId = facultyId;
		this.faculty = faculty;
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

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaculty() {
		return faculty.getName();
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	@Override
	public String toString() {
		return "Faculty { Id = " + id + " Name = " + name + " ShortName = " + shortName + " Head = " + head
				+ " PhoneNumber = " + phoneNumber + " Faculty = " + getFaculty() + " }";
	}
}
