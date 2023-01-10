package ru.timmax.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.timmax.restaurant_voting.model.Role;
import ru.timmax.restaurant_voting.model.User;
import ru.timmax.restaurant_voting.to.UserTo;

import static ru.timmax.restaurant_voting.config.SecurityConfiguration.PASSWORD_ENCODER;

@UtilityClass
public class UsersUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), userTo.getCaloriesPerDay(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setCaloriesPerDay(userTo.getCaloriesPerDay());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}