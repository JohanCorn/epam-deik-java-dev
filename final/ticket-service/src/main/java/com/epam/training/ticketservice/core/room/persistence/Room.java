package com.epam.training.ticketservice.core.room.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @Id
    private String name;

    private int rowsNum;

    private int columnsNum;

    public Room(String name, int rowsNum, int columnsNum) {
        this.name = name;
        this.rowsNum = rowsNum;
        this.columnsNum = columnsNum;
    }

    @Override
    public String toString() {
        return "Room " + name + " with " + rowsNum * columnsNum + " seats, " + rowsNum
                + " rows and " + columnsNum + " columns";
    }

}
