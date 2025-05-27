package EduTech.Servicio.Pagos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduTech.Servicio.Pagos.model.Pago;
import EduTech.Servicio.Pagos.repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> findAll(){
        return pagoRepository.findAll();
    }

    public Pago findById(Long id) {
        return pagoRepository.findById(id).get();
    }

    public Pago save(Pago pago){
        return pagoRepository.save(pago);
    }

    public void delete(long id){
        pagoRepository.deleteById(id);
    }
    
}
