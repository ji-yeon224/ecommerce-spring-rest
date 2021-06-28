package kr.ac.hansung.cse.ecommercespringrest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * A Category is an entity to classify Products.
 * Categories can have childCategories, but a given category has a single parent (optional).
 *
 * @author dnardelli
 */
@Getter
@Setter
@Entity
@Table(name = "app_category")
public class Category extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parentid")
    private Category parent;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private List<Product> products;

    @OneToMany(mappedBy = "parent")
    private List<Category> childCategories;

}
