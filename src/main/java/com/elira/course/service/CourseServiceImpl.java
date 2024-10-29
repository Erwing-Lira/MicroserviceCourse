package com.elira.course.service;

import com.elira.course.client.StudentClient;
import com.elira.course.dto.StudentDTO;
import com.elira.course.entities.Course;
import com.elira.course.http.reponse.StudentByCourseResponse;
import com.elira.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<StudentByCourseResponse> findStudentsByIdCourse(Long idCourse) {

        // Check the course
        Optional<Course> courseOptional = courseRepository.findById(idCourse);

        if(courseOptional.isPresent()) {
            // Go to the api
            List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);
            return Optional.of(
                    StudentByCourseResponse.builder()
                            .idCourse(courseOptional.get().getId())
                            .courseName(courseOptional.get().getName())
                            .teacher(courseOptional.get().getTeacher())
                            .students(studentDTOList)
                            .build()
            );
        }
        return Optional.empty();
    }
}
