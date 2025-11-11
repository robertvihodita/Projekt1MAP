package repository;

import org.springframework.stereotype.Repository;

import model.Patient;

@Repository
public class PatientRepository extends InMemoryRepository<Patient> {}

