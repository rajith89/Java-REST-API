package com.testbranch.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "certificationgroups")
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "group_attributes", joinColumns = @JoinColumn(name = "groupid"))
    @MapKeyColumn(name = "attributekey")
    @Column(name = "attributevalue")
    private Map<String, String> attributes;

    @OneToMany(mappedBy="group",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Member> members;

    @ManyToOne
    @JoinColumn(name="certificationid", nullable=false)
    private Certification certification;
}
