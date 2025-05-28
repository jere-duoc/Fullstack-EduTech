package EduTech.Servicio.Autorizacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioValidationResponseDTO {
    private Integer id;
    private String run;
    private String nombre;
    private Integer edad;
    private List<String> roles;
}
