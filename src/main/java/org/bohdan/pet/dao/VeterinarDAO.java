package org.bohdan.pet.dao;

import org.bohdan.pet.models.Veterinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VeterinarDAO
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VeterinarDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
