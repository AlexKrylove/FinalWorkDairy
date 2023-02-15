package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MonthlyTask  extends Task {
    public MonthlyTask(String title, Type type, LocalDateTime dataTime, String description) {
        super(title, type, dataTime, description);
    }
    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.getMonth().equals(getDataTime().getMonth());
    }

}
