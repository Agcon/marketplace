package ru.agcon.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agcon.marketplace.models.WashingMachines;
import ru.agcon.marketplace.repository.WashingMachinesRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wm")
public class WashingMachinesController {
    @Autowired
    private WashingMachinesRepository washingMachinesRepository;

    @GetMapping("")
    public ResponseEntity<List<WashingMachines>> getBooks() {
        List<WashingMachines> washingMachines = washingMachinesRepository.findAll();
        return ResponseEntity.ok(washingMachines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WashingMachines> getWashingMachineById(@PathVariable(value = "id") int washingMachineId) {
        Optional<WashingMachines> washingMachine = washingMachinesRepository.findById(washingMachineId);
        if (washingMachine.isPresent()) {
            return ResponseEntity.ok().body(washingMachine.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public WashingMachines createWashingMachine(@RequestBody WashingMachines washingMachine) {
        washingMachine.setTypeOfProduct("washingMachine");
        return washingMachinesRepository.save(washingMachine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WashingMachines> updateWashingMachine(@PathVariable(value = "id") int washingMachineId,
                                              @RequestBody WashingMachines washingMachineDetails) {
        Optional<WashingMachines> optionalWashingMachine = washingMachinesRepository.findById(washingMachineId);
        if (optionalWashingMachine.isPresent()) {
            WashingMachines washingMachine = optionalWashingMachine.get();
            washingMachine.setProducer(washingMachineDetails.getProducer());
            washingMachine.setNumberOfSeller(washingMachineDetails.getNumberOfSeller());
            washingMachine.setTankCapacity(washingMachineDetails.getTankCapacity());
            washingMachine.setTypeOfProduct(washingMachineDetails.getTypeOfProduct());
            washingMachine.setPrice(washingMachineDetails.getPrice());
            washingMachine.setName(washingMachineDetails.getName());
            return ResponseEntity.ok(washingMachinesRepository.save(washingMachine));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWashingMachine(@PathVariable(value = "id") int washingMachineId) {
        Optional<WashingMachines> optionalWashingMachine = washingMachinesRepository.findById(washingMachineId);
        if (optionalWashingMachine.isPresent()) {
            washingMachinesRepository.delete(optionalWashingMachine.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
