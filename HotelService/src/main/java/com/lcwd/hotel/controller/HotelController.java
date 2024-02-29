package com.lcwd.hotel.controller;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping()
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {

        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelId(@PathVariable String hotelId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.get(hotelId));
    }

    @GetMapping()
    public ResponseEntity<List<Hotel>> getAll() {

        return ResponseEntity.ok(hotelService.getAll());
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel) {
        Hotel updated = hotelService.updateHotel(hotelId, hotel);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.noContent().build();
    }
}
