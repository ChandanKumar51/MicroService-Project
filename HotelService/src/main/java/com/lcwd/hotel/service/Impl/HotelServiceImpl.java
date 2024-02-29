package com.lcwd.hotel.service.Impl;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.exception.ResourceNotFoundException;
import com.lcwd.hotel.repository.HotelRepository;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel savehotel = hotelRepository.save(hotel);
        return savehotel;
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> allHotel = hotelRepository.findAll();
        return allHotel;
    }

    @Override
    public Hotel get(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found !!"));
        return hotel;
    }

    @Override
    public void deleteHotel(String id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel updateHotel(String id, Hotel hotel) {
        return hotelRepository.findById(id)
                .map(existingHotel -> {
                    existingHotel.setName(hotel.getName());
                    existingHotel.setLocation(hotel.getLocation());
                    existingHotel.setAbout(hotel.getAbout());

                    return hotelRepository.save(existingHotel);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " not found"));
    }
}
