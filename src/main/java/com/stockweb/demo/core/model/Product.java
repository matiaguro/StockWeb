package com.stockweb.demo.core.model;


import com.stockweb.demo.core.model.audit.Audit;
import com.stockweb.demo.core.model.audit.AuditListener;
import com.stockweb.demo.core.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE product SET is_active=false WHERE product_id=?")
@EntityListeners(AuditListener.class)
public class Product implements Auditable {

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

    @Embedded
    private Audit audit;

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
