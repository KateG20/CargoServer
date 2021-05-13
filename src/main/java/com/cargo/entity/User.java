package com.cargo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {
    @Column(unique = true)
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
//    @NonNull
    String name;

    @Column
//    @NonNull
    String licensePlate;

    @Column
//    @NonNull
    String company;

        @Column
//    @OneToOne(targetEntity = Request.class, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "request", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "request_id")
//    )
//    @JoinColumn
//    @NonNull
    @JsonIgnore
        @ElementCollection(targetClass=Long.class)
    List<Long> requests; // тут только заявки со статусами 1 и 2

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

//    @OneToOne(targetEntity = Request.class, cascade = CascadeType.ALL)
//    @JsonIgnore
//    List<Request> rejectedRequests; // тут с любыми статусами, изначально с 0

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
