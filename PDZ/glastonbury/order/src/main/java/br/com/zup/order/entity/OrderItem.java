package br.com.zup.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItem {

    @Id
    private String id;

    private String name;

    private BigDecimal amount;

    private Integer quantity;
}
