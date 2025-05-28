package EduTech.Servicio.Autorizacion.security;

import EduTech.Servicio.Autorizacion.dto.UsuarioValidationResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    
    private JwtUtil jwtUtil;

    
    private String testSecret = "MiClaveSecretaSuperSeguraParaPruebasDebeSerLargaYUnica123456";
    private long testExpirationMs = 3600000; 

    
    @BeforeEach
    void setUp() {
       
        jwtUtil = new JwtUtil();

        
        ReflectionTestUtils.setField(jwtUtil, "secretString", testSecret);
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationMs", testExpirationMs);
    }

    
    @Test
    void testGenerarTokenYExtraerUsername() {
        
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_STUDENT");
        UsuarioValidationResponseDTO userDetails = new UsuarioValidationResponseDTO(
                1,                
                "testRun123",     
                "Usuario Prueba", 
                25,               
                roles             
        );

       
        String token = jwtUtil.generateToken(userDetails);
        String nombreUsuarioExtraido = jwtUtil.extractUsername(token);

       
        assertNotNull(token, "El token generado no debería ser nulo.");
        assertFalse(token.isEmpty(), "El token generado no debería estar vacío.");
        assertEquals(userDetails.getRun(), nombreUsuarioExtraido, "El RUN extraído del token debe coincidir con el RUN original del usuario.");
    }

    
    @Test
    void testExtraerRolesDelToken() {
     
        List<String> rolesEsperados = Arrays.asList("ROLE_ADMIN", "ROLE_EDITOR");
        UsuarioValidationResponseDTO userDetailsAdmin = new UsuarioValidationResponseDTO(
                2, "adminRun", "Admin User", 30, rolesEsperados
        );

        
        String token = jwtUtil.generateToken(userDetailsAdmin);
        List<String> rolesExtraidos = jwtUtil.extractRoles(token);

       
        assertNotNull(rolesExtraidos, "La lista de roles extraídos no debería ser nula.");
        assertEquals(rolesEsperados.size(), rolesExtraidos.size(), "La cantidad de roles extraídos debe coincidir con la cantidad de roles esperados.");
       
        assertTrue(rolesExtraidos.containsAll(rolesEsperados), "Los roles extraídos deben contener todos los roles esperados.");
      
        assertTrue(rolesEsperados.containsAll(rolesExtraidos), "Los roles esperados deben contener todos los roles extraídos (para asegurar que no hay roles extra).");
    }

    
    @Test
    void testTokenExpirado() throws InterruptedException {
        
        List<String> roles = Arrays.asList("ROLE_USER");
        UsuarioValidationResponseDTO userDetails = new UsuarioValidationResponseDTO(
                3, "userExp", "Usuario a Expirar", 28, roles
        );

        
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationMs", 1L); 

        String token = jwtUtil.generateToken(userDetails);

        
        Thread.sleep(50); 

       
        ExpiredJwtException exception = assertThrows(ExpiredJwtException.class, () -> {
            jwtUtil.extractUsername(token); 
        }, "Se esperaba una ExpiredJwtException para un token expirado.");

       
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationMs", testExpirationMs);
    }
 }