package entities;

import jakarta.persistence.*;
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
@Table(name = "productlines")
@NamedQueries({
        @NamedQuery(name = "Productline.findAll", query = "select p from Productline p"),
        @NamedQuery(name = "Productline.findById", query = "select p from Productline p where p.id = :id")
})
public class Productline {
    @Id
    @Size(max = 50)
    @Column(name = "productLine", nullable = false, length = 50)
    private String id;

    @Size(max = 4000)
    @Column(name = "textDescription", length = 4000)
    private String textDescription;

    @Lob
    @Column(name = "htmlDescription")
    private String htmlDescription;

    @Column(name = "image")
    private byte[] image;

}