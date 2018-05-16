package org.todeschini.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Artur on 14/05/18.
 *
 * * id (int)
 * username (String)
 * password (String)
 * is_enabled (boolean)
 * register_date (Date)
 * name (String)
 * surname (String)
 * email (String)
 * phone (String)
 */
@Entity
public class Users {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    private String username;
    private String password;
    private Boolean enabled;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registerDate;

    @Transient
    private String formatedDate;

    @NotEmpty
    private String name;
    private String surname;

    @NotEmpty
    @Email
    private String email;

    private String phone;

    public Users(String username, String password, Boolean enabled, Date registerDate, String name, String surname, String email, String phone) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.registerDate = registerDate;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Users() {
        registerDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFormatedDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date createDdate = this.registerDate;
        if( createDdate!=null){
            return  dateFormat.format(createDdate);
        }
        return "";
    }

    public void setFormatedDate(String formatedDate) {
        this.formatedDate = formatedDate;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.registerDate  = dateFormat.parse(formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            this.registerDate = new Date();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (username != null ? !username.equals(users.username) : users.username != null) return false;
        if (name != null ? !name.equals(users.name) : users.name != null) return false;
        return email != null ? email.equals(users.email) : users.email == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
