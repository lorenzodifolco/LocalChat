package ch.supsi.myproj.objectpresentation;

import ch.supsi.myproj.model.Student;

public class StudentCapsPresentation implements Presentable<Student> {

    @Override
    public String present(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student cannot be null");
        }

        String[] arr = (student.getFirstName() + " " + student.getLastName()).split(" ");

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).append(" ");
        }

        return sb.toString().trim();
    }
}
