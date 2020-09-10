package com.cde.power_supply_incident_registration.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docket_no;

    @Column(name = "contact_no")
    @NotNull(message = "Contact number cannot be null")
    private long contact_no;

    @Column(name = "zip_code")
    @NotNull(message = "Zip code cannot be null")
    private String zip_code;

    @Column(name = "issue")
    @NotNull(message = "Issue cannot be null")
    private String issue;

    @Column(name = "time_of_ticket_creation")
    private String time_of_ticket_creation;

    @Column(name = "status")
    private String status;

    public Incident(Integer docket_no, @NotNull(message = "Contact number cannot be null") long contact_no, @NotNull(message = "Zip code cannot be null") String zip_code, @NotNull(message = "Issue cannot be null") String issue, String time_of_ticket_creation, String status) {
        this.docket_no = docket_no;
        this.contact_no = contact_no;
        this.zip_code = zip_code;
        this.issue = issue;
        this.time_of_ticket_creation = time_of_ticket_creation;
        this.status = status;
    }

    public Incident(Integer docket_no, @NotNull(message = "Issue cannot be null") String issue) {
        this.docket_no = docket_no;
        this.issue = issue;
    }

    public Incident() {}
}
