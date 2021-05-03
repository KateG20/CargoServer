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
    int price;

    @Column
    @NonNull
    String shipper;

    @Column
    @NonNull
    String receiver;

    @Column
    int date;

    @Column
    int duration;

    @Column
    int distance;

    @Column
    @NonNull
    String source; // todo

    @Column
    @NonNull
    String destination;

    @Column
    int weight;

    @Column
    @NonNull
    String description;

    @Column
    int status;
    // 0 - new
    // 1 - current
    // 2 - archive
}
