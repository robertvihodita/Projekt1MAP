package repository;

import org.springframework.stereotype.Repository;

import model.Doctor;

@Repository
public class DoctorRepository extends InMemoryRepository<Doctor> {}