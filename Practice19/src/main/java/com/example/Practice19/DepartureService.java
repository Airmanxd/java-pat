package com.example.Practice19;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartureService {
    private DepartureRepository departureRepository;
    private PostOfficeRepository postOfficeRepository;

    @Transactional
    public void save(Departure departure, int postOfficeId) {
        log.info("Saving Departure");
        Optional<PostOffice> postOffice = postOfficeRepository.findById(postOfficeId);
        if(postOffice.isPresent()) {
            departure.setPostOffice(postOffice.get());
            departureRepository.save(departure);
        }
    }

    @Transactional
    public void delete(int id){
        log.info("deleting departure {}", id);
        departureRepository.deleteById(id);
    }

    public List<Departure> getSortedDeparturesByField(String fieldName) {
        log.info("returning departures sorted by {}", fieldName);
        return departureRepository.findAll(Sort.by(fieldName));
    }

    public List<Departure> getAll() {
        log.info("returning all departures");
        return departureRepository.findAll();
    }

    public List<Departure> getAllDeparturesByPostOfficeId(int postOfficeId){
        log.info("returning all departures of PO: {}", postOfficeId);
        return departureRepository.findAllByPostOfficeId(postOfficeId);
    }

    @Autowired
    public void setDepartureRepository(DepartureRepository departureRepository) {
        log.info("Setting departure repository");
        this.departureRepository = departureRepository;
    }
    @Autowired
    public void setPostOfficeRepository(PostOfficeRepository postOfficeRepository) {
        log.info("setting PO repository");
        this.postOfficeRepository = postOfficeRepository;
    }
}
