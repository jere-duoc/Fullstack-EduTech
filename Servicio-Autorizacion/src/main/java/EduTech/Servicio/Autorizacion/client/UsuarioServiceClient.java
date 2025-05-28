package EduTech.Servicio.Autorizacion.client;

import EduTech.Servicio.Autorizacion.dto.UsuarioValidationRequestDTO;
import EduTech.Servicio.Autorizacion.dto.UsuarioValidationResponseDTO;

import java.util.Optional;

public interface UsuarioServiceClient {
    Optional<UsuarioValidationResponseDTO> validarCredenciales(UsuarioValidationRequestDTO requestDTO);
}
