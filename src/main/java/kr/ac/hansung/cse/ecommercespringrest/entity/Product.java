package kr.ac.hansung.cse.ecommercespringrest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * A Product is an entity that represents an article for sale.
 * Products are associated with categories either directly or indirectly.
 * For instance, for a given child category B, if it's associated to a
 * parent category A, then the Product is associated with
 * category B (directly) and A (indirectly).
 *
 * @author dnardelli
 */
@Getter
@Setter
@Entity
@Table(name = "app_product")
public class Product extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "app_product_category",
            joinColumns = @JoinColumn(name = "productid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid"))
    private List<Category> categories;

    @Column(name = "price", nullable = false)
    private double price;

}