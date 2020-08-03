package com.gatling.demo.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Note {

    @Id
    @NonNull
    private Long id;
    private String header;
    private String body;

}