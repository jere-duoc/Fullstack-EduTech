package EduTech.Servicio.Autorizacion.security; 

import EduTech.Servicio.Autorizacion.client.UsuarioServiceClient;
import EduTech.Servicio.Autorizacion.dto.UsuarioValidationRequestDTO;
import EduTech.Servicio.Autorizacion.dto.UsuarioValidationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections; 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioServiceClient usuarioServiceClient;

    @Autowired
    public CustomAuthenticationProvider(UsuarioServiceClient usuarioServiceClient) {
        this.usuarioServiceClient = usuarioServiceClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String run = authentication.getName(); 
        String password = authentication.getCredentials().toString();

        UsuarioValidationRequestDTO validationRequest = new UsuarioValidationRequestDTO(run, password);

        Optional<UsuarioValidationResponseDTO> validationResponseOpt =
                usuarioServiceClient.validarCredenciales(validationRequest);

        if (validationResponseOpt.isPresent()) {
            UsuarioValidationResponseDTO userDetails = validationResponseOpt.get();

            List<GrantedAuthority> authorities;
            if (userDetails.getRoles() != null && !userDetails.getRoles().isEmpty()) {
                authorities = userDetails.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            } else {
                authorities = Collections.emptyList();             }

            
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails.getRun(), null, authorities);
            authToken.setDetails(userDetails); 
            return authToken;
        } else {
            throw new BadCredentialsException("Credenciales inválidas proporcionadas por el cliente de validación.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}