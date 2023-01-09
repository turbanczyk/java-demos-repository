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
@Table(name = "customers", indexes = {
        @Index(name = "salesRepEmployeeNumber", columnList = "salesRepEmployeeNumber")
})
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c"),
        @NamedQuery(name = "Customer.findByCustomerName", query = "select c from Customer c where c.customerName = :customerName")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerNumber", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;

    @Size(max = 50)
    @NotNull
    @Column(name = "contactLastName", nullable = false, length = 50)
    private String contactLastName;

    @Size(max = 50)
    @NotNull
    @Column(name = "contactFirstName", nullable = false, length = 50)
    private String contactFirstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Column(name = "addressLine1", nullable = false, length = 50)
    private String addressLine1;

    @Size(max = 50)
    @Column(name = "addressLine2", length = 50)
    private String addressLine2;

    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "state", length = 50)
    private String state;

    @Size(max = 15)
    @Column(name = "postalCode", length = 15)
    private String postalCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesRepEmployeeNumber")
    private Employee salesRepEmployeeNumber;

    @Column(name = "creditLimit", precision = 10, scale = 2)
    private BigDecimal creditLimit;

}