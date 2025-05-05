package model;

/** We are creating a task class that keeps items needed for creating tasks */
public class Task {
    private final int id;
    private String description;
    private Status status;

    public Task (int id, String description) {
        this.id = id;
        this.description = description;
        this.status = Status.PENDING;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void markAsDone() {
        this.status = Status.DONE;
    }

    @Override
    public String toString() {
        return "[" + status + "]" + ". " + description;
    }

}