package service;

import org.springframework.data.domain.Sort;
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

    public Nurse addNurse(Nurse nurse) { return nurseRepository.save(nurse); }
    public Optional<Nurse> getNurseById(String id) { return nurseRepository.findById(id); }
    public void deleteNurse(String id) { nurseRepository.deleteById(id); }
    public List<Nurse> getAllNurses() { return nurseRepository.findAll(); }

    // UPDATED: Added hospitalId parameter
    public List<Nurse> getAllNurses(String name, String departmentId, String hospitalId, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "name");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchName = (name != null && !name.isEmpty()) ? name : null;
        String searchDept = (departmentId != null && !departmentId.isEmpty()) ? departmentId : null;
        String searchHosp = (hospitalId != null && !hospitalId.isEmpty()) ? hospitalId : null;

        return nurseRepository.searchNurses(searchName, searchDept, searchHosp, sort);
    }
}