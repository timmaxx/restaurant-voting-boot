package ru.timmax.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.timmax.restaurant_voting.HasId;

import java.time.LocalDate;

@Entity
@Table(name = "menu_item",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"restaurant_id", "mdate", "name"},
                name = "menuitem_unique_restaurant_mdate_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class MenuItem extends NamedEntity implements HasId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

/*  // Такой вариант не получился: Swagger вместо mDate показывал mdate.
    // Но mDate было-бы лучше.
    @Column(name = "m_date", nullable = false)
    @NotNull
    private LocalDate mDate;
*/
    @Column(name = "mdate", nullable = false)
    @NotNull
    private LocalDate mdate;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;
}