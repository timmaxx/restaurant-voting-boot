package ru.timmax.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.Menu;
import ru.timmax.restaurant_voting.repository.MenuRepository;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public Menu save(Menu menu, int userId) {
        return menuRepository.save(menu);
    }
}