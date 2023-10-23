package ch.supsi.myproj.objectpresentation;

import ch.supsi.myproj.model.Student;

public class StudentLowerCasePresentation implements Presentable<Student> {

    @Override
    public String present(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student cannot be null");
        }

        return (student.getFirstName() + " " + student.getLastName()).toLowerCase();
    }
}
