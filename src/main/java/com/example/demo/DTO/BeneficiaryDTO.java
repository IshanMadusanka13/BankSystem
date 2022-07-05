package com.example.demo.DTO;

import com.example.demo.Custom.mobileValidate;
import com.example.demo.Custom.nicValidate;
import com.example.demo.Model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beneficiary")
public class BeneficiaryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bid")
    private int bid;

    @NotBlank(message = "Name is Required")
    @Column(name="name")
    private String name;

    @NotBlank(message = "NIC is Required")
    @nicValidate
    @Column(name = "nic")
    private String nic;

    @NotBlank(message = "Address is Required")
    @Column(name="address")
    private String address;

    @NotBlank(message = "Mobile is Required")
    @mobileValidate
    @Column(name="mobile")
    private String mobile;

    @NotBlank(message = "Email is Required")
    @Email
    @Column(name="email")
    private String email;

    @NotBlank(message = "Relationship is Required")
    @Column(name = "relationship")
    private String relationship;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "cid")
    private Customer cid;

}
