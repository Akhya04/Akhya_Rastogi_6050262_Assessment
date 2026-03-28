package com.cg.controller;

import com.cg.entity.Loan;
import com.cg.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan createdLoan = loanService.createLoan(loan);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return new ResponseEntity<>(loanService.getAllLoans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return new ResponseEntity<>(loanService.getLoanById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Loan> updateLoanStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        Loan updatedLoan = loanService.updateLoanStatus(id, newStatus);
        return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
    }
}