package com.example.olympictrials.controller;

import com.example.olympictrials.AthleteEntity;
import com.example.olympictrials.exception.ResourceNotFoundException;
import com.example.olympictrials.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1") //why do we need this.
public class AthleteController {
    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping("/athletes")
    public List<AthleteEntity> getAllAthletes() {
        return athleteRepository.findAll();
    }

    @GetMapping("/athletes/{id}")
    public ResponseEntity<AthleteEntity> getAthleteById(@PathVariable(value="id") Long athleteId) throws ResourceNotFoundException { // why are we setting this Long and not long.
        AthleteEntity athleteEntity = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new ResourceNotFoundException("Athlete with id " + athleteId + "is not found."));

        return ResponseEntity.ok().body(athleteEntity);
    }

    @PostMapping("/athletes")
    public AthleteEntity createAthlete(@Valid @RequestBody AthleteEntity athleteEntity) {
        return athleteRepository.save(athleteEntity);
    }

    @PutMapping("/athletes/{id}")
    public ResponseEntity<AthleteEntity> updateAthlete(@PathVariable(value="id") Long athleteId,
            @Valid @RequestBody AthleteEntity athleteDetails) throws ResourceNotFoundException {
                AthleteEntity athleteEntity = athleteRepository.findById(athleteId)
                        .orElseThrow(() -> new ResourceNotFoundException("Athlete with id " + athleteId + "is not found."));

                athleteEntity.setFirstName(athleteDetails.getFirstName());
                athleteDetails.setLastName(athleteDetails.getLastName());
                athleteDetails.setSport(athleteDetails.getSport());

                final AthleteEntity updatedAthlete = athleteRepository.save(athleteEntity);

                return ResponseEntity.ok(updatedAthlete);

    }

    @DeleteMapping("/athletes/{id}")
    public Map<String, Boolean> deleteAthlete(@PathVariable(value="id") Long athleteId) throws ResourceNotFoundException {
        AthleteEntity athleteEntity = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new ResourceNotFoundException("Athlete with id " + athleteId + "is not found."));

        athleteRepository.delete(athleteEntity);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
