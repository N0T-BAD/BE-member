package com.blockpage.memberservice.adaptor.infrastructure.mysql.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name = "registerTime", nullable = false, updatable = false)
    private LocalDateTime registerTime;

    @LastModifiedDate
    @Column(name = "updateTime", nullable = false)
    private LocalDateTime updateTime;

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

}
