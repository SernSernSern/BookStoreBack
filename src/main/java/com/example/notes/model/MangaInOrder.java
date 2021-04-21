package com.example.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class MangaInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderMain orderMain;

    @NotEmpty
    private String productId;

    @NotEmpty
    private String productName;

    @NotNull
    private String productDescription;

    private String productIcon;

    @NotNull
    private Integer categoryType;

    @NotNull
    private BigDecimal productPrice;

    @Min(0)
    private Integer productStock;

    @Min(1)
    private Integer count;

    public MangaInOrder(MangaInfo mangaInfo, Integer quantity){
        this.productId = mangaInfo.getProductId();
        this.productName = mangaInfo.getProductName();
        this.productDescription = mangaInfo.getProductDescription();
        this.productIcon = mangaInfo.getProductIcon();
        this.categoryType = mangaInfo.getCategoryType();
        this.productPrice = mangaInfo.getProductPrice();
        this.productStock = mangaInfo.getProductStock();
        this.count = quantity;
    }

    @Override
    public String toString() {
        return "MangaInOrder{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", categoryType=" + categoryType +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(!super.equals(o))return false;
        MangaInOrder that = (MangaInOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(productIcon, that.productIcon) &&
                Objects.equals(categoryType, that.categoryType) &&
                Objects.equals(productPrice, that.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, productName, productDescription, productIcon, categoryType, productPrice);
    }
}
