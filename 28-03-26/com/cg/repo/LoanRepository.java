package com.cg.repo;

import com.cg.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByApplicantNameAndStatus(String applicantName, String status);
}