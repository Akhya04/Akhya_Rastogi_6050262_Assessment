package com.cg.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.demo.dto.EmployeeDTO;
import com.cg.demo.entities.Employee;
import com.cg.demo.repo.EmployeeRepo;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo repository;

    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getById(Integer id) {
        Employee emp = repository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return convertToDTO(emp);
    }

    public void save(EmployeeDTO dto) {
        Employee emp = new Employee();
        if (dto.getEmpId() != null) { emp.setEmpId(dto.getEmpId()); }
        emp.setEmpName(dto.getEmpName());
        emp.setEmpSal(dto.getEmpSal());
        emp.setEmpDoj(dto.getEmpDoj());
        emp.setDeptName(dto.getDeptName());
        repository.save(emp);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpId(emp.getEmpId());
        dto.setEmpName(emp.getEmpName());
        dto.setEmpSal(emp.getEmpSal());
        dto.setEmpDoj(emp.getEmpDoj());
        dto.setDeptName(emp.getDeptName());
        return dto;
    }
}