package com.example.demo.Model;

import com.example.demo.Enumset.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import com.example.demo.Custom.mobileValidate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private int customerid;

    @Column(name = "name")
    @NotBlank(message = "Name Required")
    private String name;

    @Column(name = "nic")
    @NotBlank(message = "NIC Required")
    private String nic;

    @Column(name = "gender", columnDefinition = "CHAR")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "address")
    @NotBlank(message = "Address Required")
    private String address;

    @NotBlank(message = "Mobile Required")
    @mobileValidate
    @Column(name = "mobile")
    private String mobile;

    @NotBlank(message = "Email Required")
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "occupation")
    @NotBlank(message = "Occupation Required")
    private String occupation;

    @Column(name = "status")
    private int status;
}
