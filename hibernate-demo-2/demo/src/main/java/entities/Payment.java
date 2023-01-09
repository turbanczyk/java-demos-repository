package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payments")
@NamedQueries({
        @NamedQuery(name = "Payment.findAll", query = "select p from Payment p"),
        @NamedQuery(name = "Payment.findById_CustomerNumber", query = "select p from Payment p where p.id.customerNumber = :customerNumber")
})
public class Payment {
    @EmbeddedId
    private PaymentId id;

    @MapsId("customerNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customerNumber;

    @NotNull
    @Column(name = "paymentDate", nullable = false)
    private LocalDate paymentDate;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

}