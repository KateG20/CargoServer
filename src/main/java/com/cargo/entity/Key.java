package com.cargo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "key")
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Key {
//    @Column
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Integer id;

    @Column(unique = true)
    @Id
    @NonNull
    String value;

//    @OneToOne(mappedBy = "key")
//    User user;

    @Column
    @NonNull
    String name;

    @Column(name = "license_plate")
    @NonNull
//    @JsonProperty("license_plate")
    String licensePlate;

    @Column
    @NonNull
    String company;
}
