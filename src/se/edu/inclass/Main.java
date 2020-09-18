package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");

        //printDeadlines(tasksData);
        printDataUsingStreams(tasksData);
        printDeadlinesUsingStreams(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));

        for(Task t:filterByString(tasksData, "11")) {
            System.out.println(t);
        }

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Calculating count using streams");
        int count;
        count = (int) tasksData.stream()
                .filter(task -> task instanceof Deadline)
                .count();
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing data using streams");
        tasksData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using streams");
        tasksData.stream()
                .filter(task -> task instanceof Deadline)
                .sorted(Comparator.comparing(a -> a.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> taskData, String filterString) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) taskData.stream()
                .filter(task -> task.getDescription().contains(filterString))
                .collect(Collectors.toList());

        return filteredTaskList;
    }
}
