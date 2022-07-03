package testing.satrio.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import testing.satrio.backend.model.Cif;
import testing.satrio.backend.repository.CifRepository;
import testing.satrio.backend.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class CifController {
    @Autowired
    private CifRepository cifRepository;

    //get cif
    @GetMapping("cif")
    public List<Cif> getAllCif(){
        return this.cifRepository.findAll();
    }

    //get cif by id
    @GetMapping("cif/{id}")
    public ResponseEntity<Cif> getCifById(@PathVariable(value="id") Long cifId)
        throws ResourceNotFoundException {
        Cif cif = cifRepository.findById(cifId)
                .orElseThrow(() -> new ResourceNotFoundException("Cif not found for this id : " + cifId));
                return ResponseEntity.ok().body(cif);
    }

    //save cif
    @PostMapping("cif")
    public Cif createCif(@RequestBody Cif cif) {
        return this.cifRepository.save(cif);
    }

    //update cif
    @PutMapping("cif/{id}")
    public ResponseEntity<Cif> updateCif(@PathVariable(value = "id") Long cifId,
                                         @RequestBody Cif cifDetails) throws ResourceNotFoundException {
        Cif cif = cifRepository.findById(cifId)
                .orElseThrow(() -> new ResourceNotFoundException("Cif not found for this id : " + cifId));

        cif.setName(cifDetails.getName());
        cif.setAlamat(cifDetails.getAlamat());
        cif.setNo_ktp(cifDetails.getNo_ktp());
        cif.setJenis_kelamin(cifDetails.getJenis_kelamin());

        return ResponseEntity.ok(this.cifRepository.save(cif));
    }

    //delete cif
    @DeleteMapping("cif/{id}")
    public Map<String, Boolean> deleteCif(@PathVariable(value = "id") Long cifId) throws ResourceNotFoundException {
        Cif cif = cifRepository.findById(cifId)
                .orElseThrow(() -> new ResourceNotFoundException("Cif not found for this id : " + cifId));

        this.cifRepository.delete(cif);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
