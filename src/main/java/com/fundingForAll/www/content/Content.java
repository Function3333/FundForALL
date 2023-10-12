package com.fundingForAll.www.content;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Content {

    @Id
    @Column(name = "CONTENT_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
