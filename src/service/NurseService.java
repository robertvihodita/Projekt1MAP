package service;

import org.springframework.stereotype.Service;

import repository.NurseRepository;
import model.Nurse;
import java.util.List;
import java.util.Optional;

@Service
public class NurseService {
    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public Nurse addNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    // OK: findAll() method signature is the same
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<Nurse> getNurseById(String id) {
        return nurseRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deleteNurse(String id) {
        nurseRepository.deleteById(id);
    }
}