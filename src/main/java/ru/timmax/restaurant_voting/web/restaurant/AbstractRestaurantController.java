package ru.timmax.restaurant_voting.web.restaurant;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.timmax.restaurant_voting.model.Restaurant;
import ru.timmax.restaurant_voting.repository.RestaurantRepository;
import ru.timmax.restaurant_voting.service.RestaurantService;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@AllArgsConstructor
public abstract class AbstractRestaurantController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected RestaurantRepository repository;

    @Autowired
    protected RestaurantService service;

    // cRud

    // Read
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return repository.getAll(authUser.id());
    }

    public ResponseEntity<Restaurant> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get restaurant {} for user {}", id, authUser.id());
        return ResponseEntity.of(repository.get(id, authUser.id()));
    }
}