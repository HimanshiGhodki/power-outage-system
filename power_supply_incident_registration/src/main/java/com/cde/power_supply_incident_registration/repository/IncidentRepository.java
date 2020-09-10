package com.cde.power_supply_incident_registration.repository;

import com.cde.power_supply_incident_registration.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {
}
