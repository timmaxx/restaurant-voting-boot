package ru.timmax.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.timmax.restaurant_voting.HasId;

import java.time.LocalDate;

@Entity
@Table(name = "vote",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"user_id", "restaurant_id", "vdate"},
                name = "vote_unique_user_restaurant_vdate_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Vote extends BaseEntity implements HasId {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    //@JsonIgnore
    private Restaurant restaurant;

/*  // Такой вариант не получился: Swagger вместо vDate показывал vdate.
    // Но mDate было-бы лучше.
    @Column(name = "v_date", nullable = false)
    @NotNull
    private LocalDate vDate;
*/
    @Column(name = "vdate", nullable = false)
    @NotNull
    private LocalDate vdate;
}