package com.example.hotelRoomReservationSystem.controller;

import com.example.hotelRoomReservationSystem.model.Room;
import com.example.hotelRoomReservationSystem.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HotelReservationController {

    @Autowired
    private HotelRoomService hotelRoomService;

@GetMapping("/get")
     public String getParv(){
    return "hellp parv welcome to the spring boot session we will learn system design ";
}
@GetMapping("/getHotel")
    public Map<Integer, List<Room>> getHotels(){
    return hotelRoomService.getHotel();
}
    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms(){
        return hotelRoomService.getAllRooms();
    }

    @PostMapping("/reset")
    public void resstRooms(){
       hotelRoomService.resetRoom();
    }
@PostMapping("/book/{bookingCount}")
    public List<Room> bookRoom(@PathVariable int bookingCount){
    return hotelRoomService.roomBooking(bookingCount);

}
}
