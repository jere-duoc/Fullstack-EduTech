package EduTech.Servicio.Pagos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.Servicio.Pagos.model.Pago;
import EduTech.Servicio.Pagos.service.PagoService;

@RestController
@RequestMapping("/api/v1/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>> listar(){
        List<Pago> pagos = pagoService.findAll();
        if (pagos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }
}
