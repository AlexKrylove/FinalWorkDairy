package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public abstract class Task {
    private final int id;
    private static int idGenerator=1;
    private String title;
    private Tasks.Type type;

    private LocalDateTime dataTime;
    private String description;

    public abstract boolean appearsIn(LocalDate localDate);

    public Task(String title, Tasks.Type type, LocalDateTime dataTime, String description) {
        this.id = idGenerator++;
        this.title = title;
        this.type = type;
        this.dataTime = dataTime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Id задачи:" + id +", Заголовок: " + title +
                " Тип задачи: " + type.type +
                " Дата: " + dataTime +
                " Описание: " + description;
    }
    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idGenerator == task.idGenerator && id == task.id && Objects.equals(title, task.title) && Objects.equals(type, task.type) && Objects.equals(dataTime, task.dataTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenerator, title, type, id, dataTime, description);
    }





}
