package ru.agcon.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agcon.marketplace.models.Phones;
import ru.agcon.marketplace.repository.PhonesRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/phones")
public class PhonesController {
    @Autowired
    private PhonesRepository phonesRepository;

    @GetMapping("")
    public ResponseEntity<List<Phones>> getAllBooks() {
        List<Phones> phones = phonesRepository.findAll();
        return ResponseEntity.ok(phones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phones> getPhoneById(@PathVariable(value = "id") int phoneId) {
        Optional<Phones> phone = phonesRepository.findById(phoneId);
        if (phone.isPresent()) {
            return ResponseEntity.ok().body(phone.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public Phones createPhone(@RequestBody Phones phone) {
        phone.setTypeOfProduct("Phone");
        return phonesRepository.save(phone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Phones> updatePhone(@PathVariable(value = "id") int phoneId,
                                            @RequestBody Phones phoneDetails) {
        Optional<Phones> optionalPhone = phonesRepository.findById(phoneId);
        if (optionalPhone.isPresent()) {
            Phones phone = optionalPhone.get();
            phone.setProducer(phoneDetails.getProducer());
            phone.setNumberOfSeller(phoneDetails.getNumberOfSeller());
            phone.setBatteryCapacity(phoneDetails.getBatteryCapacity());
            phone.setTypeOfProduct("Phone");
            phone.setPrice(phoneDetails.getPrice());
            phone.setName(phoneDetails.getName());
            return ResponseEntity.ok(phonesRepository.save(phone));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable(value = "id") int phoneId) {
        Optional<Phones> optionalPhone = phonesRepository.findById(phoneId);
        if (optionalPhone.isPresent()) {
            phonesRepository.delete(optionalPhone.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
