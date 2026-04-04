package com.cg.demo.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeDTO {
    private Integer empId;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 chars")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only alphabets")
    private String empName;

    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Min salary 1000")
    @Max(value = 500000, message = "Max salary 500000")
    private Double empSal;

    @NotNull(message = "DOJ is required")
    @FutureOrPresent(message = "Date must be current or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate empDoj;

    @NotBlank(message = "Dept is required")
    @Pattern(regexp = "(?i)hr|production", message = "Dept must be hr or production")
    private String deptName;

    // Getters and Setters
    public Integer getEmpId() { return empId; }
    public void setEmpId(Integer empId) { this.empId = empId; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }
    public Double getEmpSal() { return empSal; }
    public void setEmpSal(Double empSal) { this.empSal = empSal; }
    public LocalDate getEmpDoj() { return empDoj; }
    public void setEmpDoj(LocalDate empDoj) { this.empDoj = empDoj; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}