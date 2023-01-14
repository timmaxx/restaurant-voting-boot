package ru.timmax.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.MenuItem;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuItemRepository extends BaseRepository<MenuItem> {
    @Query("SELECT mi FROM MenuItem mi WHERE :userId = :userId")
    List<MenuItem> getAll(int userId);

    @Query("SELECT mi FROM MenuItem mi WHERE mi.id = :id AND :userId = :userId")
    Optional<MenuItem> get(int id, int userId);
}