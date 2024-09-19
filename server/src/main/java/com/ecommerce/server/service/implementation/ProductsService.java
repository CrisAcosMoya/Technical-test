package com.ecommerce.server.service.implementation;

import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.exception.ResourceNotFound;
import com.ecommerce.server.mapper.ProductsMapper;
import com.ecommerce.server.repository.ProductsRepository;
import com.ecommerce.server.service.IProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductsService implements IProductsService {


    private ProductsRepository productsRepository;

    @Override
    public Mono<Products> createProduct(ProductsDto productDTO) {
        Products product = ProductsMapper.toEntity(productDTO);
        return productsRepository.save(product);
    }

    @Override
    public Flux<ProductsDto> findAll() {
        return productsRepository.findAll()
                .map(ProductsMapper::toDTO);
    }

    @Override
    public Mono<ProductsDto> update(Long id, ProductsDto productDTO) {
        return productsRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFound("Producto no encontrado",
                        "Numero", id)))
                .flatMap(product -> {
                    ProductsMapper.updateEntity(product, productDTO);
                    return productsRepository.save(product);
                })
                .map(ProductsMapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return productsRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFound("Producto no encontrado",
                        "Numero", id)))
                .flatMap(productsRepository::delete);
    }
}
