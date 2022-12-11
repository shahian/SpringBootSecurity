package com.shahian.springBootSecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appUser")
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "userName")
    private String userName;
    // use   @JsonIgnore  to won't part of api
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @ManyToMany(
            //cascade = CascadeType.ALL,
            /*
            When you do a CascadeType.ALL on the child entity,
            it will cause every DB operation to be propagated to the entity role.
            Since Transaction is PERSISTED,
            it tries to PERSIST role as well and that doesn't work since role already is in the db.
            To fix this use CascadeType.MERGE instead as with it the role will be automatically merged instead.
             */
            cascade = CascadeType.MERGE,
            /*
            Lazy and Eager are two types of data loading strategies in ORMs
            Lazy Loading − Associated data loads only when we explicitly call getter or size method.
            Egare Loading − Data loading happens at the time of their parent is fetched.
            here we should use eager to load Role items to avoid  error for loading
             */
            //fetch = FetchType.LAZY
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "appUser_roles",
            joinColumns = @JoinColumn(name = "appUser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

}
