package ru.timmax.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.error.DataConflictException;
import ru.timmax.restaurant_voting.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MealRepository extends BaseRepository<Meal> {

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(int userId);

    @Query("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId);

    @Query("SELECT m FROM Meal m WHERE m.id = :id and m.user.id = :userId")
    Optional<Meal> get(int id, int userId);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = :id and m.user.id = :userId")
    Optional<Meal> getWithUser(int id, int userId);

    default Meal checkBelong(int id, int userId) {
        return get(id, userId).orElseThrow(
                () -> new DataConflictException("Meal id=" + id + " doesn't belong to User id=" + userId));
    }
}