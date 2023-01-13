package ru.timmax.restaurant_voting.web.menu;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import ru.timmax.restaurant_voting.model.Menu;
import ru.timmax.restaurant_voting.repository.MenuRepository;
import ru.timmax.restaurant_voting.service.MenuService;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractMenuController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected MenuRepository repository;

    @Autowired
    protected MenuService service;

    // cRud

    // Read
    public List<Menu> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return repository.getAll(authUser.id());
    }

    public ResponseEntity<Menu> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get menu {} for user {}", id, authUser.id());
        return ResponseEntity.of(repository.get(id, authUser.id()));
    }
}