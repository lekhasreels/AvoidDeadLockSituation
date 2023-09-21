package com.user;

import java.util.Scanner;

public class ConferenceRoomDeadlock {
	private static final Object doorLock = new Object();
    private static final Object keyLock = new Object();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter the number of employees: ");
			int numEmployees = scanner.nextInt();

			for (int i = 1; i <= numEmployees; i++) {
			    Employee employee = new Employee("Employee " + i);
			    employee.start();
			}
		}
    }
    static class Employee extends Thread {
        private final String name;

        Employee(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " is waiting for the key.");
            synchronized (keyLock) {
                System.out.println(name + " has the key.");
                try {
                    Thread.sleep(100); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " is at the door.");
                synchronized (doorLock) {
                    System.out.println(name + " enters the conference room.");
                }
            }
        }
    }
	}