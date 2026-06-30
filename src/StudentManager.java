import java.util.ArrayList;
//write file
import java.io.BufferedWriter;
import java.io.FileWriter;

//load file
import java.io.BufferedReader;
import java.io.FileReader;

//exception
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class StudentManager {
    private ArrayList<Student> students;


    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

//    public void setStudents(ArrayList<Student> students) {
//        this.students = students;
//    }
    //Overall Statistics
    public int getTotalStudents() {
        return students.size();
    }
    // GPA Statistics
    public double getMaxGpa(){
        return students.stream()
                .mapToDouble(Student::getGpa)
                .max()
                .orElse(0.0);
    }
    public double getMinGpa(){
        return students.stream()
                .mapToDouble(Student::getGpa)
                .min()
                .orElse(0.0);
    }

    public double getAverageGpa(){
        return students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }
    //Top student and Excellent Students (GPA >= 8)
    public Student getTopStudent (){
        if (students == null || students.isEmpty()) {
            return null;
        }
        return students.stream()
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
    }
    public List<Student> getExcellentStudents(){
        return students.stream().filter(student -> student.getGpa() >= 8.0).collect(Collectors.toList());
    }

    // Status
    public long getStudentCountByStatus(StudentStatus status) {
        return students.stream()
                .filter(student -> student.getStatus() == status)
                .count();
    }

    //Median GPA
    public double getMedianGpa(){
        List<Double> listGpa = students.stream().map(Student::getGpa).sorted().toList();
//        if(listGpa.size() % 2 != 0){
//            int middle = (int) Math.floor((double) listGpa.size() / 2);
//            return listGpa.get(middle);
//        }else{
//            int mid = listGpa.size() / 2;
//            List<Double> preList = listGpa.subList(0, mid);
//            List<Double> sufList = listGpa.subList(mid, listGpa.size());
//            return (preList.getLast() + sufList.getFirst()) / 2;
//        }
        if(listGpa.isEmpty()){
            return 0;
        }

        int mid = listGpa.size() / 2;

        if(listGpa.size() % 2 != 0){
            return listGpa.get(mid);
        }

        return (listGpa.get(mid - 1) + listGpa.get(mid)) / 2;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        students.stream().sorted(Comparator.comparing(Student::getId)).forEach(System.out::println);

    }

    public void sortByGpaAscending() {
        students.stream().sorted(
                Comparator.comparingDouble(Student::getGpa)
        ).forEach(System.out::println);
    }

    public void sortByGpaDescending() {
        students.stream().sorted(
                Comparator.comparingDouble(Student::getGpa)
                        .reversed()
        ).forEach(System.out::println);
    }

    public void sortByName() {
        students.stream().sorted(
                Comparator.comparing(Student::getName)
        ).forEach(System.out::println);
    }

    public void filterStatus(StudentStatus filterStatus) {
        students.stream().filter(student -> student.getStatus() == filterStatus).forEach(System.out::println);
    }

    public void addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
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

        for (Student student : students) {

            if (student.getId().equals(id)) {
                studentToRemove = student;
                break;
            }

        }

        if (studentToRemove != null) {
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

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(student.getId() + "," +
                        student.getName() + "," +
                        student.getEmail() + "," +
                        student.getGpa() + "," +
                        student.getStatus());
                writer.newLine();
            }


            System.out.println("Students saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
            throw new RuntimeException(e);
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String email = data[2];
                double gpa = Double.parseDouble(data[3]);
                StudentStatus status = StudentStatus.valueOf(data[4]);

                Student student = new Student(id, name, email, gpa, status);
                students.add(student);
            }

        } catch (IOException e) {
            System.out.println("No existing file found.");
            throw new RuntimeException(e);
        }
    }
}
