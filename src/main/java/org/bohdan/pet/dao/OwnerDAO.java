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
public class OwnerDAO
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OwnerDAO(JdbcTemplate jdbcTemplate) {
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
}
