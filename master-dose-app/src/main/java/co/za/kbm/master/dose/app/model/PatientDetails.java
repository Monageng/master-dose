package co.za.kbm.master.dose.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PatientDetails {
	private String firstName;
	private String surname;
	private String title;
	private String initials;
	private String patientId;
	
}
