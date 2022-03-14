package Classes;

import java.util.Date;

public class Task {
    private String title;
    private String Description=" ";
    private Date Date;
    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
