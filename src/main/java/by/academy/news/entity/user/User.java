package by.academy.news.entity.user;

import by.academy.news.entity.news.News;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Eugene Petrov
 */
@Entity
@Table(name = "users")
public class User {
    public static final String DEFAULT_ROLE = "USER";

    @Id
    @Column(name = "login")
    @Pattern(regexp = "^[a-zA-Z][\\w]{6,14}", message = "Login must start from letter and contain 6-14 characters")
    private String login;


    @Column(name = "password")
    private String password;

    @Transient
    @Pattern(regexp = "[\\w]{6,14}", message = "Password must contain 6-14 characters")
    private String newPassword;

    @Transient
    private String confirmPassword;

    @Pattern(regexp = "^[a-zA-Z][\\w]{6,14}", message = "Username must start from letter and contain 6-14 characters")
    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role = DEFAULT_ROLE;


    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Invalid email value")
    @Column(name = "email")
    private String email;

    @Transient
    @AssertTrue(message = "Passwords does not match")
    private boolean passwordMatch;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Authorities> authorities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<News> news = new HashSet<>();

    public User() {
    }

    public User(String login, String password, String name, String email, String role, Set<Authorities> authorities, Set<News> news) {
        this.login = login;
        this.password = password;
        this.username = name;
        this.email = email;
        this.role = role;
        this.authorities = authorities;
        this.news = news;
    }

    public User(String login, String username) {
        this.login = login;
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        if (password != null) {
            checkPassword();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPasswordMatch() {
        return passwordMatch;
    }

    public void setPasswordMatch(Boolean passwordMatch) {
        this.passwordMatch = passwordMatch;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        this.password = newPassword;
        if (confirmPassword != null) {
            checkPassword();
        }
    }

    public void setPasswordMatch(boolean passwordMatch) {
        this.passwordMatch = passwordMatch;
    }

    private void checkPassword() {
        this.passwordMatch = newPassword.equals(confirmPassword);
    }
}
