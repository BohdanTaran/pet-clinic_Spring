package org.bohdan.pet.controllers;

import org.bohdan.pet.dao.AdminDAO;
import org.bohdan.pet.models.Veterinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VeterinarController
{
    private final AdminDAO adminDAO;
    @Autowired
    public VeterinarController(AdminDAO adminDAO) {this.adminDAO = adminDAO;}

    @GetMapping("/veterinarians")
    public String vetShow(Model model)
    {
        model.addAttribute("allVets", adminDAO.showVets());
        return "veterinarianPages/veterinariansPage";
    }

    @GetMapping("/veterinarians/new")
    public String newVetPage(@ModelAttribute("vet") Veterinar veterinar) { return "veterinarianPages/newVet"; }
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
