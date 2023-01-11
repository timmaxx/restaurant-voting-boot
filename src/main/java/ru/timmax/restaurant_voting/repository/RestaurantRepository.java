package ru.timmax.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r FROM Restaurant r WHERE :userId = :userId ORDER BY r.name")
    List<Restaurant> getAll(int userId);
    //List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.id = :id AND :userId = :userId")
    Optional<Restaurant> get(int id, int userId);
    //Optional<Restaurant> get(int id);
}