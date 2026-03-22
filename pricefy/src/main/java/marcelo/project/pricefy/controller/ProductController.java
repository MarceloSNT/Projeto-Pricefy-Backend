package marcelo.project.pricefy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.product.ProductRequestDto;
import marcelo.project.pricefy.dto.request.product.ProductRequestEditDto;
import marcelo.project.pricefy.dto.response.product.ProductResponseDto;
import marcelo.project.pricefy.entity.ProductModel;
import marcelo.project.pricefy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("listAll/{idUser}")
    @Operation(summary = "Lista de produtos", description = "Listar todos os produto do mesmo user")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public ResponseEntity<List<ProductModel>> listAllProduct(@PathVariable Long idUser){
        return ResponseEntity.ok(productService.listAllByUser(idUser));
    }

    @PutMapping("edit/{idProduct}")
    @Operation(summary = "Editar de produto", description = "Editar o produto do mesmo usuário")
    public ResponseEntity<ProductResponseDto> editProduct(@Valid @RequestBody ProductRequestEditDto productRequestEditDto, @PathVariable Long idProduct, HttpServletRequest request){
        Long idUser = (Long) request.getAttribute("idUser");

        return ResponseEntity.ok(productService.editProduct(idProduct, idUser, productRequestEditDto));
    }

    @DeleteMapping("delete/{idProduct}")
    @Operation(summary = "Deletar produto", description = "Deletar o produto do mesmo usuário")
    public void deleteByUser(@PathVariable Long idProduct, HttpServletRequest request){
        Long idUser = (Long) request.getAttribute("idUser");
        productService.deleteProduct(idProduct,idUser);
    }

}
