package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.List.*;

public class TaskService {
    private static final Map<Integer, Task> taskMap = new HashMap<>();
    private final Collection<Task> removedTasks = new ArrayList<>();


    public static void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public static void getTasks() {
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            System.out.println("ID: " + taskMap.getKey() + " " + taskMap.getValue());
        }
    }

}
