package com.cargo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "key")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Key {
    private String value;

    public String getValue() {
        return value;
    }
}
