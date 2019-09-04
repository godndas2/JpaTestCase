package com.boot.jpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userinfo")
@Data
public class UserInfo {

    @Id
    private Long userNo;

    @Column(name = "EMAIL")
    private String email;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UNO")
    private User user;
}
