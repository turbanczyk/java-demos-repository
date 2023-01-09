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
@Table(name = "employees", indexes = {
        @Index(name = "officeCode", columnList = "officeCode"),
        @Index(name = "reportsTo", columnList = "reportsTo")
})
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e"),
        @NamedQuery(name = "Employee.findByLastNameAndFirstName", query = "select e from Employee e where e.lastName = :lastName and e.firstName = :firstName")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employeeNumber", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Size(max = 50)
    @NotNull
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Size(max = 10)
    @NotNull
    @Column(name = "extension", nullable = false, length = 10)
    private String extension;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "officeCode", nullable = false)
    private Office officeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportsTo")
    private Employee reportsTo;

    @Size(max = 50)
    @NotNull
    @Column(name = "jobTitle", nullable = false, length = 50)
    private String jobTitle;

}