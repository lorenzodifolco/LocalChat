package ch.supsi.myproj.repository;

import ch.supsi.myproj.model.StudentCourse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class StudentCourseRepository {

    private ArrayList<StudentCourse> studentsCourses;

    public StudentCourseRepository(String repositoryName){
        this.studentsCourses = this.loadStudentsCourses(repositoryName);
    }

    public ArrayList<StudentCourse> getAll() {
        return studentsCourses;
    }

    ///

    private ArrayList<StudentCourse> loadStudentsCourses(String repositoryName){
        Properties properties = new Properties();
        studentsCourses = new ArrayList<>();

        try {
            properties.load(CourseRepository.class.getClassLoader().getResourceAsStream(repositoryName));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                String[] line = ((String) entry.getValue()).split(",");

                Long studentId = Long.valueOf(line[0]);
                Long courseId = Long.valueOf(line[1]);

                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(studentId);
                studentCourse.setCourseId(courseId);

                studentsCourses.add(studentCourse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentsCourses;
    }

}
