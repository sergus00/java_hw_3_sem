package org.dz2.controllers;

import org.dz2.services.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressesController {
    private final AddressService addressService;

    public AddressesController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public String getAllAddresses(Model model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        return "addresses";
    }
}
