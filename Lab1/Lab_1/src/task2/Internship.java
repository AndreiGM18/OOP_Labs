package task2;

import java.util.concurrent.ThreadLocalRandom;

public class Internship {
    String internName;
    double minGrade;
    Student[] students;

    public double getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(double minGrade) {
        this.minGrade = minGrade;
    }

    public String getInternName() {
        return internName;
    }

    public void setInternName(String internName) {
        this.internName = internName;
    }

    public void chooseCandidatesRandomly() {
        int randInx = ThreadLocalRandom.current().nextInt(students.length);
        Student randStud = students[randInx];
        System.out.println(randStud);
    }

    public void chooseCandidatesForInterview() {
        for (int i = 0; i < students.length; ++i) {
            if (students[i].grade >= minGrade) {
                System.out.println("Candidate " + students[i].name + " got a phone interview at " + internName);
            }
        }
    }
}
