import java.util.*;

class Student {
    private int id;
    private String name, surname;

    public Student (int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Student (Student st) {
        this.id = st.id;
        this.name = st.name;
        this.surname = st.surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show () {
        System.out.printf("Name: %s\nSurname: %s\nStudent ID: %d\n%n", name, surname, id);
    }
}

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        String name = scanner.next();
        String surname = scanner.next();

        Student student1 = new Student(id, name, surname);

        Student student2 = new Student(student1);

        student2.setName("Cezar");
        student2.setSurname("Ghiu");

        student1.show();

        student2.show();
    }
}