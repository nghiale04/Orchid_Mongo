package com.sum25.orchids.services.Impl;

import com.sum25.orchids.dto.OrchidDto;
import com.sum25.orchids.entity.Categories;
import com.sum25.orchids.entity.Orchids;
import com.sum25.orchids.repository.CategoryrRepository;
import com.sum25.orchids.services.OrchidsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.sum25.orchids.repository.OrchidRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrchidsServiceImpl implements OrchidsService {

    private final OrchidRepository orchidRepository;
    private final CategoryrRepository categoryrRepository;


    @Override
    public List<OrchidDto> getAllOrchids() {
        List<Orchids> orchidsList = orchidRepository.findAll();
        List<OrchidDto> orchidDtoList = new ArrayList<>();
        for (Orchids orchids : orchidsList) {
            OrchidDto orchidDto = orchidEntitytoDto(orchids);
            orchidDtoList.add(orchidDto);
        }
        return orchidDtoList;
    }

    @Override
    public OrchidDto getDetailOrchidById(String orchidId) {
    Orchids orchids = orchidRepository.findById(orchidId).orElseThrow(
            () -> new RuntimeException("Orchid with ID " + orchidId + " not found")
    );
        return orchidEntitytoDto(orchids);
    }

    @Override
    public void createOrchid(OrchidDto orchidDto) {
        try {
            // Decode base64
            File uploadDir = new File("uploads");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String base64Image = orchidDto.getOrchidUrl().split(",")[1]; // Bỏ "data:image/jpeg;base64,"
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            String filename = UUID.randomUUID() + ".jpg";
            Path path = Paths.get("uploads/" + filename);
            Files.write(path, imageBytes);

            String orchidUrl = "http://localhost:8080/images/" + filename;

            Orchids orchid = Orchids.builder()
                    .orchidName(orchidDto.getOrchidName())
                    .orchidDescription(orchidDto.getOrchidDescription())
                    .orchidUrl(orchidUrl)
                    .price(orchidDto.getPrice())
                    .isNatural(orchidDto.getIsNatural())
                    .category(categoryrRepository.findByCategoryName(orchidDto.getOrchidType()))
                    .build();

            orchidRepository.save(orchid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrchid(String orchidId) {
        orchidRepository.deleteById(orchidId);
    }

    @Override
    public void updateOrchid(OrchidDto orchidDto) {
        Orchids orchids = orchidRepository.findById(orchidDto.getId()).get();
        if (orchids == null) {
            throw new RuntimeException("Orchid with ID " + orchidDto.getId() + " not found");
        }

        try {
            // Decode base64
            File uploadDir = new File("uploads");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String base64Image = orchidDto.getOrchidUrl().split(",")[1]; // Bỏ "data:image/jpeg;base64,"
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            String filename = UUID.randomUUID() + ".jpg";
            Path path = Paths.get("uploads/" + filename);
            Files.write(path, imageBytes);

            String orchidUrl = "http://localhost:8080/images/" + filename;

            orchids.setOrchidUrl(orchidUrl);
            orchids.setOrchidName(orchidDto.getOrchidName());
            orchids.setOrchidDescription(orchidDto.getOrchidDescription());
            orchids.setPrice(orchidDto.getPrice());
            orchids.setIsNatural(orchidDto.getIsNatural());
            orchids.setCategory(categoryrRepository.findByCategoryName(orchidDto.getOrchidType()));

            System.out.println(orchids);
            orchidRepository.save(orchids);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public OrchidDto orchidEntitytoDto(Orchids orchids) {
        OrchidDto orchidDto = new OrchidDto().builder()
                .id(orchids.getId())
                .isNatural(orchids.getIsNatural())
                .orchidDescription(orchids.getOrchidDescription())
                .orchidName(orchids.getOrchidName())
                .orchidUrl(orchids.getOrchidUrl())
                .price(orchids.getPrice())
                .orchidType(orchids.getCategory().getCategoryName())
                .build();

        return orchidDto;
    }

    public Orchids orchidDtoToEntity(OrchidDto orchidDto) {
        Orchids orchids = new Orchids();
        orchids.setId(orchidDto.getId());
        orchids.setIsNatural(orchidDto.getIsNatural());
        orchids.setOrchidDescription(orchidDto.getOrchidDescription());
        orchids.setOrchidName(orchidDto.getOrchidName());
        orchids.setOrchidUrl(orchidDto.getOrchidUrl());
        orchids.setPrice(orchidDto.getPrice());
        return orchids;
    }

}
