package org.todeschini.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    private String username;
    private String password;
    private Boolean enabled;
    private Date registerDate;

    @NotEmpty
    private String name;
    private String surname;

    @NotEmpty
    @Email
    private String email;

    private String phone;
}
