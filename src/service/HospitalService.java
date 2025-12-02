package service;

import org.springframework.stereotype.Service;

import repository.HospitalRepository;
import model.Hospital;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // OK: findAll() method signature is the same
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<Hospital> getHospitalById(String id) {
        return hospitalRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deleteHospital(String id) {
        hospitalRepository.deleteById(id);
    }
}