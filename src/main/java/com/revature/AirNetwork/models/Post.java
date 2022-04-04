package com.revature.AirNetwork.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Post {
    @Id
    private int pid;
    private String description;


}
