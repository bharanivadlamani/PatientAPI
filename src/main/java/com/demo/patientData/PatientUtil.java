package com.demo.patientData;

import java.time.LocalDate;
import java.time.Period;

public class PatientUtil {

	public static boolean isPatientAged18(LocalDate birthday) {
		Period period = Period.between(birthday, LocalDate.now());
		if (period.getYears() >= 18)
			return true;
		return false;
	}

	public static boolean isPatientCreatedAnYearAgo(LocalDate patientCreationDate) {
		Period period = Period.between(patientCreationDate, LocalDate.now());
		if (period.getYears() >= 1)
			return true;
		return false;
	}

	public static boolean isGenderDataValid(String gender) {
		if (gender != null && !gender.isEmpty() && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")
				|| gender.equalsIgnoreCase("other") || gender.equalsIgnoreCase("unknown")))
			return true;
		else
			return false;
	}

}
