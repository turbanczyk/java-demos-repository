package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "orderdetails", indexes = {
        @Index(name = "productCode", columnList = "productCode")
})
@NamedQueries({
        @NamedQuery(name = "Orderdetail.findAll", query = "select o from Orderdetail o"),
        @NamedQuery(name = "Orderdetail.findById_OrderNumber", query = "select o from Orderdetail o where o.id.orderNumber = :orderNumber")
})
public class Orderdetail {
    @EmbeddedId
    private OrderdetailId id;

    @MapsId("orderNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderNumber", nullable = false)
    private Order orderNumber;

    @MapsId("productCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productCode", nullable = false)
    private Product productCode;

    @NotNull
    @Column(name = "quantityOrdered", nullable = false)
    private Integer quantityOrdered;

    @NotNull
    @Column(name = "priceEach", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceEach;

    @NotNull
    @Column(name = "orderLineNumber", nullable = false)
    private Short orderLineNumber;

}