package ch.supsi.myproj.objectpresentation;

import ch.supsi.myproj.model.Course;

public class CourseUpperCasePresentation implements Presentable<Course> {


    @Override
    public String present(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("course cannot be null");
        }

        return course.getName().toUpperCase();
    }
}
