package marcelo.project.pricefy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.product.ProductRequestDto;
import marcelo.project.pricefy.dto.response.product.ProductResponseDto;
import marcelo.project.pricefy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Produto", description = "Endpoints para mercado")
@RequestMapping("pricefy/product/")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping("create/{idUser}")
    @Operation(summary = "Criação de produto", description = "Criar produto")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public ResponseEntity<ProductResponseDto> createProoduct(@Valid @RequestBody ProductRequestDto productRequestDto, @PathVariable Long idUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequestDto, idUser));
    }
}
