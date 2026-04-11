package marcelo.project.pricefy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.price.PriceRequestDto;
import marcelo.project.pricefy.dto.request.price.PriceRequestEditDto;
import marcelo.project.pricefy.dto.response.price.PriceResponseDto;
import marcelo.project.pricefy.entity.PriceModel;
import marcelo.project.pricefy.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("listPrices/{idUser}")
    @Operation(summary = "Listar preços", description = "Listar todos os preços pelo user")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public ResponseEntity<List<PriceResponseDto>> listingPrices(@PathVariable Long idUser){
        return ResponseEntity.ok(priceService.listPrices(idUser));
    }

    @PutMapping("edit/{idPrice}")
    @Operation(summary = "Editar preços", description = "Editar o preço pelo user")
    public ResponseEntity<PriceResponseDto> editPrice(@Valid @RequestBody PriceRequestEditDto priceRequestEditDto,
                                                      HttpServletRequest request,
                                                      @PathVariable Long idPrice){

        Long idUser = (Long) request.getAttribute("idUser");

        return ResponseEntity.ok(priceService.edit(priceRequestEditDto, idUser, idPrice));
    }

    @DeleteMapping("delete/{idPrice}")
    @Operation(summary = "Deletar preço", description = "Deletar o preço pelo user")
    public void deletePrice(@PathVariable Long idPrice, HttpServletRequest request){
        Long idUser = (Long) request.getAttribute("idUser");
        priceService.deletePrice(idUser, idPrice);
    }

    @DeleteMapping("deleteAll/{idUser}")
    @Operation(summary = "Deletar todos os preços", description = "Deletar todos os preços do mesmo usuário")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public void deleteAllByUser(@PathVariable Long idUser){
        priceService.deleteAllPrice(idUser);
    }

    @GetMapping("/pdf")
    @Operation(summary = "Listar todos os menores preços", description = "Listar todos os preços do mesmo usuário")
    public ResponseEntity<byte[]> generatePdf(HttpServletRequest request) {

        Long idUser = (Long) request.getAttribute("idUser");
        List<PriceResponseDto> items = priceService.findLowestPrices(idUser);
        byte[] pdf = priceService.generatePdf(items);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=lista-compras.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
