package com.demo.patientData.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.patientData.model.Patient;

public interface PatientDao extends JpaRepository<Patient, Integer> {

	public List<Patient> findByGender(String gender);

	public List<Patient> findByOrderByLastNameAsc();

	public List<Patient> findByGenderOrderByLastNameAsc(String gender);

}
