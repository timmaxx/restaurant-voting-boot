package ru.timmax.restaurant_voting.web.vote;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import ru.timmax.restaurant_voting.model.Vote;
import ru.timmax.restaurant_voting.repository.VoteRepository;
import ru.timmax.restaurant_voting.service.VoteService;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractVoteController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected VoteRepository repository;

    @Autowired
    protected VoteService service;

    // cRud

    // Read
    public List<Vote> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return repository.getAll(authUser.id());
    }

    public ResponseEntity<Vote> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get vote {} for user {}", id, authUser.id());
        return ResponseEntity.of(repository.get(id, authUser.id()));
    }
}