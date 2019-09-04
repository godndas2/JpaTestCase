package com.boot.jpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "uno")
    private Long userNo;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private USER_TYPE type;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;

    public enum USER_TYPE {USER, ADMIN};

}
