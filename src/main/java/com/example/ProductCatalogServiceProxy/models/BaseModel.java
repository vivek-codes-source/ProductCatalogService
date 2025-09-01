package com.example.ProductCatalogServiceProxy.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
@MappedSuperclass
public  abstract class BaseModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedAt;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date LastModifiedAt;
    private Status status;

}
