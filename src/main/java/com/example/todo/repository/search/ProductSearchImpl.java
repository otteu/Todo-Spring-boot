package com.example.todo.repository.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.todo.domain.QProduct;
import com.example.todo.domain.QProductImage;
import com.example.todo.domain.dto.ProductDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import dto.PageRequestDTO;
import dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class ProductSearchImpl implements ProductSearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO) {

        log.info("----- searchList -------");

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                Sort.by("pno").descending()
        );

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;

        // ===== content query =====
        List<Tuple> productList = queryFactory
                .select(product, productImage)
                .from(product)
                .leftJoin(product.imageList, productImage)
                .where(productImage.ord.eq(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.pno.desc())
                .fetch();

        // ===== count query =====
        long count = queryFactory
                .select(product.count())
                .from(product)
                .leftJoin(product.imageList, productImage)
                .where(productImage.ord.eq(0))
                .fetchOne();

        log.info("=========================");
        log.info(productList);

        // TODO: Tuple → ProductDTO 변환
        return PageResponseDTO.<ProductDTO>withAll()
                .dtoList(List.of())
                .totalCount((int) count)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
