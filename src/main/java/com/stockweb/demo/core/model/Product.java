package com.stockweb.demo.core.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table (name = "product")
//@Where(clause = "is_active=true")
//@SQLDelete(sql = "UPDATE product SET is_active=false WHERE product_id=?")
//@EntityListeners(AuditListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private Long amount;

    @Column
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
