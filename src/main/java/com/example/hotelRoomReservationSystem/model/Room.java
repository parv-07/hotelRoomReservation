package com.example.hotelRoomReservationSystem.model;
public class Room {
    Integer number;
    boolean occupied;

    public Room(int number){
        this.number=number;
        this.occupied=false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getFloor(){
        return number/10;

    }
    public Integer getRoomIndexOnFloor(){
        return number%100 -1;
    }
    @Override
    public String toString(){
return "Room" + this.number +" " + "Occupied" + this.occupied;
    }



}
