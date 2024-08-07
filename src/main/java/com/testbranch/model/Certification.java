package com.testbranch.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "certification")
public class Certification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "desgin")
    private String design;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "expiarydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiaryDate;

    @OneToMany(mappedBy="certification",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Group> groups;

}
