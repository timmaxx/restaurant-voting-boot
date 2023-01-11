package ru.timmax.restaurant_voting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.timmax.restaurant_voting.model.Restaurant;
import ru.timmax.restaurant_voting.repository.RestaurantRepository;
import ru.timmax.restaurant_voting.service.RestaurantService;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProfileRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/profile/restaurants";

    public ProfileRestaurantController(RestaurantRepository repository, RestaurantService service) {
        super(repository, service);
    }


    // cRud

    // Read
    @GetMapping
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return super.getAll(authUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        return super.get(authUser, id);
    }
}