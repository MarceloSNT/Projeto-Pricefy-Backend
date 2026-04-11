package marcelo.project.pricefy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBPRICE")
public class PriceModel {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrice;

    private Double vlProduct;

    @ManyToOne
    private ProductModel product;

    @ManyToOne
    private MarketModel market;
}
