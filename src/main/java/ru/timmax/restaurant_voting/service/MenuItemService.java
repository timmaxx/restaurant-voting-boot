package ru.timmax.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.MenuItem;
import ru.timmax.restaurant_voting.repository.MenuItemRepository;

@Service
@AllArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Transactional
    public MenuItem save(MenuItem menuItem, int userId) {
        return menuItemRepository.save(menuItem);
    }
}