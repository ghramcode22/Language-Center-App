package Geeks.languagecenterapp.Controller;

import Geeks.languagecenterapp.DTO.Request.BookRequest;
import Geeks.languagecenterapp.DTO.Request.EnrollRequest;
import Geeks.languagecenterapp.DTO.Request.RateRequest;
import Geeks.languagecenterapp.Model.CourseEntity;
import Geeks.languagecenterapp.Model.Enum.UserAccountEnum;
import Geeks.languagecenterapp.Model.UserEntity;
import Geeks.languagecenterapp.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal UserEntity user) {
        return new ResponseEntity<>(userService.userProfile(user), HttpStatus.OK);
    }

    @PostMapping("/certificates")
    public ResponseEntity<?> uploadTeacherCertificates (@Param("files") List<MultipartFile> files) {
        return userService.uploadCertificates(files);
    }

    //TODO :api need test
    @GetMapping("/enrolled-courses")
    public List<CourseEntity> getEnrolledCourses(@AuthenticationPrincipal UserEntity user) {
        return userService.getEnrolledCourses(user);
    }
    //TODO :api need test
    @GetMapping("/favorite-courses")
    public List<CourseEntity> getFavoriteCourses(@AuthenticationPrincipal UserEntity user) throws JsonProcessingException {
        return userService.getFavoriteCourses(user);
    }
    // Enroll Course
    @PostMapping("/enroll-course/{courseId}")
    public ResponseEntity<Object> enrollCourse(@PathVariable("courseId") int id, @ModelAttribute EnrollRequest body) throws JsonProcessingException {
        return userService.enroll(body, id);
    }
    // Rate Course
    @PostMapping("/rate-course/{courseId}")
    public ResponseEntity<Object> rateCourse(@PathVariable("courseId") int id, @ModelAttribute RateRequest body) throws JsonProcessingException {
        return userService.rateCourse(body, id);
    }
    // Rate teacher
    @PostMapping("/rate-teacher/{teacherId}")
    public ResponseEntity<Object> rateTeacher(@PathVariable("teacherId") int id, @ModelAttribute RateRequest body) throws JsonProcessingException {
        return userService.rateTeacher(body, id);
    }
    // Get Teacher Rate
    @GetMapping("/get-teacher-rate/{id}")
    public ResponseEntity<Object> getCourseRate(@PathVariable("id") int id ) throws JsonProcessingException {
        return userService.getTeacherRate(id);
    }

    @GetMapping("/showTeachers")
    public List<UserEntity> getTeachers(@RequestParam UserAccountEnum accountType) throws Exception {
        if (accountType.equals(UserAccountEnum.TEACHER))
            return userService.getUsers(UserAccountEnum.TEACHER);
        else throw new Exception("please write a valid type");

    }

    @GetMapping("/showStudents")
    public List<UserEntity> getStudent(@RequestParam UserAccountEnum accountType) throws Exception {
        if (accountType.equals(UserAccountEnum.USER))
            return userService.getUsers(UserAccountEnum.USER);
        else throw new Exception("please write a valid type");
    }

}
