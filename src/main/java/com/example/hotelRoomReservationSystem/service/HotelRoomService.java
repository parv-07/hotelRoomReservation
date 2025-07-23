package com.example.hotelRoomReservationSystem.service;

import com.example.hotelRoomReservationSystem.CustomeExceptionHandler.InvalidBookingException;
import com.example.hotelRoomReservationSystem.model.Room;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelRoomService {

    public static Map<Integer, List<Room>> roomMap = new HashMap<>();

    @PostConstruct
    public void superman() {
        try {
            for (int i = 1; i <= 9; i++) {
                List<Room> parv = new ArrayList<>();
                for (int j = 1; j <= 10; j++) {
                    Room room = new Room((i * 100) + j);
                    parv.add(room);
                }
                roomMap.put(i, parv);

            }
            List<Room> roomset = new ArrayList<>();
            for (int i = 1; i <= 7; i++) {
                Room room = new Room((10 * 100) + i);
                roomset.add(room);
                roomMap.put(10, roomset);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public Map<Integer, List<Room>> getHotel() {
        return roomMap;
    }

    public List<Room> getAllRooms() {
        List<Room> all = new ArrayList<>();
        roomMap.values().forEach(all::addAll);
        return all;
    }

    public void resetRoom() {
        getAllRooms().forEach(room -> room.setOccupied(false));
    }

    public List<Room> roomBooking(Integer count) {

        List<List<Room>> allCombination = new ArrayList<>();
        if (count > 5) {
            throw new InvalidBookingException("you cannot book more than 5 rooms");
        }

        for (int floor : roomMap.keySet()) {
            List<Room> available = roomMap.get(floor).stream().filter(r -> !r.isOccupied()).toList();

            if (available.size() >= count) {
                List<Room> sameFloorCombination = findMinimalHorizontalTime(available, count);
                allCombination.add(sameFloorCombination);
            }

        }
        List<Room> availableRooms = getAllRooms().stream().filter(r -> !r.isOccupied()).sorted(Comparator.comparingInt(Room::getFloor).thenComparingInt(Room::getNumber))
                .collect(Collectors.toUnmodifiableList());
        System.out.println("The available rooms are "+availableRooms);
              for (int i = 0; i < availableRooms.size() - count; i++) {
            List<Room> combo = new ArrayList<>();
            combo = availableRooms.subList(i, i + count);
            allCombination.add(combo);
        }
         return allCombination.stream().min(Comparator.comparingInt(this::calculateTravelTime)).map(
                 room ->{
                     room.forEach(r->r.setOccupied(true));
                 return room;}).orElse(List.of());
    }

public int calculateTravelTime(List<Room> rooms){
        int max = rooms.stream().mapToInt(Room::getFloor).max().orElse(0);
        int min = rooms.stream().mapToInt(Room::getFloor).min().orElse(0);
        int verticalTime= (max-min)*2;
        HashMap<Integer,List<Integer>> horizontalmapping = new HashMap<>();

        for(Room room :rooms){
            horizontalmapping.computeIfAbsent(room.getFloor(), f->new ArrayList<>())
                                  .add(room.getRoomIndexOnFloor());
        }
        int horizontalTiming=0;
        for(List<Integer> floor: horizontalmapping.values()){
            int maxTime = Collections.max(floor);
            int minTime= Collections.min(floor);
            
            horizontalTiming = maxTime-minTime;

        }
        return verticalTime+horizontalTiming;
}
    private List<Room> findMinimalHorizontalTime(List<Room> available, int count) {
        int minHorizontalTime = Integer.MAX_VALUE;
        List<Room> best = new ArrayList<>();
        for (int i = 0; i <= available.size() - count; i++) {
            List<Room> countPerRoom = available.subList(i, i + count);
            int time = countPerRoom.get(count - 1).getRoomIndexOnFloor() - countPerRoom.get(0).getRoomIndexOnFloor();
            if (minHorizontalTime > time) {
                minHorizontalTime = time;
                best = new ArrayList<>(countPerRoom);
            }
        }
        return best;
    }

}
