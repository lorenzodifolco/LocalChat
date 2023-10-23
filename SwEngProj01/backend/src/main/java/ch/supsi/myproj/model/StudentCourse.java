package ch.supsi.myproj.model;

public class StudentCourse extends ModelObject{

    private Long studentId;
    private Long courseId;

    public StudentCourse() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

}
