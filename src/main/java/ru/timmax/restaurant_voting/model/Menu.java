package ru.timmax.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.timmax.restaurant_voting.HasId;

import java.time.LocalDate;

@Entity
//@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "m_date"}, name = "menu_unique_restaurant_mdate_idx")})
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "mdate"}, name = "menu_unique_restaurant_mdate_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Menu extends BaseEntity implements HasId {

/*  // Такой вариант не получился: Swagger вместо mDate показывал mdate.
    // Но mDate было-бы лучше.
    @Column(name = "m_date", nullable = false)
    @NotNull
    private LocalDate mDate;
*/
    @Column(name = "mdate", nullable = false)
    @NotNull
    private LocalDate mdate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    //@JsonIgnore
    private Restaurant restaurant;
}