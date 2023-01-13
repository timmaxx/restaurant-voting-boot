package ru.timmax.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.Menu;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @Query("SELECT m FROM Menu m WHERE :userId = :userId")
    List<Menu> getAll(int userId);

    @Query("SELECT m FROM Menu m WHERE m.id = :id AND :userId = :userId")
    Optional<Menu> get(int id, int userId);
}