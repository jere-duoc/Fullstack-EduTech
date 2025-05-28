package EduTech.Servicio.Autorizacion.client;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import EduTech.Servicio.Autorizacion.dto.UsuarioValidationRequestDTO;
import EduTech.Servicio.Autorizacion.dto.UsuarioValidationResponseDTO;

@Component
public class UsuarioServiceMockClient implements UsuarioServiceClient {

    @Override
    public Optional<UsuarioValidationResponseDTO> validarCredenciales(UsuarioValidationRequestDTO requestDTO) {
        if ("1-9".equals(requestDTO.getRun())&& "password".equals(requestDTO.getPassword())) {
            List<String> roles = Arrays.asList("ROLE_USER", "ROLE_STUDENT");
            UsuarioValidationResponseDTO response = new UsuarioValidationResponseDTO(
                1,
                requestDTO.getRun(),
                "Usuario Simulado",
                30,
                roles
            );
            return Optional.of(response);
        } else if ("admin".equals(requestDTO.getRun())&& "adminpass".equals(requestDTO.getPassword())) {
            List<String> roles = Arrays.asList("ROLE_USER","ROLE_STUDENT");
            UsuarioValidationResponseDTO response = new UsuarioValidationResponseDTO(
                2,
                requestDTO.getRun(),
                "Admin simulado",
                40,
                roles
            );
            return Optional.of(response);
        }
        return Optional.empty();
    }

}
