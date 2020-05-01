package com.imooc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserInfo {

    @Id
    private String userId;

    private String userName;

    private String password;
}
