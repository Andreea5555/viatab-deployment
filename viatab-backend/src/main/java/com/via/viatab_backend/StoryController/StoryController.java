package com.via.viatab_backend.StoryController;
import com.via.viatab_backend.StoryRepository;
import com.via.viatab_backend.Story;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin(origins = "*")
public class StoryController {

    @Autowired
    private StoryRepository storyRepository;

    // Get all stories
    @GetMapping
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable String id) {
        Optional<Story> story = storyRepository.findById(id);
        return story.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{department}")
    public List<Story> getStoriesByDepartment(@PathVariable String department) {
        return storyRepository.findByDepartment(department);
    }

    @PostMapping
    public Story createStory(@RequestBody Story story) {
        return storyRepository.save(story);
    }

        @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable String id, @RequestBody Story storyDetails) {
        Optional<Story> optionalStory = storyRepository.findById(id);
        if (optionalStory.isPresent()) {
            Story story = optionalStory.get();
            story.setTitle(storyDetails.getTitle());
            story.setContent(storyDetails.getContent());
            story.setDepartment(storyDetails.getDepartment());
            story.setAuthor(storyDetails.getAuthor());
            return ResponseEntity.ok(storyRepository.save(story));
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE story
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable String id) {
        if (storyRepository.existsById(id)) {
            storyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    
}
