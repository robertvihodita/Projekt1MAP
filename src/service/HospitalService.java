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

    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital.getId(), hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Optional<Hospital> getHospitalById(String id) {
        return hospitalRepository.findById(id);
    }

    public void deleteHospital(String id) {
        hospitalRepository.delete(id);
    }
}
