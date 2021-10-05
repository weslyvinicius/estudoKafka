package br.com.zup.order.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {

    @Id
    private String id;

    private String customerId;

    private BigDecimal amount;

    @ManyToMany
    @Cascade(CascadeType.ALL)
    private List<OrderItem> items;

    private String status;



}
