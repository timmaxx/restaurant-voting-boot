package ru.timmax.restaurant_voting.web.menu_item;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import ru.timmax.restaurant_voting.model.MenuItem;
import ru.timmax.restaurant_voting.repository.MenuItemRepository;
import ru.timmax.restaurant_voting.service.MenuItemService;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractMenuItemController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected MenuItemRepository repository;

    @Autowired
    protected MenuItemService service;

    // cRud

    // Read
    public List<MenuItem> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return repository.getAll(authUser.id());
    }

    public ResponseEntity<MenuItem> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get menuItem {} for user {}", id, authUser.id());
        return ResponseEntity.of(repository.get(id, authUser.id()));
    }
}