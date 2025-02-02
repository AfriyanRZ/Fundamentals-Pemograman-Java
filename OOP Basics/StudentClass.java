class Student {
    String name;
    int age;
    double gpa;

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
    }
}

public class StudentClass {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "John Doe";
        s1.age = 20;
        s1.gpa = 3.7;
        s1.displayInfo();
    }
}