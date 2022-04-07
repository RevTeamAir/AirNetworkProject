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
@Table(name = "network_likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer id;

    //One author to one like
    @ManyToOne
    @JsonIgnoreProperties({"posts"})
    private User authorFk;

    //one like per post per user
    @ManyToOne
    private Post postFk;
}
