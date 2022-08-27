package org.bohdan.pet.controllers;

import org.bohdan.pet.dao.OwnerDAO;
import org.bohdan.pet.dao.PetDAO;
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
    private final OwnerDAO ownerDAO;
    private final PetDAO petDAO;

    @Autowired
    public OwnerController(OwnerDAO ownerDAO, PetDAO petDAO)
    {
        this.ownerDAO = ownerDAO;
        this.petDAO = petDAO;
    }

    @GetMapping("/owners")
    public String ownersShow(Model model)
    {
        model.addAttribute("allOwners", ownerDAO.showOwners());
        return "ownerPages/ownersPage";
    }

    @GetMapping("/owners/{id}")
    public String ownerInfo(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("owner", ownerDAO.showOwner(id));
        model.addAttribute("visit_of_pets", petDAO.show_PetVisits(id));
        return "ownerPages/ownerInfo";
    }

    @GetMapping("/owners/new")
    public String newOwnerPage(@ModelAttribute("owner") Owner owner) { return "ownerPages/newOwner"; }

    @PostMapping("/owners/new")
    public String newOwner(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "ownerPages/newOwner";

        ownerDAO.saveOwner(owner);

        return "redirect:/owners";
    }

    @GetMapping("/owners/{id}/edit")
    public String editOwnerPage(@PathVariable("id") int id, @ModelAttribute("owner") Owner owner, Model model)
    {
        model.addAttribute("owner", ownerDAO.showOwner(id));
        return "ownerPages/editOwnerInfo";
    }

    @PatchMapping("/owners/{id}/edit")
    public String editOwner(@PathVariable("id") int id, @ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "ownerPages/editOwnerInfo";

        ownerDAO.editInfoOwner(id, owner);

        return "redirect:/owners/" + id;
    }

    @DeleteMapping("/owners/{id}")
    public String deleteOwner(@PathVariable("id") int id)
    {
        ownerDAO.deleteOwner(id);
        return "redirect:/owners";
    }


}
