package org.bohdan.pet.controllers;

import org.bohdan.pet.dao.OwnerDAO;
import org.bohdan.pet.dao.PetDAO;
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
    private final OwnerDAO ownerDAO;
    private final PetDAO petDAO;

    @Autowired
    public PetController(OwnerDAO ownerDAO, PetDAO petDAO)
    {
        this.ownerDAO = ownerDAO;
        this.petDAO = petDAO;
    }

    @GetMapping("/{id}/newPet")
    public String addPetPage(@PathVariable("id") int id, @ModelAttribute("pet") Pet pet, Model model)
    {
        model.addAttribute("owner", ownerDAO.showOwner(id));
        return "petPages/newPet";
    }
    @PostMapping("/{id}/newPet")
    public String addPet(@PathVariable("id") int id,
                         @ModelAttribute("owner") Owner owner, @ModelAttribute("pet") @Valid Pet pet,
                         BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) return "petPages/newPet";

        model.addAttribute("owner", ownerDAO.showOwner(id));
        petDAO.savePet(pet, id);

        return "redirect:/owners/" + id;
    }

    @GetMapping("/{id}/editPet/{idPet}")
    public String editPetPage(@PathVariable("id") int id, @PathVariable("idPet") int idPet,
                              Model model)
    {
        model.addAttribute("pet", petDAO.showPet(id, idPet));
        model.addAttribute("owner", ownerDAO.showOwner(id));
        return "petPages/editPet";
    }

    @PatchMapping("/{id}/editPet/{idPet}")
    public String editPet(@PathVariable("id") int id, @PathVariable("idPet") int idPet,
                          @ModelAttribute("owner") Owner owner, @ModelAttribute("pet") @Valid Pet pet,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "petPages/editPet";

        petDAO.editPet(pet, id, idPet);

        return "redirect:/owners/" + id;
    }

    @DeleteMapping("/{id}/deletePet/{idPet}")
    public String deletePet(@PathVariable("id") int id, @PathVariable("idPet") int idPet)
    {
        petDAO.deletePet(id, idPet);
        return "redirect:/owners/" + id;
    }
}
