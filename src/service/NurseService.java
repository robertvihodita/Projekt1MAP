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

        public Nurse addNurse(Nurse nurse) {
            return nurseRepository.save(nurse.getId(), nurse);
        }

        public List<Nurse> getAllNurses() {
            return nurseRepository.findAll();
        }

        public Optional<Nurse> getNurseById(String id) {
            return nurseRepository.findById(id);
        }

        public void deleteNurse(String id) {
            nurseRepository.delete(id);
        }
    }