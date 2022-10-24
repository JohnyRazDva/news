package by.academy.news.entity.news;

import by.academy.news.entity.user.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author Eugene Petrov
 */
@Entity
@Table(name = "news")
public class News {

    @Id
    private int id;

    @Column(name = "title", columnDefinition = "TEXT")
    @Pattern(regexp = "((.|\\s)*\\S(.|\\s)*){3,}", message = "Title must contain 3 or more characters")
    private String title;

    @Column(name = "brief", columnDefinition = "TEXT")
    @Pattern(regexp = "((.|\\s)*\\S(.|\\s)*){10,}", message = "Brief must contain 10 or more characters")
    private String brief;

    @Column(name = "content", columnDefinition = "TEXT")
    @Pattern(regexp = "(.|\\s)*\\S(.|\\s){60,}", message = "Content must contain 60 or more characters")
    private String content;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "username")
    private User user;

    public News() {
    }

    public News(int id, String title, String brief, String content, LocalDate date, User user) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
