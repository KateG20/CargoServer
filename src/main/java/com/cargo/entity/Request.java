package com.cargo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "request")
public class Request {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    @NonNull
    Integer price;

    @Column
    @NonNull
    String shipper;

    @Column
    @NonNull
    String receiver;

    @Column
    @NonNull
    Integer date;

    @Column
    @NonNull
    Integer duration;

    @Column
    @NonNull
    Integer distance;

    @Column
    @NonNull
    String source;

    @Column
    @NonNull
    String destination;

    @Column
    @NonNull
    Integer weight;

    @Column
    @NonNull
    String description;

    @Column
    @NonNull
    Integer status;
    // 0 - new
    // 1 - current
    // 2 - archive

    @Column
    @NonNull
    final Boolean taken = false;
}
