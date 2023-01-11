package ru.timmax.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.Restaurant;
import ru.timmax.restaurant_voting.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        return restaurantRepository.save(restaurant);
    }
}