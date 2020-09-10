package com.cde.power_supply_incident_registration.controller;

import com.cde.power_supply_incident_registration.entity.Incident;
import com.cde.power_supply_incident_registration.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping()
    public ResponseEntity<Incident> saveIncidentDetail(@Validated @RequestBody Incident incident) {
        Incident incidentDetail = incidentService.saveIncidentDetail(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidentDetail);
    }

    @GetMapping()
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        return ResponseEntity.status(HttpStatus.FOUND).body(incidents);
    }

    @GetMapping(value = "/{docket_no}/param", params = "version=1")
    public ResponseEntity<Incident> getAnyTicket(@PathVariable(value = "docket_no") Integer docket_no){
        Incident incident = incidentService.getAnyTicket(docket_no);
        return ResponseEntity.status(HttpStatus.FOUND).body(incident);
    }

    @GetMapping(value = "/{docket_no}/param", params = "version=2")
    public ResponseEntity<Incident> getIncidentByDocketNo(@PathVariable Integer docket_no){
        Incident incident = incidentService.getIncidentByDocketNo(docket_no);
        return ResponseEntity.status(HttpStatus.FOUND).body(incident);
    }

    @PatchMapping("/admin/{docket_no}")
    public ResponseEntity<Incident> updateTicket(@PathVariable(value = "docket_no") Integer docket_no ,
                                                 @RequestBody Incident incident){
        Incident incidentResponseEntity = incidentService.updateTicket(docket_no, incident);
        return ResponseEntity.ok(incidentResponseEntity);
    }

    @PutMapping("/{docket_no}")
    public ResponseEntity<Incident> updateIncidentDetail(@PathVariable(value = "docket_no") Integer docket_no,
                                                         @Validated @RequestBody Incident incident){
        Incident incidentDetail = incidentService.updateIncidentDetail(docket_no, incident);
        return ResponseEntity.ok(incidentDetail);
    }

    @DeleteMapping("/{docket_no}")
    public void deleteIncidentTicket(@PathVariable(value = "docket_no") Integer docket_no){
        incidentService.deleteIncidentTicket(docket_no);
    }

    @RequestMapping("/customer/{username}")
    public ResponseEntity<String> getCustomerByUsername(@PathVariable("username") String username){
        String details = incidentService.getCustomerByUsername(username);
        return ResponseEntity.ok(details);

    }

    /*@GetMapping(value = "/admin/{docket_no}/param", params = "version=1")
    public ResponseEntity<Incident> getAnyTicket(@PathVariable(value = "docket_no") Integer docket_no){
        Incident incident = incidentService.getAnyTicket(docket_no);
        return ResponseEntity.status(HttpStatus.FOUND).body(incident);
    }*/

    /*@GetMapping(value = "/customer/{docket_no}/param", params = "version=2")
    public ResponseEntity<Incident> getIncidentByDocketNo(@PathVariable Integer docket_no){
        Incident incident = incidentService.getIncidentByDocketNo(docket_no);
        return ResponseEntity.status(HttpStatus.FOUND).body(incident);
    }*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArguments(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String,String> handleNoSuchElementException(NoSuchElementException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return errors;
    }
}
