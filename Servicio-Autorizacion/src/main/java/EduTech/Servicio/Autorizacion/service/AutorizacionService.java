package EduTech.Servicio.Autorizacion.service;

import EduTech.Servicio.Autorizacion.dto.LoginRequestDTO;
import EduTech.Servicio.Autorizacion.dto.UsuarioValidationResponseDTO; 
import EduTech.Servicio.Autorizacion.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AutorizacionService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AutorizacionService(AuthenticationManager authenticationManager,
                               JwtUtil jwtUtil
                               ) { 
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        
    }

    public String login(LoginRequestDTO loginRequest) {
       
        Authentication authenticationRequest =
                new UsernamePasswordAuthenticationToken(loginRequest.getRun(), loginRequest.getPassword());

       
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

      
        if (!(authenticationResponse.getDetails() instanceof UsuarioValidationResponseDTO)) {
           
            throw new IllegalStateException("Los detalles de la autenticación no son del tipo esperado (UsuarioValidationResponseDTO)");
        }
        UsuarioValidationResponseDTO userDetails = (UsuarioValidationResponseDTO) authenticationResponse.getDetails();

        
        return jwtUtil.generateToken(userDetails);
    }

}