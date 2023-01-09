package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders", indexes = {
        @Index(name = "customerNumber", columnList = "customerNumber")
})
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "select o from Order o"),
        @NamedQuery(name = "Order.findById", query = "select o from Order o where o.id = :id")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderNumber", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;

    @NotNull
    @Column(name = "requiredDate", nullable = false)
    private LocalDate requiredDate;

    @Column(name = "shippedDate")
    private LocalDate shippedDate;

    @Size(max = 15)
    @NotNull
    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Lob
    @Column(name = "comments")
    private String comments;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customerNumber;

}