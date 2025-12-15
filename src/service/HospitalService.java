package service;

import org.springframework.data.domain.Sort;
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
        return hospitalRepository.save(hospital);
    }

    // UPDATED: Handles Filtering and Sorting
    public List<Hospital> getAllHospitals(String keyword, String city, String sortField, String sortDir) {
        // Default sort
        Sort sort = Sort.by(sortField != null ? sortField : "name");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        // Handle empty strings as null for the query
        String searchName = (keyword != null && !keyword.isEmpty()) ? keyword : null;
        String searchCity = (city != null && !city.isEmpty()) ? city : null;

        return hospitalRepository.searchHospitals(searchName, searchCity, sort);
    }

    public Optional<Hospital> getHospitalById(String id) {
        return hospitalRepository.findById(id);
    }

    public void deleteHospital(String id) {
        hospitalRepository.deleteById(id);
    }

    // Fallback for other controllers if needed
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}