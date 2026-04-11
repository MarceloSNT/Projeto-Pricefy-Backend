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
@Table(name = "TBMARKET")
public class MarketModel {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarket;

    private String dsName;

    @ManyToOne
    @JsonIgnore
    private UserModel user;
}
