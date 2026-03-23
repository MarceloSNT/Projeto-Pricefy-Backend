package marcelo.project.pricefy.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import marcelo.project.pricefy.dto.request.price.PriceRequestDto;
import marcelo.project.pricefy.dto.request.price.PriceRequestEditDto;
import marcelo.project.pricefy.dto.response.price.PriceResponseDto;
import marcelo.project.pricefy.entity.MarketModel;
import marcelo.project.pricefy.entity.PriceModel;
import marcelo.project.pricefy.entity.ProductModel;
import marcelo.project.pricefy.entity.UserModel;
import marcelo.project.pricefy.repository.MarketRepository;
import marcelo.project.pricefy.repository.PriceRepository;
import marcelo.project.pricefy.repository.ProductRepository;
import marcelo.project.pricefy.repository.UserRepository;
import marcelo.project.pricefy.utils.Utils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final MarketRepository marketRepository;
    private final UserRepository userRepository;

    @Transactional
    public PriceResponseDto createPrice(PriceRequestDto priceRequestDto, Long idUser){
        ProductModel product = productRepository.findById(priceRequestDto.product())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        MarketModel market = marketRepository.findById(priceRequestDto.market())
                .orElseThrow(() -> new RuntimeException("Mercado não encontrado"));

        UserModel user = userRepository.findByIdUser(idUser);

        PriceModel price = new PriceModel();

        price.setIdPrice(priceRequestDto.idPrice());
        price.setVlProduct(priceRequestDto.vlProduto());
        price.setProduct(product);
        price.setMarket(market);
        price.setUser(user);

        PriceModel priceSaved = priceRepository.save(price);

        return new PriceResponseDto(
                priceSaved.getIdPrice(),
                priceSaved.getVlProduct(),
                priceSaved.getProduct().getDsName(),
                priceSaved.getMarket().getDsName(),
                priceSaved.getUser().getDsUsername()
        );
    }

    @Transactional
    public List<PriceModel> listingAll(Long idUser){
        return priceRepository.findAllByUser_IdUser(idUser);
    }

    @Transactional
    public PriceResponseDto edit(PriceRequestEditDto priceRequestEditDto, Long idUser, Long idPrice){
        PriceModel price = priceRepository.findByIdPriceAndUser_IdUser(idPrice, idUser).orElseThrow(() -> new RuntimeException("Preço não encontrado"));

        Utils.copyNonNullProperties(priceRequestEditDto, price);

        PriceModel priceEdit = priceRepository.save(price);
        return new PriceResponseDto(
                priceEdit.getIdPrice(),
                priceEdit.getVlProduct(),
                priceEdit.getProduct().getDsName(),
                priceEdit.getMarket().getDsName(),
                priceEdit.getUser().getDsUsername()
        );
    }

    @Transactional
    public void deletePrice(Long idUser, Long idPrice){
        PriceModel price = priceRepository.findByIdPriceAndUser_IdUser(idPrice, idUser).orElseThrow(() -> new RuntimeException("Preço não encontrado ou sem permissão " + idPrice));

        priceRepository.delete(price);
    }

    @Transactional
    public void deleteAllPrice(Long idUser){
        priceRepository.deleteAllByUser_IdUser(idUser);
    }

    @Transactional
    public List<PriceResponseDto> findLowestPrices(Long idUser) {

        return priceRepository.findAllByUser_IdUser(idUser)
                .stream()
                .collect(Collectors.groupingBy(
                        price -> price.getProduct().getIdProduct(),
                        Collectors.minBy(Comparator.comparing(PriceModel::getVlProduct))
                ))
                .values()
                .stream()
                .flatMap(Optional::stream)
                .map(price -> new PriceResponseDto(
                        price.getIdPrice(),
                        price.getVlProduct(),
                        price.getProduct().getDsName(),
                        price.getMarket().getDsName(),
                        price.getUser().getDsUsername()
                ))
                .toList();
    }

    @Transactional
    public byte[] generatePdf(List<PriceResponseDto> items) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            String date = LocalDate.now().toString();

            Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph("Lista de Compras - " + date, titleFont);
            document.add(title);

            document.add(new Paragraph(" "));

            Map<String, List<PriceResponseDto>> grouped = items.stream()
                    .collect(Collectors.groupingBy(PriceResponseDto::market));

            for (Map.Entry<String, List<PriceResponseDto>> entry : grouped.entrySet()) {

                Font marketFont = new Font(Font.HELVETICA, 16, Font.BOLD);
                Paragraph marketName = new Paragraph(entry.getKey(), marketFont);
                document.add(marketName);

                for (PriceResponseDto item : entry.getValue()) {

                    Paragraph product = new Paragraph(
                            "- " + item.product() + " .... " + currency.format(item.vlProduct())
                    );

                    document.add(product);
                }

                document.add(new Paragraph(" "));
            }

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
        return out.toByteArray();
    }
}
