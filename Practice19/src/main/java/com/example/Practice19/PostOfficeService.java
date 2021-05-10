package com.example.Practice19;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class PostOfficeService {
    private PostOfficeRepository postOfficeRepository;

    @Transactional
    public void save(PostOffice postOffice){
        log.info("saving PO {}", postOffice.getId());
        postOfficeRepository.save(postOffice);
    }

    @Transactional
    public void remove(int id){
        log.info("removing PO{}", id);
        postOfficeRepository.deleteById(id);
    }

    public List<PostOffice> getSortedPostOfficesByField(String fieldName){
        log.info("returning POs sorted by {}", fieldName);
        return postOfficeRepository.findAll(Sort.by(fieldName));
    }

    public List<PostOffice> getAll(){
        log.info("returning all POs");
        return postOfficeRepository.findAll();
    }

    @Autowired
    private void setPostOfficeRepository(PostOfficeRepository postOfficeRepository) {
        log.info("setting PO repository");
        this.postOfficeRepository = postOfficeRepository;
    }
}

