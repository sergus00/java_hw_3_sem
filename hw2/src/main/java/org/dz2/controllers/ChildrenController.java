package org.dz2.controllers;

import org.dz2.entities.Child;
import org.dz2.services.ChildService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChildrenController {
    private final ChildService childService;

    public ChildrenController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("/children")
    public String getAllChildren(Model model) {
        model.addAttribute("children", childService.getAllChildren());
        return "children/children";
    }

    @GetMapping("/children/add/{parentsId}")
    public String postAddChild(@PathVariable(value = "parentsId") String parentsId, Model model) {
        model.addAttribute("parentsId", parentsId);
        return "children/addChild";
    }

    @PostMapping("/children/add/{parentsId}")
    public String addChild(Child child, @PathVariable(value = "parentsId") Integer parentsId, Model model) {
        Child result = childService.addChild(child, parentsId);
        if (result == null) {
            model.addAttribute("isError", true);
            model.addAttribute("errorMessage", "Не удалось добавить ребенка");
            return "children/addChild";
        }
        return "redirect:/parents";
    }

    @GetMapping("/children/{id}")
    public String child(@PathVariable(value = "id") Integer id, Model model) {
        Child child = childService.getById(id);
        if (child == null) {
            return "redirect:/parents";
        }
        model.addAttribute("child", child);
        model.addAttribute("school", childService.getSchool(child));
        return "children/child";
    }

    @PostMapping("/children/{id}")
    public String saveSchool(Integer schoolId, @PathVariable(value = "id") Integer id, Model model) {
        Child child = childService.getById(id);
        if (child == null) {
            model.addAttribute("isError", true);
            model.addAttribute("errorMessage", "Не удалось найти ребенка");
            return "children/child";
        }
        if (!childService.setSchool(id, schoolId)) {
            model.addAttribute("isError", true);
            model.addAttribute("errorMessage", "Не удалось записать в учебное заведение");
            return "children/child";
        }
        return "redirect:/parents";
    }
}
