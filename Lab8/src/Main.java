import java.util.*;

public class Main {
    public static void main(String[] args) {

        /* Do not modify */
        Student s1 = new Student("Maria", "Popescu", 3, 8.5);
        Student s2 = new Student("Ion", "Grigorescu", 2, 8);
        Student s3 = new Student("Ana", "Enescu", 7, 7);
        Student s4 = new Student("Mihai", "Eminovici", 1, 4.45);
        Student s5 = new Student("Andrei", "Radu", 12, 2);

        List<Student> students = new ArrayList<>(List.of(s1, s2, s3, s4, s5));
        List<Student> copyStudents = new ArrayList<>(students);

        List<Integer> numbers = List.of(10, 20, 5, 243, 5556, 312, 566, 245, 122, 5556, 5, 10, 20, 122);
        ArrayList<String> subjects = new ArrayList<>(List.of("PP", "PA", "PCOM", "IOCLA", "AA",
                "SO", "CPL", "EP", "RL", "LFA"));
        Random random = new Random(12);
        /* End of unmodifiable zone */

        /* ------------------------- Task 2 ------------------------- */
        /* --------- Sort using Comparable<Student> interface ------- */
        Collections.sort(students);
        System.out.println(students);

        /* ------------------------- Task 3 ------------------------- */
        /* -------------- Sort using a lambda expression ------------ */
        copyStudents.sort((o1, o2) -> o1.averageGrade > o2.averageGrade ? -1 : o1.averageGrade < o2.averageGrade ? 1 : 0);
        System.out.println(copyStudents);

        /* ------------------------- Task 4 ------------------------- */
        /* ----------- Implement your priority queue here ----------- */
        PriorityQueue<Student> priorityQueue = null;
        priorityQueue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Long.compare(((Student)o1).id, ((Student)o2).id);
            }
        });
        priorityQueue.addAll(students);

        System.out.println(priorityQueue);

        /* ------------------------- Task 6 ------------------------- */
        Map<Student, LinkedList<String>> studentMap = new HashMap<>();
        students.forEach(s -> studentMap.putIfAbsent(s, new LinkedList<>()));

        /*--------- Add 4 random elements in each LinkedList ----------*/
        /* Use the previously declared random object and use subjects.size() as your bound */
        for (Map.Entry<Student, LinkedList<String>> entry : studentMap.entrySet()) {
            entry.getValue().addFirst(subjects.get(random.nextInt(subjects.size())));
            entry.getValue().addFirst(subjects.get(random.nextInt(subjects.size())));
            entry.getValue().addFirst(subjects.get(random.nextInt(subjects.size())));
            entry.getValue().addFirst(subjects.get(random.nextInt(subjects.size())));
        }

        System.out.println(studentMap);

        /* ------------------------- Task 7 ------------------------- */
        /* -------------  No need to add or modify here --------------*/
        LinkedEvenSet linked = new LinkedEvenSet();
        linked.addAll(numbers);

        EvenSet set = new EvenSet();
        set.addAll(numbers);

        TreeEvenSet tree = new TreeEvenSet();
        tree.addAll(numbers);

        System.out.println(linked);
        System.out.println(set);
        System.out.println(tree);
    }
}


class EvenSet extends HashSet<Integer> {
    @Override
    public boolean add(Integer integer) {
        if (integer % 2 == 0)
            return super.add(integer);

        return false;
    }
}


class LinkedEvenSet extends LinkedHashSet<Integer> {
    @Override
    public boolean add(Integer integer) {
        if (integer % 2 == 0)
            return super.add(integer);

        return false;
    }
}


class Student implements Comparable<Student> {
    /* ------------------------- Task 1 ------------------------- */
    /* Add student properties */
    /* Generate getters and setters */
    String name;
    String surname;
    long id;
    double averageGrade;

    public Student(String name, String surname, long id, double averageGrade) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.averageGrade = averageGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    /* Override `compareTo` and `toString` methods */
    @Override
    public int compareTo(Student o) {
        if (this.averageGrade == o.averageGrade) {
            if (this.name.equals(o.name))
                return this.surname.compareTo(o.surname);
            else
                return this.name.compareTo(o.name);
        } else
                return this.averageGrade > o.averageGrade ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", averageGrade=" + averageGrade +
                '}';
    }

    /* ------------------------- Task 5 ------------------------- */
    /* Override `equals` and `hashCode` methods */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if ((o instanceof Student) == false)
            return false;

        Student student = (Student) o;
        return id == student.id
                && Double.compare(student.averageGrade, averageGrade) == 0
                && Objects.equals(name, student.name)
                && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id, averageGrade);
    }
}

class TreeEvenSet extends TreeSet<Integer> {
    @Override
    public boolean add(Integer integer) {
        if (integer % 2 == 0)
            return super.add(integer);

        return false;
    }
}
