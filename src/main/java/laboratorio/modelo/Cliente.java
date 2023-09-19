package laboratorio.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario implements Serializable {

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String obra;

}
