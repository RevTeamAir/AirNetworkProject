package com.revature.AirNetwork.models;



import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity //defines we want this class persisted to the database
@Table(name = "network_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    // @ColumnTransformer(read = "pgp_sym_decrypt(password, ‘mySecretKey’)", write = "pgp_sym_encrypt(?, ‘mySecretKey’)")
    // need to figure out how to define the secret key

    //@ColumnTransformer(read = "pgp_sym_decrypt(password, ${SECRET_KEY})", write = "pgp_sym_encrypt(?, $SECRET_KEY)") // <--- secret key would be in env variable??
    //@ColumnTransformer(read = "pgp_sym_decrypt(password, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String profilePictureLocation; // store the location of the picture in our s3 bucket
    // "https://ons3bucket.s3.amazonaws.com/profilePics/coolimage.jpeg"

    @CreationTimestamp
    @Column(updatable = false) // NOTE: need this constraint so that when you update a user the "creationDate" field doesn't become null
    private Date creationDate;

    //posts foreign key (one user to many posts)
    @OneToMany(mappedBy = "authorIdFK", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    private String bio;

    // need to add a constructor to validate credentials
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //need to add a constructor with all the minimum fields required to create a new user (all the nullable=false ones)
    public User(String username, String password, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}