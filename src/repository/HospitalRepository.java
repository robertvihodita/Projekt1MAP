package repository;

import org.springframework.stereotype.Repository;
import model.Hospital;

@Repository
public class HospitalRepository extends InFileRepository<Hospital> {
    public HospitalRepository() {
        super("hospital.json", Hospital.class);
    }
}