import Tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new DailyTask("Почистить зубы", Type.PERSONAL,  LocalDateTime.now(), "Пойду чистить зубы"));
        tasks.add(new DailyTask("Почистить зубы", Type.PERSONAL,  LocalDateTime.now(), "Пойду чистить зубы"));
        tasks.add(new OneTimeTask("Почистить зубы", Type.PERSONAL,  LocalDateTime.now(), "Пойду чистить зубы"));

        System.out.print(tasks);


        TaskService.addTask(new WeeklyTask("Починить машину", Type.PERSONAL,  LocalDateTime.of(2023,02,05,15,34), "Почистить зубы"));
        TaskService.addTask(new DailyTask("Добавилось в мапу", Type.PERSONAL,  LocalDateTime.now(), "Почистить зубы"));
        TaskService.addTask(new DailyTask("Добавилось в мапу", Type.PERSONAL,  LocalDateTime.now(), "Почистить зубы"));
        TaskService.getTasks();

        System.out.println(tasks);


    }



}