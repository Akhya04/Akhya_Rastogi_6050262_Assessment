package com.cg.service;

import com.cg.entity.Loan;
import com.cg.exception.DuplicateLoanApplicationException;
import com.cg.exception.InvalidLoanAmountException;
import com.cg.exception.LoanNotFoundException;
import com.cg.repo.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan createLoan(Loan loan) {
        // Business Rule 1: Validate Loan Amount
        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
        }

        // Business Rule 2: Check for existing PENDING loan
        boolean hasPendingLoan = loanRepository.existsByApplicantNameAndStatus(loan.getApplicantName(), "PENDING");
        if (hasPendingLoan) {
            throw new DuplicateLoanApplicationException("Applicant already has a pending loan application.");
        }

        // Set default status and save
        loan.setStatus("PENDING");
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + id));
    }

    @Override
    public Loan updateLoanStatus(Long id, String status) {
        Loan loan = getLoanById(id); // Automatically throws LoanNotFoundException if not found
        loan.setStatus(status.toUpperCase());
        return loanRepository.save(loan);
    }
}