package com.imooc.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
// 自动更新时间
@DynamicUpdate
// 自动设置时间注解前提
@EntityListeners(AuditingEntityListener.class)
public class UserToken {

    @Id
    private String tokenId;

    private String userId;

    private String token;

    @CreatedDate
    private Date createTime;
}
