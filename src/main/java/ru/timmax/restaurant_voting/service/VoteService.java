package ru.timmax.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.Vote;
import ru.timmax.restaurant_voting.repository.VoteRepository;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        return voteRepository.save(vote);
    }
}