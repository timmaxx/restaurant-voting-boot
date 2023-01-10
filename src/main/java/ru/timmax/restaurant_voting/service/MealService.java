package ru.timmax.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.timmax.restaurant_voting.model.Meal;
import ru.timmax.restaurant_voting.repository.MealRepository;
import ru.timmax.restaurant_voting.repository.UserRepository;

@Service
@AllArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    @Transactional
    public Meal save(Meal meal, int userId) {
        meal.setUser(userRepository.getExisted(userId));
        return mealRepository.save(meal);
    }
}
