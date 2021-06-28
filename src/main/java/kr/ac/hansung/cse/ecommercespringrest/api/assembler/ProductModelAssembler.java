package kr.ac.hansung.cse.ecommercespringrest.api.assembler;

import kr.ac.hansung.cse.ecommercespringrest.api.Representationmodel.CategoryModel;
import kr.ac.hansung.cse.ecommercespringrest.api.Representationmodel.ProductModel;
import kr.ac.hansung.cse.ecommercespringrest.api.controller.CategoryController;
import kr.ac.hansung.cse.ecommercespringrest.api.controller.ProductController;
import kr.ac.hansung.cse.ecommercespringrest.entity.Category;
import kr.ac.hansung.cse.ecommercespringrest.entity.Product;
import kr.ac.hansung.cse.ecommercespringrest.util.CollectionCheck;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Transform {@link Product Entity} into {@link ProductModel} DTO
 *
 * @author dnardelli
 */
@Component
public class ProductModelAssembler
        extends RepresentationModelAssemblerSupport<Product, ProductModel> {

        public ProductModelAssembler() {
            super(ProductController.class, ProductModel.class);
        }

        @Override
        public ProductModel toModel(Product entity) {

            ProductModel productModel = instantiateModel(entity);

            productModel.add( linkTo(methodOn(ProductController.class).retrieveProduct(entity.getId()))
                    .withSelfRel() );

            productModel.setId(entity.getId());
            productModel.setName(entity.getName());
            productModel.setPrice(entity.getPrice());

            if(!CollectionCheck.isEmpty(entity.getCategories()))
                productModel.setCategories(toCategoryModel(entity.getCategories()));
            return productModel;
        }

        @Override
        public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends Product> entities) {

            CollectionModel<ProductModel> actorModels = super.toCollectionModel(entities);

            actorModels.add(linkTo(methodOn(ProductController.class).retrieveAllProducts()).withSelfRel());

            return actorModels;
        }

        private List<CategoryModel> toCategoryModel(List<Category> categories) {

            if (categories.isEmpty())
                return Collections.emptyList();

            return categories.stream()
                    .map(category -> CategoryModel.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build()
                            .add(linkTo(methodOn(CategoryController.class).retrieveCategory(category.getId()))
                                    .withRel("category")))
                    .collect(Collectors.toList());
        }
}