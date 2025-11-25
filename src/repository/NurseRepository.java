package repository;

import org.springframework.stereotype.Repository;
import model.Nurse;

@Repository
public class NurseRepository extends InFileRepository<Nurse> {
    public NurseRepository() {
        super("nurse.json", Nurse.class);
    }
}