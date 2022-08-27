package org.bohdan.pet.controllers;

import org.bohdan.pet.dao.AdminDAO;
import org.bohdan.pet.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class OwnerController
{
    private final AdminDAO adminDAO;
    @Autowired
    public OwnerController(AdminDAO adminDAO) {this.adminDAO = adminDAO;}

    @GetMapping("/owners")
    public String ownersShow(Model model)
    {
        model.addAttribute("allOwners", adminDAO.showOwners());
        return "ownerPages/ownersPage";
    }

    @GetMapping("/owners/{id}")
    public String ownerInfo(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("owner", adminDAO.showOwner(id));
        model.addAttribute("visit_of_pets", adminDAO.show_PetVisits(id));
        return "ownerPages/ownerInfo";
    }

    @GetMapping("/owners/new")
    public String newOwnerPage(@ModelAttribute("owner") Owner owner) { return "ownerPages/newOwner"; }

    @PostMapping("/owners/new")
    public String newOwner(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "ownerPages/newOwner";

        adminDAO.saveOwner(owner);

        return "redirect:/owners";
    }

    @GetMapping("/owners/{id}/edit")
    public String editOwnerPage(@PathVariable("id") int id, @ModelAttribute("owner") Owner owner, Model model)
    {
        model.addAttribute("owner", adminDAO.showOwner(id));
        return "ownerPages/editOwnerInfo";
    }

    @PatchMapping("/owners/{id}/edit")
    public String editOwner(@PathVariable("id") int id, @ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "ownerPages/editOwnerInfo";

        adminDAO.editInfoOwner(id, owner);

        return "redirect:/owners/" + id;
    }

    @DeleteMapping("/owners/{id}")
    public String deleteOwner(@PathVariable("id") int id)
    {
        adminDAO.deleteOwner(id);
        return "redirect:/owners";
    }


}
