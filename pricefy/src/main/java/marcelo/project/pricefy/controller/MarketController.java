package marcelo.project.pricefy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.market.MarketRequestDto;
import marcelo.project.pricefy.dto.response.market.MarketResponseDto;
import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Mercado", description = "Endpoints para mercado")
@RequestMapping("pricefy/market/")
public class MarketController {

    @Autowired
    private final MarketService marketService;

    @PostMapping("create/{idUser}")
    @Operation(summary = "Criação de mercado", description = "Criar mercado")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public ResponseEntity<MarketResponseDto> createMarket(@PathVariable Long idUser,@Valid @RequestBody MarketRequestDto marketRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(marketService.createMarket(idUser, marketRequestDto));
    }

    @GetMapping("listAll/{idUser}")
    @Operation(summary = "Lista de mercado", description = "Listar todos os mercados do mesmo usuário")
    @PreAuthorize("#idUser == authentication.principal.idUser")
    public ResponseEntity<List<MarketModel>> listAllByUser(@PathVariable Long idUser){
        return ResponseEntity.status(HttpStatus.OK).body(marketService.listingAllByUser(idUser));
    }
}
