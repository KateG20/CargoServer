package com.cargo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
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

    @Column
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "request", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "request_id")
//    )
    @NonNull
    List<Request> requests;

    @Column
    @NonNull
    Key key;

    @Column
    @NonNull
    Boolean verified = false;
}
