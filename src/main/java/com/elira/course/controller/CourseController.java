package com.elira.course.controller;

import com.elira.course.entities.Course;
import com.elira.course.http.reponse.StudentByCourseResponse;
import com.elira.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveCourse(
            @RequestBody Course course
    ) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllStudents() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Long id
    ) {
        Optional<Course> courseOptional = courseService.findById(id);

        if (courseOptional.isPresent()) {
            return ResponseEntity.ok(courseOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search-students-by-course/{idCourse}")
    public ResponseEntity<?> findStudentsByIDCourse(
            @PathVariable Long idCourse
    ) {
        Optional<StudentByCourseResponse> studentByCourseResponseOptional = courseService.findStudentsByIdCourse(idCourse);

        if (studentByCourseResponseOptional.isPresent()) {
            return ResponseEntity.ok(studentByCourseResponseOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
