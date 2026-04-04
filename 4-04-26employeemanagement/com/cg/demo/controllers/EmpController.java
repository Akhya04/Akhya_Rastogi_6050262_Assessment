package com.cg.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cg.demo.dto.EmployeeDTO;
import com.cg.demo.service.EmployeeService;
import jakarta.validation.Valid;

@Controller
public class EmpController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/viewall")
    public String viewAll(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "viewall";
    }

    @GetMapping("/edit/{eid}")
    public String showEditForm(@PathVariable("eid") Integer eid, Model model) {
        model.addAttribute("employeeDTO", service.getById(eid));
        return "editemployee";
    }

    @PostMapping("/update")
    public String updateEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO dto, 
                                 BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) return "editemployee";
        service.save(dto);
        ra.addFlashAttribute("message", "Employee Edited Successfully!");
        return "redirect:/viewall";
    }

    @GetMapping("/delete/{eid}")
    public String deleteEmployee(@PathVariable("eid") Integer eid, RedirectAttributes ra) {
        service.delete(eid);
        ra.addFlashAttribute("message", "Employee Deleted Successfully!");
        return "redirect:/viewall";
    }
}