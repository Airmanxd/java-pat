package com.example.Practice18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DepartureService {
    private DepartureRepository departureRepository;
    private PostOfficeRepository postOfficeRepository;

    @Transactional
    public void save(Departure departure, int postOfficeId) {
        Optional<PostOffice> postOffice = postOfficeRepository.findById(postOfficeId);
        if(postOffice.isPresent()) {
            departure.setPostOffice(postOffice.get());
            departureRepository.save(departure);
        }
    }

    @Transactional
    public void delete(int id){
        departureRepository.deleteById(id);
    }

    public List<Departure> getSortedDeparturesByField(String fieldName) {
        return departureRepository.findAll(Sort.by(fieldName));
    }

    public List<Departure> getAll() {
        return departureRepository.findAll();
    }

    public List<Departure> getAllDeparturesByPostOfficeId(int postOfficeId){
        return departureRepository.findAllByPostOfficeId(postOfficeId);
    }

    @Autowired
    public void setDepartureRepository(DepartureRepository departureRepository) {
        this.departureRepository = departureRepository;
    }
    @Autowired
    public void setPostOfficeRepository(PostOfficeRepository postOfficeRepository) {
        this.postOfficeRepository = postOfficeRepository;
    }
}
