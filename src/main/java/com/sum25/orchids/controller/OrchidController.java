package com.sum25.orchids.controller;

import com.sum25.orchids.dto.OrchidDto;
import com.sum25.orchids.dto.ResponseDTO;
import com.sum25.orchids.services.OrchidsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orchids")
@AllArgsConstructor
public class OrchidController {

    private final OrchidsService orchidsService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllOrchids() {
        List<OrchidDto> orchidDtoList = orchidsService.getAllOrchids();
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .statusCode("200")
                        .message("Orchids fetched successfully")
                        .data(orchidDtoList)
                        .build()
        );
    }

    @GetMapping("/{orchidId}")
    public ResponseEntity<ResponseDTO> getDetailOrchidById(@PathVariable String orchidId) {
        OrchidDto orchidDto = orchidsService.getDetailOrchidById(orchidId);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .statusCode("200")
                        .message("Orchid details fetched successfully")
                        .data(orchidDto)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createOrchid(@RequestBody OrchidDto orchidDto) {
        orchidsService.createOrchid(orchidDto);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .statusCode("201")
                        .message("Orchid created successfully")
                        .data(null)
                        .build()
        );
    }

    @DeleteMapping("/{orchidId}")
    public ResponseEntity<ResponseDTO> deleteOrchid(@PathVariable String orchidId) {
        orchidsService.deleteOrchid(orchidId);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .statusCode("200")
                        .message("Orchid deleted successfully")
                        .build()
        );
    }

    @PutMapping("/{orchidId}")
    public ResponseEntity<ResponseDTO> updateOrchid(@PathVariable String orchidId, @RequestBody OrchidDto orchidDto) {
        orchidDto.setId(orchidId);
        orchidsService.updateOrchid(orchidDto);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .statusCode("200")
                        .message("Orchid updated successfully")
                        .data(null)
                        .build()
        );
    }
}
