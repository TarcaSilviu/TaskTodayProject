package Classes;

import java.util.Date;

public class Task {
    private String title;
    private String Description=" ";
    private Date date;
    public Task(String title) {
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDate(){return date;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setDate(Date date){this.date=date;}
}
