package com.example.Practice18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostOfficeService {
    private PostOfficeRepository postOfficeRepository;

    @Transactional
    public void save(PostOffice postOffice){
        postOfficeRepository.save(postOffice);
    }

    @Transactional
    public void remove(int id){
        postOfficeRepository.deleteById(id);
    }

    public List<PostOffice> getSortedPostOfficesByField(String fieldName){
        return postOfficeRepository.findAll(Sort.by(fieldName));
    }

    public List<PostOffice> getAll(){
        return postOfficeRepository.findAll();
    }

    @Autowired
    private void setPostOfficeRepository(PostOfficeRepository postOfficeRepository) {
        this.postOfficeRepository = postOfficeRepository;
    }
}

