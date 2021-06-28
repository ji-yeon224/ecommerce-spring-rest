package kr.ac.hansung.cse.ecommercespringrest.api.Representationmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

/**
 * Spring HATEOAS-oriented DTO for {@see Product} entity
 *
 * @author dnardelli
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "products", itemRelation = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModel extends RepresentationModel<ProductModel> {

    private Long id;
    private  String name;
    private  double price;
    private List<CategoryModel> categories;

}
