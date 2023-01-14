package ru.timmax.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.Vote;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v WHERE :userId = :userId")
    List<Vote> getAll(int userId);

    @Query("SELECT v FROM Vote v WHERE v.id = :id AND :userId = :userId")
    Optional<Vote> get(int id, int userId);
}