package by.academy.news.entity.user;

import javax.persistence.*;

/**
 * @author Eugene Petrov
 */
@Entity
@Table(name = "authorities")
public class Authorities {
    @Id
    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Authorities(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public Authorities() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

