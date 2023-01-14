package ru.timmax.restaurant_voting.web.menu_item;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.timmax.restaurant_voting.model.MenuItem;
import ru.timmax.restaurant_voting.repository.RestaurantRepository;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.net.URI;
import java.util.List;

import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminMenuItemController extends AbstractMenuItemController {
    static final String REST_URL = "/api/admin/menu_items";

    @Autowired
    protected RestaurantRepository restaurantRepository;

    // CRUD

    // Create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody MenuItem menuItem) {
        int userId = authUser.id();
        log.info("create {} for user {}", menuItem, userId);
        checkNew(menuItem);
        /*
        //
        Optional<Menu> optionalMenu = menuRepository.getByName( menuItem.getRestaurant().getName(), userId);
        menuItem.setRestaurant( optionalMenu.get());
        //
        */
        MenuItem created = service.save(menuItem, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    // Read
    @Override
    @GetMapping
    public List<MenuItem> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return super.getAll(authUser);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> get(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable int id) {
        return super.get(authUser, id);
    }

    // Update
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser,
                       @Valid @RequestBody MenuItem menuItem,
                       @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} for user {}", menuItem, userId);
        assureIdConsistent(menuItem, id);
        service.save(menuItem, userId);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete {} for user {}", id, authUser.id());
        repository.deleteExisted(id);
    }
}