package co.za.kbm.master.dose.app.model;

public class PatientDetails {
	private String firstName;
	private String surname;
	private String title;
	private String initials;
	private String patientId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String toString() {
		return "firstName : " + firstName + " surname : " + surname + " title : " + title + " initials : " + initials + " patientId : " + patientId;
	}
}
