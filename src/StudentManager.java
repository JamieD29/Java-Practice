import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

//    public void setStudents(ArrayList<Student> students) {
//        this.students = students;
//    }

    public void displayAllStudents() {
        if(students.isEmpty()){
            System.out.println("No students found");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void addStudent(Student student) {
        if(findStudentById(student.getId()) != null){
            return;
        }
        students.add(student);
    }

//    public void updateStudent(Student student) {
//        if(findStudentById(student.getId()) != null){
//            students.set(students.indexOf(student), student);
//        }
//
//    }

    public void removeStudent(String id) {


        Student studentToRemove = null;

        for(Student student : students){

            if(student.getId().equals(id)){
                studentToRemove = student;
                break;
            }

        }

        if(studentToRemove != null){
            students.remove(studentToRemove);
        }
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }

        }
        return null;
    }

}
