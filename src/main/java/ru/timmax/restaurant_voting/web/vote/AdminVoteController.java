package ru.timmax.restaurant_voting.web.vote;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.timmax.restaurant_voting.model.Vote;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.net.URI;
import java.util.List;

import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.timmax.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminVoteController extends AbstractVoteController {
    static final String REST_URL = "/api/admin/votes";

    //@Autowired
    //protected RestaurantRepository restaurantRepository;

    // CRUD

    // Create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody Vote vote) {
        int userId = authUser.id();
        log.info("create {} for user {}", vote, userId);
        checkNew(vote);
        /*
        //
        Optional<Menu> optionalMenu = menuRepository.getByName( menuItem.getRestaurant().getName(), userId);
        menuItem.setRestaurant( optionalMenu.get());
        //
        */
        Vote created = service.save(vote, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    // Read
    @Override
    @GetMapping
    public List<Vote> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return super.getAll(authUser);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable int id) {
        return super.get(authUser, id);
    }

    // Update
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser,
                       @Valid @RequestBody Vote vote,
                       @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} for user {}", vote, userId);
        assureIdConsistent(vote, id);
        service.save(vote, userId);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete {} for user {}", id, authUser.id());
        repository.deleteExisted(id);
    }
}