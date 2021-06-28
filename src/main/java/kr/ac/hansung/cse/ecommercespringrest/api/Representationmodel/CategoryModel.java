package kr.ac.hansung.cse.ecommercespringrest.api.Representationmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

/**
 * Spring HATEOAS-oriented DTO for {@see Category} entity
 *
 * @author dnardelli
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "categories", itemRelation = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryModel extends RepresentationModel<CategoryModel> {

    private Long id;
    private  String name;
    private List<ProductModel> products;
}
