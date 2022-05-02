package com.alex.vis.internetshop.mapper;

import com.alex.vis.internetshop.dto.ProductDTO;
import com.alex.vis.internetshop.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO dto);

    @InheritInverseConfiguration
    ProductDTO fromProduct(Product product);

    List<Product> toProductList(List<ProductDTO> dtos);

    List<ProductDTO> fromProductList(List<Product> products);
}
