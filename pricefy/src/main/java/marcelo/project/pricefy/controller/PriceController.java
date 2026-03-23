package marcelo.project.pricefy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.price.PriceRequestDto;
import marcelo.project.pricefy.dto.response.price.PriceResponseDto;
import marcelo.project.pricefy.entity.PriceModel;
import marcelo.project.pricefy.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Preço", description = "Endpoints para preço")
@RequestMapping("pricefy/price/")
public class PriceController {

    @Autowired
    private final PriceService priceService;

    @PostMapping("create/{idUser}")
    @Operation(summary = "Criar preço",description = "Criação de preço para produto")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public ResponseEntity<PriceResponseDto> createPrice(@Valid @RequestBody PriceRequestDto priceRequestDto,
                                                        @PathVariable Long idUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(priceService.createPrice(priceRequestDto,idUser));
    }

    @GetMapping("listAll/{idUser}")
    @Operation(summary = "Listar preços", description = "Listar todos os preços pelo user")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public ResponseEntity<List<PriceModel>> listingAllPrice(@PathVariable Long idUser){
        return ResponseEntity.ok(priceService.listingAll(idUser));
    }

}
