package marcelo.project.pricefy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TBUSER")
public class UserModel {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String dsUsername;

    private String dsEmail;

    private String dsPassword;

}
