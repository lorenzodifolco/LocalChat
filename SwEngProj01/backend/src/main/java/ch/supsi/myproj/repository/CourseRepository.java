package ch.supsi.myproj.repository;

import ch.supsi.myproj.model.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class CourseRepository {

    private ArrayList<Course> courses;

    public CourseRepository(String repositoryName) {
        this.courses = this.loadCourses(repositoryName);
    }

    public Course getById(Long id){
        return courses.stream().filter(course -> course.getId() == id).findFirst().orElse(null);
    }

    public ArrayList<Course> getAll() {
        return courses;
    }

    ///

    private ArrayList<Course> loadCourses(String repositoryName) {
        Properties properties = new Properties();
        courses = new ArrayList<>();

        try {
            properties.load(CourseRepository.class.getClassLoader().getResourceAsStream(repositoryName));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Long id = Long.parseLong((String) entry.getKey());
                String name = (String)entry.getValue();

                Course c = new Course();
                c.setId(id);
                c.setName(name);

                courses.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }

}
