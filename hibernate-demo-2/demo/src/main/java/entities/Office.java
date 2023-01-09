package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offices")
@NamedQueries({
        @NamedQuery(name = "Office.findAll", query = "select o from Office o"),
        @NamedQuery(name = "Office.findByCity", query = "select o from Office o where o.city = :city")
})
public class Office {
    @Id
    @Size(max = 10)
    @Column(name = "officeCode", nullable = false, length = 10)
    private String id;

    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

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
    @Column(name = "state", length = 50)
    private String state;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Size(max = 15)
    @NotNull
    @Column(name = "postalCode", nullable = false, length = 15)
    private String postalCode;

    @Size(max = 10)
    @NotNull
    @Column(name = "territory", nullable = false, length = 10)
    private String territory;

}