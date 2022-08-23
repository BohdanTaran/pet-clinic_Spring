package org.bohdan.pet.dao;

import org.bohdan.pet.models.Owner;
import org.bohdan.pet.models.Pet;
import org.bohdan.pet.models.Veterinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AdminDAO
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Owner> showOwners()
    {
        return jdbcTemplate.query("SELECT * FROM owners", new BeanPropertyRowMapper<>(Owner.class));
    }

    public Owner showOwner(int id)
    {
        return jdbcTemplate.query("SELECT * FROM owners WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Owner.class))
                .stream().findAny().orElse(null);
    }

    public void saveOwner(Owner owner)
    {
        jdbcTemplate.update("INSERT INTO owners VALUES((SELECT COALESCE(MAX(id), 0)+1 FROM owners), ?, ?, ?, ?)",
                owner.getAddress(), owner.getCity(), owner.getPhone(), owner.getFirst_last_name());
    }

    public void editInfoOwner(int id, Owner updateOwner)
    {
        jdbcTemplate.update("UPDATE owners SET address=?, city=?, phone=?, first_last_name=? WHERE id=?",
                updateOwner.getAddress(), updateOwner.getCity(), updateOwner.getPhone(), updateOwner.getFirst_last_name(), id);
    }

    public void deleteOwner(int id)
    {
        jdbcTemplate.update("DELETE FROM owners WHERE id=?", id);
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

    public List<Veterinar> showVets()
    {
        return jdbcTemplate.query("SELECT * FROM vets", new BeanPropertyRowMapper<>(Veterinar.class));
    }

    public void saveVet(Veterinar veterinar)
    {
        jdbcTemplate.update("INSERT INTO vets VALUES ((SELECT COALESCE(MAX(id), 0) + 1 FROM vets), ?, ?, ?)",
                veterinar.getFirst_name(), veterinar.getLast_name(), veterinar.getSpecialty());
    }

    public Veterinar showVeterinar(int idVet)
    {
        return jdbcTemplate.query("SELECT * FROM vets WHERE vets.id=? ", new Object[]{idVet}, new BeanPropertyRowMapper<>(Veterinar.class))
                .stream().findAny().orElse(null);
    }

    public void editVet(Veterinar updateVet, int idVet)
    {
        jdbcTemplate.update("UPDATE vets SET first_name=?, last_name=?, specialty=? WHERE id=?",
                updateVet.getFirst_name(), updateVet.getLast_name(), updateVet.getSpecialty(), idVet);
    }

    public void deleteVet(int idVet)
    {
        jdbcTemplate.update("DELETE FROM vets WHERE id=?", idVet);
    }
}
