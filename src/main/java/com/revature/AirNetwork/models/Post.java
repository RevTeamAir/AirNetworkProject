package com.revature.AirNetwork.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity
@Table(name = "network_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description; //content of the post

    @Column(columnDefinition = "integer default 0")
    private Integer likeCount;

    @CreationTimestamp
    @Column(updatable = false)
    private Date creationDate;

    @Column
    private String postImageLocation; // store the location of the picture in our s3 bucket

    public Post(String description) {
        this.description = description;
    }

    //MULTIPLICITY RELATIONSHIPS
    @ManyToOne
    @JsonIgnoreProperties({"posts"})
    private User authorIdFK;

    @OneToMany(mappedBy = "postFk", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
}
