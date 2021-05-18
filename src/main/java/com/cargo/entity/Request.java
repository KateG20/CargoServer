package com.cargo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

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
    Long date;

    @Column
    @NonNull
    Long duration;

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
    String description;

    @Column
    @NonNull
    Integer status;

    @Column
    @JsonIgnore
    Integer userId;

    @Column(name = "rejected", columnDefinition = "integer[]")
    @JsonIgnore
    @ElementCollection(targetClass=Integer.class)
    List<Integer> rejectedBy;
}
