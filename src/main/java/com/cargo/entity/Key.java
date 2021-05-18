package com.cargo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "key")
@NoArgsConstructor
@AllArgsConstructor
public class Key {
    @Column(unique = true)
    @Id
    @NonNull
    String value;

    @Column
    @NonNull
    String name;

    @Column(name = "license_plate")
    @NonNull
    String licensePlate;

    @Column
    @NonNull
    String company;
}
