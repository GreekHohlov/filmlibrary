package com.sber.filmlibrary.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_seq", allocationSize = 1)
public class Orders
        extends GenericModel{
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_INFO_USERS"))
    private Users users;
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDERS_INFO_FILMS"))
    private Films films;
    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;
    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;
    @Column(name = "purchase", nullable = false)
    private Boolean purchase;
}
