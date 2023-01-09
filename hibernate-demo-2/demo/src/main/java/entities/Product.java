package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products", indexes = {
        @Index(name = "productLine", columnList = "productLine")
})
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select p from Product p"),
        @NamedQuery(name = "Product.findById", query = "select p from Product p where p.id = :id")
})
public class Product {
    @Id
    @Size(max = 15)
    @Column(name = "productCode", nullable = false, length = 15)
    private String id;

    @Size(max = 70)
    @NotNull
    @Column(name = "productName", nullable = false, length = 70)
    private String productName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productLine", nullable = false)
    private Productline productLine;

    @Size(max = 10)
    @NotNull
    @Column(name = "productScale", nullable = false, length = 10)
    private String productScale;

    @Size(max = 50)
    @NotNull
    @Column(name = "productVendor", nullable = false, length = 50)
    private String productVendor;

    @NotNull
    @Lob
    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @NotNull
    @Column(name = "quantityInStock", nullable = false)
    private Short quantityInStock;

    @NotNull
    @Column(name = "buyPrice", nullable = false, precision = 10, scale = 2)
    private BigDecimal buyPrice;

    @NotNull
    @Column(name = "MSRP", nullable = false, precision = 10, scale = 2)
    private BigDecimal msrp;

}