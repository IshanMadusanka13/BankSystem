package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fixeddeposit")
@Entity
public class FixedDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    private int fid;

    @Column(name = "accountno")
    private int accountNo;

    @Column(name = "interestrate")
    private double interestRate;

    @Column(name = "opendate")
    private Date openDate;

    @Column(name = "balance")
    private double balance;

    @Column(name = "duration")
    private Date Duration;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer cid;

}
