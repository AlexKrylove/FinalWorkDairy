import Exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;


public class MonthlyTask extends Task{

    public MonthlyTask(String title, Type type, String description, String dateTime) {
        super(title, type, description, dateTime);
    }

    public boolean appearsIn(LocalDate date) {
        Period period = Period.between(getDateTime().toLocalDate(), date);
        return (period.getYears() >= 0 && period.getMonths() >= 0 && period.getDays() == 0);
    }



    @Override
    public String toString() {
        return
                "Ежемесячная задача" + "\n" +
                        "id задачи: " + getId() + "\n" +
                        "Тип задачи: " + getType() + "\n" +
                        "Время задачи: " + getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                        "Наименование: " + getTitle() + "\n" +
                        "Описание: " + getDescription() + "\n";
    }
}