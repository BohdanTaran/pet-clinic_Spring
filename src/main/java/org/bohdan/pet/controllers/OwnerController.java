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
@RequestMapping("/owners")
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

    @RequestMapping(method = RequestMethod.GET)
    public String ownersShow(Model model)
    {
        model.addAttribute("allOwners", ownerDAO.showOwners());
        return "ownerPages/ownersPage";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ownerInfo(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("owner", ownerDAO.showOwner(id));
        model.addAttribute("visit_of_pets", petDAO.show_PetVisits(id));
        return "ownerPages/ownerInfo";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newOwnerPage(@ModelAttribute("owner") Owner owner) { return "ownerPages/newOwner"; }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newOwner(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "ownerPages/newOwner";

        ownerDAO.saveOwner(owner);

        return "redirect:/owners";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editOwnerPage(@PathVariable("id") int id, @ModelAttribute("owner") Owner owner, Model model)
    {
        model.addAttribute("owner", ownerDAO.showOwner(id));
        return "ownerPages/editOwnerInfo";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.PATCH)
    public String editOwner(@PathVariable("id") int id, @ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) return "ownerPages/editOwnerInfo";

        ownerDAO.editInfoOwner(id, owner);

        return "redirect:/owners/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteOwner(@PathVariable("id") int id)
    {
        ownerDAO.deleteOwner(id);
        return "redirect:/owners";
    }


}
