package com.example.notes.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class MangaCategory implements Serializable {
    @Id
    @GeneratedValue
    private Long categoryId;

    private String categoryName;

    @NaturalId
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public MangaCategory() {
    }

    public MangaCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
