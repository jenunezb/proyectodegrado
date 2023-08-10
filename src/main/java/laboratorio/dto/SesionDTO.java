package laboratorio.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class SesionDTO {
    private String email;
    private String password;
}
