package task2;

import task2.Internship;

public class JobMarket {
    public static void main(String[] args) {
        Student[] students = new Student[4];

        students[0] = new Student();
        students[0].name = "Gigel";

        students[1] = new Student();
        students[1].name = "Dorel";

        students[2] = new Student();
        students[2].name = "Marcel";

        students[3] = new Student();
        students[3].name = "Ionel";

        Internship[] Its = new Internship[4];

        Its[0] = new Internship();
        Its[0].internName = "Google";

        Its[1] = new Internship();
        Its[1].internName = "Amazon";

        Its[2] = new Internship();
        Its[2].internName = "Facebook";

        Its[3] = new Internship();
        Its[3].internName = "Microsoft";

        Its[0].chooseCandidatesForInterview();
        Its[1].chooseCandidatesForInterview();
        Its[2].chooseCandidatesForInterview();
        Its[3].chooseCandidatesForInterview();
    }
}