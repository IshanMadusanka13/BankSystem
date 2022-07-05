package com.example.demo.DTO;

import com.example.demo.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "savingsaccount")
@Entity
public class SavingsAccDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "said")
    private int said;

    @Column(name = "accountno")
    private int accountNo;

    @Column(name = "interestrate")
    //@NotBlank(message = "Interest Rate Required")
    private double interestRate;

    @Column(name = "opendate")
    private Date openDate;

    @Column(name = "balance")
    //@NotBlank(message = "Balance Required")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer cid;

}
