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
@Table(name = "ROLE")
public class Role {
    @Id
    @Size(max = 20)
    @Column(name = "role_id", nullable = false, length = 20)
    private String roleId;

    @Size(max = 40)
    @Column(name = "descr", length = 40)
    private String descr;

}