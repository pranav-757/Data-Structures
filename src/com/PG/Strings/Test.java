package com.PG.Strings;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Student[] students = {new Student(10, "rahul"), new Student(12, "aman"), new Student(11, "aman"),
                new Student(2, "naman") };

        Student.sortStudents(students);

        for(Student s : students) {
            System.out.println(s.getName() + "  " + s.getRollNo());
        }
        //Arrays.sort(students, new SortBy);
    }
}
