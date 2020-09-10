package com.cde.power_supply_incident_registration.service;

import com.cde.power_supply_incident_registration.client.IncidentClient;
import com.cde.power_supply_incident_registration.entity.Incident;
import com.cde.power_supply_incident_registration.repository.IncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IncidentService{

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private IncidentClient incidentClient;

    public  static final Logger log= LoggerFactory.getLogger(IncidentService.class);

    public Incident saveIncidentDetail(Incident incident) {
        log.info("saving incident");
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        incident.setTime_of_ticket_creation(formattedDate);
        incident.setStatus("New");
        incidentRepository.save(incident);
        return incident;
    }

    public Incident updateTicket(Integer docket_no, Incident incident) {
        log.info("updating incident for docket number: "+docket_no);
        Optional<Incident> optionalIncident = incidentRepository.findById(docket_no);
        optionalIncident.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        optionalIncident.get().setStatus(incident.getStatus());
        incidentRepository.save(optionalIncident.get());
        return optionalIncident.get();
    }

    public Incident getAnyTicket(Integer docket_no) {
        log.info("running version 1 of method");
        log.info("getting incident for docket number: "+docket_no);
        Optional<Incident> optionalIncident = incidentRepository.findById(docket_no);
        optionalIncident.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        Incident incident = new Incident(optionalIncident.get().getDocket_no()
                , optionalIncident.get().getIssue());
        return incident;
    }

    public Incident getIncidentByDocketNo(Integer docket_no) {
        log.info("running version 2 of method");
        log.info("getting incident for docket number: "+docket_no);
        Optional<Incident> incident = incidentRepository.findById(docket_no);
        incident.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        return incident.get();
    }

    public List<Incident> getAllIncidents() {
        log.info("get all incidents");
        List<Incident> incidents = incidentRepository.findAll();
        return incidents;
    }

    public Incident updateIncidentDetail(Integer docket_no, Incident inc) {
        log.info("updating incident for docket number: "+docket_no);
        Optional<Incident> incidentOptional = incidentRepository.findById(docket_no);
        incidentOptional.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        Incident incident = incidentOptional.get();
        incident.setContact_no(inc.getContact_no());
        incident.setIssue(inc.getIssue());
        incident.setStatus(inc.getStatus());
        incident.setZip_code(inc.getZip_code());
        incidentRepository.saveAndFlush(incident);
        return incident;
    }

    public void deleteIncidentTicket(Integer docket_no) {
        log.info("deleting incident for docket number: "+docket_no);
        Optional<Incident> incident = incidentRepository.findById(docket_no);
        incident.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        incidentRepository.delete(incident.get());
    }

    public String getCustomerByUsername(String username){
        return incidentClient.getCustomerDetails(username);
    }
}
