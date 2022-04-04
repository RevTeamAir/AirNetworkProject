package com.revature.AirNetwork.models;

import lombok.*;
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
@Table(name = "users")
public class User {
    @Id  //defines primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrements
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = true)
    private String profilePictureLocation; // store the location of the picture in our s3 bucket

    @CreationTimestamp
    @Column(updatable = false) // NOTE: need this constraint so that when you update a user the "creationDate" field doesn't become null
    private Date creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

}
