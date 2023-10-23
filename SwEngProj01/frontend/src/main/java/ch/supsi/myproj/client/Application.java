package ch.supsi.myproj.client;

import ch.supsi.myproj.model.Course;
import ch.supsi.myproj.model.Student;
import ch.supsi.myproj.objectpresentation.CourseUpperCasePresentation;
import ch.supsi.myproj.objectpresentation.StudentLowerCasePresentation;
import ch.supsi.myproj.service.StudentsCoursesService;
import ch.supsi.myproj.stringpresentation.CapsPresentation;
import ch.supsi.myproj.stringpresentation.LowerCasePresentation;

public class Application {

    public static void main(String[] args) throws IllegalArgumentException {
        StudentsCoursesService studentsCoursesService = new StudentsCoursesService();

        // object presentable
        ch.supsi.myproj.objectpresentation.Presentable<Course> coursePresentation1 = new CourseUpperCasePresentation();
        ch.supsi.myproj.objectpresentation.Presentable<Student> studentPresentation1 = new StudentLowerCasePresentation();

        for (Course course : studentsCoursesService.getCourses()) {
            System.out.println("- " + coursePresentation1.present(course));
            for (Student student : studentsCoursesService.getStudentsByCourseId(course.getId())) {
                System.out.println("  - " + studentPresentation1.present(student));
            }
        }

        // string presentable
        ch.supsi.myproj.stringpresentation.Presentable<String> coursePresentation2 = new LowerCasePresentation();
        ch.supsi.myproj.stringpresentation.Presentable<String> studentPresentation2 = new CapsPresentation();

        for (Course course : studentsCoursesService.getCourses()) {
            System.out.println("- " + coursePresentation2.present(course.getName()));
            for (Student student : studentsCoursesService.getStudentsByCourseId(course.getId())) {
                System.out.println("  - " + studentPresentation2.present(student.getFirstName() + " " + student.getLastName()));
            }
        }
    }

}
