package ex2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/*
 *
 *  ¨¨¨¨¨¨¨¨¨¨*
 *  ¨¨¨¨¨¨¨¨¨**
 *  ¨¨¨¨¨¨¨¨¨*o*
 *  ¨¨¨¨¨¨¨¨*☺*o*
 *  ¨¨¨¨¨¨¨***o***
 *  ¨¨¨¨¨¨**o**☺*o*
 *  ¨¨¨¨¨**☺**o**o**
 *  ¨¨¨¨**o**☺***☺*o*                 ECHIPA OOP vă urează un Crăciun fericit,
 *  ¨¨¨*****☺*o**o****                      și un AN NOU fără restanțe!
 *  ¨¨**☺**o*****o**☺**
 *  ¨void*o*void*o*void*o*
 *  ****o***☺**o***o***☺*
 *  ¨¨¨¨¨____!_!____
 *  ¨¨¨¨¨\_________/¨¨
 *
 */

class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> right;
    private Node<T> left;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }
}

interface Tree<T extends Comparable<T>> {
    void addValue(T value);

    void addAll(List<T> values);

    HashSet<T> getValues(T inf, T sup);

    int size();

    boolean isEmpty();
}

class TreeImpl<T extends Comparable<T>> implements Tree<T> {
    public Node<T> root = null;

    Node insert(Node node, T value) {
        if (node == null) {
            node = new Node(value);
            return node;
        } else if (node.getValue().compareTo(value) >= 0) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (node.getValue().compareTo(value) < 0) {
            node.setRight(insert(node.getRight(), value));
        }
        return node;
    }

    @Override
    public void addValue(T value) {
        root = insert(root, value);
    }

    @Override
    public void addAll(List<T> values) {
        for (T value : values) {
            addValue(value);
        }
    }

    public void inorderGet(Node<T> node, T inf, T sup, LinkedList<T> list) {
        if (node != null) {
            inorderGet(node.getLeft(), inf, sup, list);
            if (node.getValue().compareTo(inf) >= 0 && node.getValue().compareTo(sup) <= 0)
                list.add(node.getValue());
            inorderGet(node.getRight(), inf, sup, list);
        }
    }

    @Override
    public HashSet<T> getValues(T inf, T sup) {
        LinkedList<T> list = new LinkedList<>();
        inorderGet(root, inf, sup, list);
        return new HashSet<>(list);
    }

    static int size;
    public void inorderSize(Node<T> node) {
        if (node != null) {
            inorderSize(node.getLeft());
            ++size;
            inorderSize(node.getRight());
        }
    }

    @Override
    public int size() {
        size = 0;
        inorderSize(root);
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}

class Student implements Comparable<Student> {
    private final Float grade;

    public Student(Float grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Student student) {
        return this.grade.compareTo(student.grade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "grade=" + grade +
                '}';
    }
}

public class Main {
    private static Integer testNr = 0;

    public static void main(String[] args) {

        final Tree<Student> tree = new TreeImpl<>();
        final List<Student> studentList = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            studentList.add(createGenericStudent((float) i));
        }

        printTestNr(); // 1
        System.out.println(tree.isEmpty());
        System.out.println(tree.size());
        System.out.println();

        printTestNr(); // 2
        for (int i = 0; i < 5; i++) {
            tree.addValue(createGenericStudent((float) i));
        }
        System.out.println(tree.size());
        System.out.println();

        printTestNr(); // 3
        tree.addAll(studentList);
        System.out.println(tree.isEmpty());
        System.out.println(tree.size());
        System.out.println();

        printTestNr(); // 4
        final Student studentThatPassedExam = new Student(5.0f);
        final Student studentThatWorkedHardToPassTheExam = new Student(10.0f);
        final HashSet<Student> values = tree.getValues(studentThatPassedExam, studentThatWorkedHardToPassTheExam);
        System.out.println(values.size());
        values.stream().sorted().forEach(System.out::println);
    }

    private static void printTestNr() {
        System.out.println("TEST " + ++testNr + " result:");
    }

    private static Student createGenericStudent(final Float grade) {
        return new Student(grade);
    }
}
