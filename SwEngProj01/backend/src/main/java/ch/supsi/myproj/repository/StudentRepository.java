package ch.supsi.myproj.repository;

import ch.supsi.myproj.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class StudentRepository {

    private ArrayList<Student> students;

    public StudentRepository(String repositoryName){
        this.students = this.loadStudents(repositoryName);
    }

    public Student getById(Long id){
        return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }

    public ArrayList<Student> getAll() {
        return students;
    }

    ///

    private ArrayList<Student> loadStudents(String repositoryName){
        Properties properties = new Properties();
        students = new ArrayList<>();

        try {
            properties.load(StudentRepository.class.getClassLoader().getResourceAsStream(repositoryName));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Long id = Long.parseLong((String) entry.getKey());
                String[] student = ((String) entry.getValue()).split(",");

                Student s = new Student();
                s.setFirstName(student[0]);
                s.setLastName(student[1]);
                s.setId(id);

                students.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

}
