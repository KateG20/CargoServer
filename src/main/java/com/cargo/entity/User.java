package com.cargo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "userTable")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(unique = true)
    @NonNull
    String login;

    @Column
    @NonNull
    String password;

    @Column
    @NonNull
    String name;

    @Column
    @NonNull
    String licensePlate;

    @Column
    @NonNull
    String company;

    //    @Column
    @OneToOne(targetEntity = Request.class, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "request", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "request_id")
//    )
//    @JoinColumn
//    @NonNull
            @JsonIgnore
    List<Request> requests;

    //    @Column
//    @NonNull
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "key", referencedColumnName = "value")
//    @JsonIgnore
//    Key key;

//    @Column
//    @NonNull
//    Boolean verified = false;
}
