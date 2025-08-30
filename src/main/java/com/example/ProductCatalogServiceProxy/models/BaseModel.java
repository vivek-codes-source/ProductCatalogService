package com.example.ProductCatalogServiceProxy.models;

import java.util.Date;

public  abstract class BaseModel {
    private long id;
    private Date CreatedAt;
    private Date LastModifiedAt;
    private Status status;

}
