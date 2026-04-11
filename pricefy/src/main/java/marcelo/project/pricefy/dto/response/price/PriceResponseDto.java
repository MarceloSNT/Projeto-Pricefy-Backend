package marcelo.project.pricefy.dto.response.price;

public record PriceResponseDto(
        Long idPrice,
        Double vlProduct,
        String product,
        String market
) {
}
