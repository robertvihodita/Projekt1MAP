package repository;

import org.springframework.stereotype.Repository;

import model.Nurse;

@Repository
public class NurseRepository extends InMemoryRepository<Nurse> {}
