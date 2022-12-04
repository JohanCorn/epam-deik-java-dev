package com.epam.training.ticketservice.core.screening.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.room.persistence.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Screening {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_title")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_name")
    private Room room;

    private Date start;

    public Screening(Movie movie, Room room, Date start) {
        this.movie = movie;
        this.room = room;
        this.start = start;
    }

    public Date getEnd() {
        return new Date(start.getTime() + movie.getDuration() * 60_000L);
    }

    @Override
    public String toString() {
        return movie + ", screened in room " + room.getName() + ", at " + new SimpleDateFormat(Screening.getDateFormat()).format(start);
    }

    public static String getDateFormat() {
        return "yyyy-MM-dd hh:mm";
    }

    public boolean isOverlapping(Screening anotherScreening) {
        return !(getStart().before(anotherScreening.getStart()) && getEnd().after(anotherScreening.getEnd()));
    }

}
