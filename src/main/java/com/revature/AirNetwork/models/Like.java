package com.revature.AirNetwork.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "network_likes" , uniqueConstraints = @UniqueConstraint(columnNames=("authorFk")))
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer id;


    @ManyToOne
    @JsonIgnoreProperties({"likes", "posts"})
    @JoinColumn(name="authorFk")
    private User authorFk;

    //one like per post per user
    @ManyToOne
    @JsonIgnoreProperties({"likes"})
    private Post postFk;
}
