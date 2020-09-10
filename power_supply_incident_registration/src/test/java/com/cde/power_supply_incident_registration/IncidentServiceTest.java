package com.cde.power_supply_incident_registration;

import com.cde.power_supply_incident_registration.entity.Incident;
import com.cde.power_supply_incident_registration.repository.IncidentRepository;
import com.cde.power_supply_incident_registration.service.IncidentService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(IncidentService.class)
public class IncidentServiceTest {

    @Autowired
    public IncidentService incidentService;

    @MockBean
    public IncidentRepository incidentRepository;

    Incident incident = new Incident(101, 907467894, "456789", "power cut", "10-05-2020 20:03:56", "CLOSED");

    @Test
    public void saveCustomerDetailsTest(){
        Mockito.when(incidentRepository.save(incident)).thenReturn(incident);
        Assert.assertEquals(incident, incidentService.saveIncidentDetail(incident));
    }

    @Test
    public void getAllIncidentsTest(){
        Mockito.when(incidentRepository.findAll()).thenReturn(Stream.of(incident).collect(Collectors.toList()));
        Assert.assertEquals(1, incidentService.getAllIncidents().size());
    }

    @Test
    public void getAnyTicketTest(){
        Mockito.when(incidentRepository.findById(101)).thenReturn(java.util.Optional.ofNullable(incident));
        Assert.assertEquals(incident, incidentService.getAnyTicket(101));
    }

    @Test
    public void getIncidentByDocketNoTest(){
        Mockito.when(incidentRepository.findById(101)).thenReturn(java.util.Optional.ofNullable(incident));
        Assert.assertEquals(incident, incidentService.getAnyTicket(101));
    }

    @Test
    public void updateIncidentDetailTest(){
        Mockito.when(incidentRepository.findById(101)).thenReturn(java.util.Optional.ofNullable(incident));
        incident.setIssue("new issue");
        Mockito.when(incidentRepository.saveAndFlush(incident)).thenReturn(incident);
        Assertions.assertThat(incidentService.updateIncidentDetail(101, incident)).isEqualTo(incident);
    }

    @Test
    public void updateTicketTest(){
        Mockito.when(incidentRepository.findById(101)).thenReturn(java.util.Optional.ofNullable(incident));
        incident.setStatus("New");
        Mockito.when(incidentRepository.saveAndFlush(incident)).thenReturn(incident);
        Assertions.assertThat(incidentService.updateIncidentDetail(101, incident)).isEqualTo(incident);
    }
}
