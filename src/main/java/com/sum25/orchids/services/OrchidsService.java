package com.sum25.orchids.services;

import com.sum25.orchids.dto.OrchidDto;

import java.util.List;

public interface OrchidsService {
    List<OrchidDto> getAllOrchids();
    OrchidDto getDetailOrchidById(String orchidId);
    void createOrchid(OrchidDto orchidDto);
    void deleteOrchid(Long orchidId);
    void updateOrchid(OrchidDto orchidDto);
}
