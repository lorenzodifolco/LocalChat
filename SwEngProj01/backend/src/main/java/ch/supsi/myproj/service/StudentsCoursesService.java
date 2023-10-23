package ch.supsi.myproj.service;

import ch.supsi.myproj.model.Course;
import ch.supsi.myproj.model.Student;
import ch.supsi.myproj.model.StudentCourse;
import ch.supsi.myproj.repository.CourseRepository;
import ch.supsi.myproj.repository.StudentCourseRepository;
import ch.supsi.myproj.repository.StudentRepository;

import java.util.ArrayList;

public class StudentsCoursesService {

    private static final String STUDENT_COURSE_REPOSITORY_NAME = "student_course.properties";
    private static final String STUDENT_REPOSITORY_NAME = "student.properties";
    private static final String COURSE_REPOSITORY_NAME = "course.properties";

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public StudentsCoursesService() {
        studentRepository = new StudentRepository(STUDENT_REPOSITORY_NAME);
        courseRepository = new CourseRepository(COURSE_REPOSITORY_NAME);
        studentCourseRepository = new StudentCourseRepository(STUDENT_COURSE_REPOSITORY_NAME);
    }

    public ArrayList<Course> getCourses() {
        return courseRepository.getAll();
    }

    public ArrayList<Student> getStudents() {
        return studentRepository.getAll();
    }

    public ArrayList<Student> getStudentsByCourseId(Long courseId) {
        ArrayList<Student> students = new ArrayList<>();

        for (StudentCourse studentCourse : studentCourseRepository.getAll()) {
            if (studentCourse.getCourseId() == courseId) {
                students.add(studentRepository.getById(studentCourse.getStudentId()));
            }
        }

        return students;
    }

}
