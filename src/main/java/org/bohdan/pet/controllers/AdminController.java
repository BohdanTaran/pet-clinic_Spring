package org.bohdan.pet.controllers;

import org.bohdan.pet.dao.AdminDAO;
import org.bohdan.pet.models.Owner;
import org.bohdan.pet.models.Pet;
import org.bohdan.pet.models.Veterinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController
{
    private final AdminDAO adminDAO;
    @Autowired
    public AdminController(AdminDAO adminDAO) {this.adminDAO = adminDAO;}

    @GetMapping("/home")
    public String homeShow()
    {
        return "homePage";
    }

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

    @GetMapping("/owners/{id}/newPet")
    public String addPetPage(@PathVariable("id") int id, @ModelAttribute("pet") Pet pet,  Model model)
    {
        model.addAttribute("owner", adminDAO.showOwner(id));
        return "petPages/newPet";
    }
    @PostMapping("/owners/{id}/newPet")
    public String addPet(@PathVariable("id") int id,
                         @ModelAttribute("owner") Owner owner, @ModelAttribute("pet") @Valid Pet pet,
                         BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) return "petPages/newPet";

        model.addAttribute("owner", adminDAO.showOwner(id));
        adminDAO.savePet(pet, id);

        return "redirect:/owners/" + id;
    }

    @GetMapping("/owners/{id}/editPet/{idPet}")
    public String editPetPage(@PathVariable("id") int id, @PathVariable("idPet") int idPet,
                              Model model)
    {
        model.addAttribute("pet", adminDAO.showPet(id, idPet));
        model.addAttribute("owner", adminDAO.showOwner(id));
        return "petPages/editPet";
    }

    @PatchMapping("/owners/{id}/editPet/{idPet}")
    public String editPet(@PathVariable("id") int id, @PathVariable("idPet") int idPet,
                           @ModelAttribute("owner") Owner owner, @ModelAttribute("pet") @Valid Pet pet,
                           BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "petPages/editPet";

        adminDAO.editPet(pet, id, idPet);

        return "redirect:/owners/" + id;
    }

    @DeleteMapping("owners/{id}/deletePet/{idPet}")
    public String deletePet(@PathVariable("id") int id, @PathVariable("idPet") int idPet)
    {
        adminDAO.deletePet(id, idPet);
        return "redirect:/owners/" + id;
    }

    @GetMapping("/veterinarians")
    public String vetShow(Model model)
    {
        model.addAttribute("allVets", adminDAO.showVets());
        return "veterinarianPages/veterinariansPage";
    }

    @GetMapping("/veterinarians/new")
    public String newVetPage(@ModelAttribute ("vet") Veterinar veterinar) { return "veterinarianPages/newVet"; }
    @PostMapping("/veterinarians/new")
    public String newVet(@ModelAttribute ("vet") @Valid Veterinar veterinar,
                         BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "veterinarianPages/newVet";

        adminDAO.saveVet(veterinar);

        return "redirect:/veterinarians";
    }

    @GetMapping("/veterinarians/edit/{idVet}")
    public String editVetPage(@PathVariable("idVet") int idVet, Model model)
    {
        model.addAttribute("vet", adminDAO.showVeterinar(idVet));
        return "veterinarianPages/editVet";
    }
    @PatchMapping("/veterinarians/edit/{idVet}")
    public String editVet(@PathVariable("idVet") int idVet,
                          @ModelAttribute("vet") @Valid Veterinar veterinar,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "veterinarianPages/editVet";

        adminDAO.editVet(veterinar, idVet);

        return "redirect:/veterinarians";
    }
    @DeleteMapping("/veterinarians/{idVet}")
    public String delVet(@PathVariable("idVet") int idVet)
    {
        adminDAO.deleteVet(idVet);

        return "redirect:/veterinarians";
    }
}
