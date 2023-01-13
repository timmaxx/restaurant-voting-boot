package ru.timmax.restaurant_voting.web.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.timmax.restaurant_voting.model.Menu;
import ru.timmax.restaurant_voting.web.AuthUser;

import java.util.List;

@RestController
@RequestMapping(value = ProfileMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProfileMenuController extends AbstractMenuController {
    static final String REST_URL = "/api/profile/menus";

    // cRud

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
}