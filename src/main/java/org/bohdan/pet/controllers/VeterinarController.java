package org.bohdan.pet.controllers;

import org.bohdan.pet.dao.VeterinarDAO;
import org.bohdan.pet.models.Veterinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/veterinarians")
public class VeterinarController
{
    private final VeterinarDAO veterinarDAO;
    @Autowired
    public VeterinarController(VeterinarDAO veterinarDAO) {this.veterinarDAO = veterinarDAO;}

    @RequestMapping(method = RequestMethod.GET)
    public String vetShow(Model model)
    {
        model.addAttribute("allVets", veterinarDAO.showVets());
        return "veterinarianPages/veterinariansPage";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newVetPage(@ModelAttribute("vet") Veterinar veterinar) { return "veterinarianPages/newVet"; }
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newVet(@ModelAttribute ("vet") @Valid Veterinar veterinar,
                         BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "veterinarianPages/newVet";

        veterinarDAO.saveVet(veterinar);

        return "redirect:/veterinarians";
    }

    @RequestMapping(value = "/edit/{idVet}", method = RequestMethod.GET)
    public String editVetPage(@PathVariable("idVet") int idVet, Model model)
    {
        model.addAttribute("vet", veterinarDAO.showVeterinar(idVet));
        return "veterinarianPages/editVet";
    }

    @RequestMapping(value = "/edit/{idVet}", method = RequestMethod.PATCH)
    public String editVet(@PathVariable("idVet") int idVet,
                          @ModelAttribute("vet") @Valid Veterinar veterinar,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "veterinarianPages/editVet";

        veterinarDAO.editVet(veterinar, idVet);

        return "redirect:/veterinarians";
    }

    @RequestMapping(value = "/{idVet}", method = RequestMethod.DELETE)
    public String delVet(@PathVariable("idVet") int idVet)
    {
        veterinarDAO.deleteVet(idVet);

        return "redirect:/veterinarians";
    }
}
