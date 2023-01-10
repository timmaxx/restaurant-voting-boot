package ru.timmax.restaurant_voting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.timmax.restaurant_voting.util.validation.NoHtml;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_unique_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Restaurant extends BaseEntity {
    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 20)
    @NoHtml
    private String name;
}
