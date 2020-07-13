package com.PG.Strings;

import java.util.Arrays;
import java.util.Comparator;

public class Student {
    int rollNo;
    String name;

    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void sortStudents(Student[] students) {
        Arrays.sort(students, new SortByRollAndName());
    }
}

class SortByRollAndName implements Comparator<Student> {
    public int compare(Student a, Student b) {
        if( a.getName().compareTo(b.getName()) != 0) {
                return a.getName().compareTo(b.getName());
        } else {
            return a.getRollNo() - b.getRollNo();
        }
    }
}

//class Main {
//
//}
