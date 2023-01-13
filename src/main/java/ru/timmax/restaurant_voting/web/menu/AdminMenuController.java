package ru.timmax.restaurant_voting.web.menu;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.timmax.restaurant_voting.model.Menu;
import ru.timmax.restaurant_voting.model.Restaurant;
import ru.timmax.restaurant_voting.repository.RestaurantRepository;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminMenuController extends AbstractMenuController {
    static final String REST_URL = "/api/admin/menu";

    @Autowired
    protected RestaurantRepository restaurantRepository;

    // CRUD

    // Create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody Menu menu) {
        int userId = authUser.id();
        log.info("create {} for user {}", menu, userId);
        checkNew(menu);
        //
        /*if ( menu.getRestaurant() == null) {
            ; // Не вызывался конструктор для ресторана.
        }*/
        Optional<Restaurant> optionalRestaurant = restaurantRepository.getByName( menu.getRestaurant().getName(), userId);
        /*if (optionalRestaurant.isEmpty()) {
            ; // Не найден ресторан с таким именем.
        }*/
        menu.setRestaurant( optionalRestaurant.get());
        //
        Menu created = service.save(menu, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    // Read
    @Override
    @GetMapping
    public List<Menu> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return super.getAll(authUser);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Menu> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        return super.get(authUser, id);
    }

    // Update
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser,
                       @Valid @RequestBody Menu menu,
                       @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} for user {}", menu, userId);
        assureIdConsistent(menu, id);
        service.save(menu, userId);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete {} for user {}", id, authUser.id());
        repository.deleteExisted(id);
    }
}