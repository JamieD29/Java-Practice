import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        int choice = 0;
        String id;
        String name;
        String email;
        double gpa;
        StudentManager students = new StudentManager();
        System.out.println("**********Student Management System**********");
        do{
            System.out.println("\n****************");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. View Student (ID)");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:{
                    
                    System.out.println("***** Add New Student *****");
                    System.out.print("Enter Student ID: ");
                    id = input.nextLine();
                    if(students.findStudentById(id) != null){
                        System.out.println("Student with ID " + id + " already exists");
                        break;
                    }
                    System.out.print("Enter Student Name: ");
                    name = input.nextLine();
                    System.out.print("Enter Student Email: ");
                    email = input.nextLine();
                    System.out.print("Enter Student GPA: ");
                    gpa = input.nextDouble();
                    input.nextLine();
                    Student newStudent = new Student(id, name, email, gpa);
                    students.addStudent(newStudent);
                    System.out.println("\nStudent Added Successfully");
                    break;
                }
                case 2:{
                    int updateChoice;
                    System.out.println("***** Update Student *****");
                    System.out.print("Enter Student ID: ");
                    id = input.nextLine();
                    Student updateStudent = students.findStudentById(id);
                    if(updateStudent != null){
                        
                        System.out.println("Do you want to update Name, Email, GPA?");
                        System.out.println("1. Name    2. Email    3. GPA");
                        System.out.print("Enter number: ");

                        updateChoice = input.nextInt();
                        input.nextLine();
                        switch (updateChoice) {
                            case 1:{
                                System.out.print("Enter updated name: ");
                                name = input.nextLine();
                                updateStudent.setName(name);
                                System.out.println("\nStudent Updated Successfully");
                                break;
                            }
                            case 2:{
                                System.out.print("Enter updated email: ");
                                email = input.nextLine();
                                updateStudent.setEmail(email);
                                System.out.println("\nStudent Updated Successfully");
                                break;
                            }
                            case 3:{
                                System.out.print("Enter updated gpa: ");
                                gpa = input.nextDouble();
                                updateStudent.setGpa(gpa);
                                System.out.println("\nStudent Updated Successfully");
                                break;
                            }
                            default:{
                                System.out.println("Invalid choice number.");
                                break;
                            }
                        }
                    }else{
                        System.out.println("Try again");
                    }
                   break;
                }
                case 3:{
                    System.out.println("***** Delete Student *****");
                    System.out.print("Enter Student ID: ");
                    id = input.nextLine();
                    if(students.findStudentById(id) != null){
                        students.removeStudent(id);
                        System.out.println("\nStudent Deleted Successfully");
                    }else{
                        System.out.println("Student Not Found");
                    }

                break;
                }
                case 4:{
                    System.out.println("**** View All Student *****");
                    students.displayAllStudents();

                    break;
                }
                case 5:{
                    System.out.println("**** View Student (ID)*****");
                    System.out.print("Enter Student ID: ");
                    id = input.nextLine();
                    Student student =
                            students.findStudentById(id);
                    if(student != null){
                        System.out.println(student);
                    }
                    else{
                        System.out.println("\nStudent not found");
                    }
                    break;
                }
                case 6:{
                    isExit = true;
                    System.out.println("**** Exit Program ***");
                    break;
                }
                default:{
                    System.out.println("\nInvalid choice number.");
                }
                
            }
           
       }while(!isExit);
       input.close();
    }
}
