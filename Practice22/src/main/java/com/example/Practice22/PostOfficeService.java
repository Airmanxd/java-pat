package com.example.Practice22;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PostOfficeService {
    private PostOfficeRepository postOfficeRepository;
    private EmailService emailService;
    public void save(PostOffice postOffice){
        log.info("saving PO {}", postOffice.getId());
        emailService.sendSimpleMessage("Saved new PO", postOffice.toString());
        postOfficeRepository.save(postOffice);
    }

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
    @Autowired
    public void setEmailService(EmailService emailService) {
        log.info("setting email service for PO service");
        this.emailService = emailService;
    }
}

