package marcelo.project.pricefy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBPRODUCT")
public class ProductModel {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    private String dsName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceModel> prices;

    @ManyToOne
    @JsonIgnore
    private UserModel user;
}
