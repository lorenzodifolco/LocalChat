package ch.supsi.myproj.objectpresentation;

import ch.supsi.myproj.model.Course;

public class CourseLowerCasePresentation implements Presentable<Course> {
    
    @Override
    public String present(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("course cannot be null");
        }

        if (course.getName() == null) {
            throw new IllegalArgumentException("course name cannot be null");
        }

        return course.getName().toLowerCase();
    }

}
