package Geeks.languagecenterapp.Controller;
import Geeks.languagecenterapp.DTO.Request.PostRequest;
import Geeks.languagecenterapp.DTO.Response.PostResponse;
import Geeks.languagecenterapp.Model.Enum.UserAccountEnum;
import Geeks.languagecenterapp.Model.PostEntity;
import Geeks.languagecenterapp.Model.UserEntity;
import Geeks.languagecenterapp.Service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    //Create post
    @PostMapping("/add")
    public ResponseEntity<Object> addPost(@AuthenticationPrincipal UserEntity user, @ModelAttribute PostRequest body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        if (user.getAccountType()!= UserAccountEnum.ADMIN){
            // Create a response object with the success message
            response.put("message","You are UnAuthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return postService.add(body);
    }
    //update post
    @PostMapping("/update/{id}")
    public ResponseEntity<Object>updatePost(@AuthenticationPrincipal UserEntity user,@PathVariable("id") int id , @ModelAttribute PostRequest body)throws JsonProcessingException{
        Map<String, String> response = new HashMap<>();
        if (user.getAccountType()!= UserAccountEnum.ADMIN){
            // Create a response object with the success message
            response.put("message","You are UnAuthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return postService.update(body, id);
    }
    //delete post
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable("id") int id ,@AuthenticationPrincipal UserEntity user) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        if (user.getAccountType()!= UserAccountEnum.ADMIN){
            // Create a response object with the success message
            response.put("message","You are UnAuthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return postService.delete(id);
    }
    //get All posts
    @GetMapping("/get/all")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.ok(postService.getAll());
    }
    //get All ads
    @GetMapping("/get/ads")
    public ResponseEntity<List<PostResponse>> getAllAds(){
        return ResponseEntity.ok(postService.getAds());
    }
    //get All recent ads
    @GetMapping("/get/ads/desc")
    public ResponseEntity<List<PostResponse>> getAllRecentAds(){
        return ResponseEntity.ok(postService.getRecentAds());
    }
    //get All old ads
    @GetMapping("/get/ads/asc")
    public ResponseEntity<List<PostResponse>> getAllOldAds(){
        return ResponseEntity.ok(postService.getOldAds());
    }
    //get All events
    @GetMapping("/get/events")
    public ResponseEntity<List<PostResponse>> getAllEvents(){
        return ResponseEntity.ok(postService.getEvents());
    }
    //get All recent events
    @GetMapping("/get/events/desc")
    public ResponseEntity<List<PostResponse>> getAllRecentEvents(){
        return ResponseEntity.ok(postService.getRecentEvents());
    }
    //get All old events
    @GetMapping("/get/events/asc")
    public ResponseEntity<List<PostResponse>> getAllOldEvents(){
        return ResponseEntity.ok(postService.getOldEvents());
    }


}
