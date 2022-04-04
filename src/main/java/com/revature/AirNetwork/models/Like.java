package com.revature.AirNetwork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "network_likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer id;

    //one author to one like
    @OneToOne
    private User authorFk;

    //many likes to one post
    @ManyToOne
    private Post postFk;


}
