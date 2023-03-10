import Exception.IncorrectArgumentException;
import Exception.TaskNotFoundException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n4. Редактировать наименование задачи\n5. Редактировать описание задачи\n0. Выход");
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            try {
                                inputTask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 2:
                            System.out.print("Выберите id задачи для удаления: ");
                            try {
                                delDask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 3:
                            System.out.println("Введите дату [dd.MM.yyyy]: ");
                            try {
                                dateFormat(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 4:
                            System.out.print("Выберите id задачи для редактирования наименования: ");
                            try {
                                editTitle(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 5:
                            System.out.print("Выберите id задачи для редактирования описания: ");
                            try {
                                editDescription(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

        private static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Введите название задачи: ");
        scanner.nextLine();
        String taskName = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        System.out.println("Введите дату начала задачи [dd.MM.yyyy HH:mm]: ");
        String taskDate = scanner.nextLine();
        Type typeTask;
        label:
        while (true) {
            System.out.println("Выберите повторяемость задачи: ");
            System.out.println("1.Однократная 2.Ежедневная 3.Еженедельная 4.Ежемесячная 5.Ежегодная");
            if (scanner.hasNextInt()) {
                int replayTask = scanner.nextInt();
                switch (replayTask) {
                    case 1:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new OneTimeTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 2:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new DailyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 3:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new WeeklyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 4:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new MonthlyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 5:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new YearlyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    default:
                        System.out.println("Выберите от 1 до 5");
                        break;
                }
            } else {
                scanner.next();
            }
        }

    }

        public static int checkId(Scanner scanner) throws IncorrectArgumentException {
        int intId;
        try {
            intId = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IncorrectArgumentException("Некорректный ввод");
        }
        return intId;
    }


    public static Type typeTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Выберите тип задачи: ");
        System.out.println("1.Личная, 2.Рабочая");
        String input = scanner.next();
        Type typeTask = (Objects.equals(input, "1")) ? Type.PERSONAL : (Objects.equals(input, "2")) ? Type.WORK : null;
        if (typeTask == null) {
            throw new IncorrectArgumentException("Некорректно указан тип задачи, добавьте задачу повторно");
        }
        return typeTask;
    }


    public static void delDask(Scanner scanner) throws IncorrectArgumentException {
        int inputId = checkId(scanner);
        try {
            TaskService.removeTask(inputId);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void dateFormat(Scanner scanner) throws IncorrectArgumentException {
        LocalDate date;
        try {
            scanner.nextLine();
            String str = scanner.nextLine();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse(str, dtf);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Некорректно введена дата");
        }
        try {
            TaskService.getAllByDate(date);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }



    public static void editTitle(Scanner scanner) throws IncorrectArgumentException {
        int inputId = checkId(scanner);
        System.out.println("Введите новый заголовок");
        scanner.nextLine();
        String newTitle = scanner.nextLine();
        try {
            TaskService.updateTitle(inputId, newTitle);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void editDescription(Scanner scanner) throws IncorrectArgumentException {
        int inputId = checkId(scanner);
        System.out.println("Введите новое описание");
        scanner.nextLine();
        String newTitle = scanner.nextLine();
        try {
            TaskService.updateDescription(inputId, newTitle);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }
}