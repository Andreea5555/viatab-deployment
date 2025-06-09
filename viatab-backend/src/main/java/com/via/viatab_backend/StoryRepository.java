package com.via.viatab_backend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.via.viatab_backend.Story;


@Repository
public interface StoryRepository extends MongoRepository<Story, String> {

    List<Story> findByDepartment(String department);
    List<Story> findByAuthor(String author);
    List<Story> findByTitleContainingIgnoreCase(String title);
    
}
