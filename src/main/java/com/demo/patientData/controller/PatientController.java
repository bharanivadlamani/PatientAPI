package com.demo.patientData.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.patientData.PatientUtil;
import com.demo.patientData.dao.PatientDao;
import com.demo.patientData.model.Patient;

@RestController
//@ResponseBody("exclude=bar")
public class PatientController {

	@Autowired
	PatientDao patientDao;

	@PostMapping("/addPatient")
	public String addPatient(@RequestBody Patient patient) {

		if (!PatientUtil.isGenderDataValid(patient.getGender()))
			return "Gender is invalid";
		if (!PatientUtil.isPatientAged18(patient.getBirthday()))
			return "Patient is younger than 18 years";
		patient.setCreationDate(LocalDate.now());
		patientDao.save(patient);
		return "Patient Data saved";
	}

	@GetMapping("/getAllPatients")
	public List<Patient> getAllPatients() {
		return (List<Patient>) patientDao.findByOrderByLastNameAsc();
	}
	
	@GetMapping("/patient/{patientId}")
	public Optional<Patient> getPatient(@PathVariable int patientId) {
		return patientDao.findById(patientId);
	}

	@GetMapping("/getFemalePatients")
	public List<Patient> getAllFemalePatients() {
		return (List<Patient>) patientDao.findByGenderOrderByLastNameAsc("female");
	}

	@DeleteMapping("/deletePatient/{patientId}")
	public String deletePatient(@PathVariable int patientId) {
		patientDao.deleteById(patientId);
		return "Patient Deleted";
	}

	@DeleteMapping("/deletePatientsCreatedYearAgo")
	public String deletePatientsCreatedYearAgo() {
		int count = 0;
		for (Patient patient : patientDao.findAll()) {
			if (PatientUtil.isPatientCreatedAnYearAgo(patient.getCreationDate())) {
				patientDao.deleteById(patient.getPatientID());
				count++;
			}
		}
		if (count == 0)
			return "No Patients exist whose date was created more than a year ago";
		return count + " Patient(s) data created more than year ago is Deleted";
	}

}
