package marcelo.project.pricefy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBPRODUCT")
public class ProductModel {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    private String dsName;

    @ManyToOne
    private UserModel user;
}
