package com.epam.training.ticketservice.core.room.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomTest {

    private static final Room underTest = new Room("The Room", 12, 10);

    @Test
    void testToStringShouldReturnTheFormattedData() {
        // Given
        // When
        int rowsNum = underTest.getRowsNum();
        int columnsNum = underTest.getColumnsNum();
        // Then
        Assertions.assertEquals("Room " + underTest.getName() + " with " + rowsNum * columnsNum + " seats, " + rowsNum
                + " rows and " + columnsNum + " columns", underTest.toString());
    }

}
