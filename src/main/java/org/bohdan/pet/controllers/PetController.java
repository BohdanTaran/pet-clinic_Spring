package org.bohdan.pet.controllers;

import org.bohdan.pet.dao.AdminDAO;
import org.bohdan.pet.models.Owner;
import org.bohdan.pet.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners")
public class PetController
{
    private final AdminDAO adminDAO;
    @Autowired
    public PetController(AdminDAO adminDAO) {this.adminDAO = adminDAO;}

    @GetMapping("/{id}/newPet")
    public String addPetPage(@PathVariable("id") int id, @ModelAttribute("pet") Pet pet, Model model)
    {
        model.addAttribute("owner", adminDAO.showOwner(id));
        return "petPages/newPet";
    }
    @PostMapping("/{id}/newPet")
    public String addPet(@PathVariable("id") int id,
                         @ModelAttribute("owner") Owner owner, @ModelAttribute("pet") @Valid Pet pet,
                         BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) return "petPages/newPet";

        model.addAttribute("owner", adminDAO.showOwner(id));
        adminDAO.savePet(pet, id);

        return "redirect:/owners/" + id;
    }

    @GetMapping("/{id}/editPet/{idPet}")
    public String editPetPage(@PathVariable("id") int id, @PathVariable("idPet") int idPet,
                              Model model)
    {
        model.addAttribute("pet", adminDAO.showPet(id, idPet));
        model.addAttribute("owner", adminDAO.showOwner(id));
        return "petPages/editPet";
    }

    @PatchMapping("/{id}/editPet/{idPet}")
    public String editPet(@PathVariable("id") int id, @PathVariable("idPet") int idPet,
                          @ModelAttribute("owner") Owner owner, @ModelAttribute("pet") @Valid Pet pet,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "petPages/editPet";

        adminDAO.editPet(pet, id, idPet);

        return "redirect:/owners/" + id;
    }

    @DeleteMapping("/{id}/deletePet/{idPet}")
    public String deletePet(@PathVariable("id") int id, @PathVariable("idPet") int idPet)
    {
        adminDAO.deletePet(id, idPet);
        return "redirect:/owners/" + id;
    }
}
