package org.dz2.controllers;

import org.dz2.entities.Parents;
import org.dz2.services.AddressService;
import org.dz2.services.ParentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class ParentsController {
    private final ParentsService parentsService;
    private final AddressService addressService;

    public ParentsController(ParentsService parentsService, AddressService addressService) {
        this.parentsService = parentsService;
        this.addressService = addressService;
    }

    @GetMapping("/parents")
    public String getParents(Model model) {
        model.addAttribute("parents", parentsService.GetAllParents());
        return "parents/parents";
    }

    @GetMapping("/parents/add")
    public String getAddParents(Model model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        return "parents/addParents";
    }

    @GetMapping("/parents/edit/{id}")
    public String getEditParents(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        model.addAttribute("parents", parentsService.GetById(id));
        return "parents/editParents";
    }

    @PostMapping("/parents/add")
    public String postAddParents(Parents parents, Integer addressId, Model model) {
        if (Objects.equals(parents.getMother(), null) && Objects.equals(parents.getFather(), null)) {
            model.addAttribute("isError", true);
            model.addAttribute("errorMessage", "Нужно записать хотя бы одного родителя");
            model.addAttribute("addresses", addressService.getAllAddresses());
            return "parents/addParents";
        }
        parentsService.addOrUpdateParents(parents, addressId);
        return "redirect:/parents";
    }

    @PostMapping("/parents/edit/{id}")
    public String postEditParents(Parents parents, Integer addressId, Model model) {
        if (Objects.equals(parents.getMother(), null) && Objects.equals(parents.getFather(), null)) {
            model.addAttribute("isError", true);
            model.addAttribute("errorMessage", "Нужно записать хотя бы одного родителя");
            model.addAttribute("addresses", addressService.getAllAddresses());
            return "parents/editParents";
        }
        parentsService.addOrUpdateParents(parents, addressId);
        return "redirect:/parents";
    }
}
