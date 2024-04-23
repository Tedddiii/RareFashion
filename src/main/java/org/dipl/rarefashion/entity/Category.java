package org.dipl.rarefashion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Size(max = 40)
    @Column(name = "name", length = 40)
    private String name;

}