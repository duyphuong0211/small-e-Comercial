package com.example.demo.controller;
import com.example.demo.domain.Product;
import com.example.demo.dto.PageRequestDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.FilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product/filter")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FilterSpecification<Product> productFilterSpecification;

    @GetMapping("/{name}")
    public List<Product> getProductByName(@PathVariable(name = "name") String name) {
        return productRepository.findByName(name);
    }

    @GetMapping("colors/{name}")
    public List<Product> getProductByColorName(@PathVariable(name = "name") String name) {
        return productRepository.findProductByColorsName(name);
    }

//    @PostMapping("/specification")
//    public List<Product> getProducts() {
//        Specification<Product> specification = new Specification<Product>() {
//
//            @Override
//            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.equal(root.get("name"), "Product 1");
//            }
//        };
//
//        List<Product> products = productRepository.findAll(specification);
//
//        return products;
//
//    }

    @PostMapping("/specification")
    public List<Product> getProducts(@RequestBody RequestDto requestDto) {
        Specification searchSpecification = productFilterSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getOperator());
        return productRepository.findAll(searchSpecification);
    }


    @PostMapping("/specification/pagination")
    public Page<Product> getProductsPage(@RequestBody RequestDto requestDto) {
        Specification searchSpecification = productFilterSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getOperator());

        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageDto());
        return productRepository.findAll(searchSpecification, pageable);
    }

}








