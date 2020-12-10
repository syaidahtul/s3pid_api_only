package com.acc.s3pid.models.core;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import static javax.persistence.TemporalType.TIMESTAMP;
import lombok.AccessLevel;
import lombok.Setter;

@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityHistory<U>
{
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private U createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    private U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date lastModifiedDate;
}
