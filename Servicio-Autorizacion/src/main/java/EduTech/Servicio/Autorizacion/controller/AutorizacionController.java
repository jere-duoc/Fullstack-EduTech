package EduTech.Servicio.Autorizacion.controller;

import EduTech.Servicio.Autorizacion.entity.Autorizacion;
import EduTech.Servicio.Autorizacion.service.AutorizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autorizacion")
public class AutorizacionController {

    @Autowired
    private AutorizacionService autorizacionService;

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<Autorizacion> iniciarSesion(@RequestBody Autorizacion autorizacion) {
        Autorizacion autorizacionResponse = autorizacionService.iniciarSesion(autorizacion);
        return new ResponseEntity<>(autorizacionResponse, HttpStatus.OK);
    }

    @PostMapping("/registrar-usuario")
    public ResponseEntity<Autorizacion> registrarUsuario(@RequestBody Autorizacion autorizacion) {
        Autorizacion autorizacionResponse = autorizacionService.registrarUsuario(autorizacion);
        return new ResponseEntity<>(autorizacionResponse, HttpStatus.CREATED);
    }
}
