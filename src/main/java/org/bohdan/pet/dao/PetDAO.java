package org.bohdan.pet.dao;

import org.bohdan.pet.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PetDAO
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePet(Pet pet, int idOwner)
    {
        jdbcTemplate.update("INSERT INTO pets VALUES(?, ?, ?, ?, (SELECT COALESCE(MAX(id), 0)+1 FROM pets))",
                idOwner, pet.getName(), pet.getBreed(), pet.getType());

        Date visitDate = new Date(pet.getVisit_date().getTime());

        jdbcTemplate.update("INSERT INTO visits VALUES((SELECT COALESCE(MAX(id), 0)+1 FROM visits), ?, ?, (SELECT MAX(id) FROM pets))",
                visitDate, pet.getDescription());
    }

    public void editPet(Pet updatePet, int idOwner, int idPet)
    {
        jdbcTemplate.update("UPDATE pets SET name=?, breed=?, type=? WHERE id_owner=? AND id=?",
                updatePet.getName(), updatePet.getBreed(), updatePet.getType(), idOwner, idPet);

        jdbcTemplate.update("UPDATE visits SET visit_date=?, description=? WHERE id_pet=?",
                updatePet.getVisit_date(), updatePet.getDescription(), idPet);
    }

    public List<Pet> show_PetVisits(int idOwner)
    {
        return jdbcTemplate.query("SELECT * FROM owners INNER JOIN pets ON owners.id = pets.id_owner INNER JOIN visits ON pets.id = visits.id_pet WHERE owners.id=?", new Object[]{idOwner}, new BeanPropertyRowMapper<>(Pet.class));
    }

    public Pet showPet(int idOwner, int idPet)
    {
        return jdbcTemplate.query("SELECT * FROM owners INNER JOIN pets ON owners.id = pets.id_owner INNER JOIN visits ON pets.id = visits.id_pet WHERE owners.id=? AND pets.id=?", new Object[]{idOwner, idPet}, new BeanPropertyRowMapper<>(Pet.class))
                .stream().findAny().orElse(null);
    }

    public void deletePet(int idOwner, int idPet)
    {
        Pet petDel = jdbcTemplate.query("SELECT * FROM owners INNER JOIN pets ON owners.id = pets.id_owner INNER JOIN visits ON pets.id = visits.id_pet WHERE owners.id=? AND pets.id=?", new Object[]{idOwner, idPet}, new BeanPropertyRowMapper<>(Pet.class))
                .stream().findAny().orElse(null);

        jdbcTemplate.update("DELETE FROM visits WHERE id=?", petDel.getId());
        jdbcTemplate.update("DELETE FROM pets WHERE id=?", petDel.getId());
    }
}
