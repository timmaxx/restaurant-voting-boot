package ru.timmax.restaurant_voting.web.restaurant;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.timmax.restaurant_voting.model.Restaurant;
import ru.timmax.restaurant_voting.repository.RestaurantRepository;
import ru.timmax.restaurant_voting.service.RestaurantService;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.net.URI;
import java.util.List;

import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/admin/restaurants";

    public AdminRestaurantController(RestaurantRepository repository, RestaurantService service) {
        super(repository, service);
    }


    // CRUD

    // Create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Restaurant restaurant) {
        int userId = authUser.id();
        log.info("create {} for user {}", restaurant, userId);
        checkNew(restaurant);
        Restaurant created = service.save(restaurant, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    // Read
    @Override
    @GetMapping
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return super.getAll(authUser);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        return super.get(authUser, id);
    }

    // Update
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser,
                       @Valid @RequestBody Restaurant restaurant,
                       @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} for user {}", restaurant, userId);
        assureIdConsistent(restaurant, id);
        service.save(restaurant, userId);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete {} for user {}", id, authUser.id());
        repository.deleteExisted(id);
    }
}