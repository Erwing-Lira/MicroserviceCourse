package com.elira.course.service;

import com.elira.course.entities.Course;
import com.elira.course.http.reponse.StudentByCourseResponse;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course saveCourse(Course course);

    // Invoke other api
    Optional<StudentByCourseResponse> findStudentsByIdCourse(Long idCourse);
}
