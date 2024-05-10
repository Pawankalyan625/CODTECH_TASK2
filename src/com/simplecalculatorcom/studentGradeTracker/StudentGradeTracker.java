package com.simplecalculatorcom.studentGradeTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Subject {
	private String name;
	private double grade;

	public Subject(String name) {
		this.name = name;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public double getGrade() {
		return grade;
	}

	public String getName() {
		return name;
	}
}

class Student {
	private String name;
	private List<Subject> subjects;

	public Student(String name) {
		this.name = name;
		subjects = new ArrayList<>();
	}

	public void addSubject(String name) {
		subjects.add(new Subject(name));
	}

	public void setGradeForSubject(String name, double grade) {
		for (Subject subject : subjects) {
			if (subject.getName().equalsIgnoreCase(name)) {
				subject.setGrade(grade);
				return;
			}
		}
		System.out.println("Subject not found!");
	}

	public double calculateAverageGrade() {
		if (subjects.isEmpty()) {
			return 0;
		}
		double totalGrade = 0;
		for (Subject subject : subjects) {
			totalGrade += subject.getGrade();
		}
		return totalGrade / subjects.size();
	}

	public String calculateLetterGrade() {
		double averageGrade = calculateAverageGrade();
		if (averageGrade >= 90) {
			return "A";
		} else if (averageGrade >= 80) {
			return "B";
		} else if (averageGrade >= 70) {
			return "C";
		} else if (averageGrade >= 60) {
			return "D";
		} else {
			return "F";
		}
	}

	public String getName() {
		return name;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}
}

class Color {

	// ANSI escape codes for colors
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_PRUPLE = "\u001B[35m";
	public static final String ANSI_BLUE = "\u001B[36m";
	public static final String ANSI_BOLD = "\u001B[1m"; // Bold text
	public static final String ANSI_BG_BLUE = "\u001B[48;5;12m"; // Background color blue

}

public class StudentGradeTracker {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Color r = new Color();
		System.out.println(r.ANSI_BG_BLUE + r.ANSI_BOLD + "Welcome to the Student Grade Tracker!" + r.ANSI_RESET);

		System.out.print(r.ANSI_YELLOW + "Enter student name: " + r.ANSI_RESET);
		String studentName = scanner.nextLine();

		System.out.print(r.ANSI_YELLOW + "Enter the number of subjects: " + r.ANSI_RESET);
		int numSubjects = scanner.nextInt();

		Student student = new Student(studentName);

		for (int i = 0; i < numSubjects; i++) {
			System.out.print(r.ANSI_YELLOW + "Enter name of subject " + (i + 1) + ": " + r.ANSI_RESET);
			String subjectName = scanner.next();
			student.addSubject(subjectName);
		}

		for (Subject subject : student.getSubjects()) {
			System.out.print(r.ANSI_YELLOW + "Enter grade for " + student.getName() + " in " + subject.getName() + ": "
					+ r.ANSI_RESET);
			double grade = scanner.nextDouble();
			student.setGradeForSubject(subject.getName(), grade);
		}

		System.out.println("\n" + "-".repeat(30));
		System.out.println("| " + r.ANSI_YELLOW + "Student Name:" + r.ANSI_RESET + "   | " + student.getName());

		for (Subject subject : student.getSubjects()) {
			System.out.printf("| " + r.ANSI_YELLOW + "Subject:" + r.ANSI_RESET + "        | %-35s%n",
					subject.getName());
			System.out.printf("| " + r.ANSI_YELLOW + "Grade:" + r.ANSI_RESET + "          | %-35.2f%n",
					subject.getGrade());

		}
		System.out.printf("| " + r.ANSI_YELLOW + "Average Grade:  " + r.ANSI_RESET + "|" + " %-38.2f%n",
				student.calculateAverageGrade());
		System.out.println(
				"| " + r.ANSI_YELLOW + "Letter Grade:   " + r.ANSI_RESET + "| " +r.ANSI_BG_BLUE+r.ANSI_BOLD+"  "+ student.calculateLetterGrade()+"  "+r.ANSI_RESET);
		System.out.println("-".repeat(30));
	}
}
