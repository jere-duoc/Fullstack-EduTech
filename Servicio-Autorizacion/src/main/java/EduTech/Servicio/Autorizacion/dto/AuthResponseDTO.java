package EduTech.Servicio.Autorizacion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String tokenType = "Bearer";

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public AuthResponseDTO(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }
}

