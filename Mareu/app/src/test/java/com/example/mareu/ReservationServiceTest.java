package com.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.mareu.di.DI;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.model.Reservation;
import com.example.mareu.service.DummyMeetingRoomGenerator;
import com.example.mareu.service.ReservationApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
public class ReservationServiceTest {

    private ReservationApiService service;
    private String subject;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        subject = "Test";
    }

    @Test
    public void getUserNameWithSuccess(){
        String s = service.getUserName();
        assertEquals(s,"Xetiam@gmail.com");
    }

    @Test
    public void getReservationsWithSuccess() {
        ArrayList<Reservation> reservations = service.getReservation();
        ArrayList<Reservation> expectedReservations = new ArrayList<>();
        Reservation r1 = new Reservation(0, Calendar.getInstance(), new ArrayList<>(), "test1", getRandomColor(), Calendar.getInstance(), subject);
        service.createMeeting(r1);
        expectedReservations.add(r1);
        Reservation r2 = new Reservation(1, Calendar.getInstance(), new ArrayList<>(), "test2", getRandomColor(), Calendar.getInstance(), subject);
        service.createMeeting(r2);
        expectedReservations.add(r2);
        Reservation r3 = new Reservation(2, Calendar.getInstance(), new ArrayList<>(), "test3", getRandomColor(), Calendar.getInstance(), subject);
        service.createMeeting(r3);
        expectedReservations.add(r3);
        assertThat(reservations, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedReservations.toArray()));
    }

    @Test
    public void getMeetingRoomWithSuccess() {
        ArrayList<MeetingRoom> meetingRooms = service.getMeetingRooms();
        ArrayList<MeetingRoom> expectedMeetingRooms = DummyMeetingRoomGenerator.generateMeetingRooms();
        assertEquals(meetingRooms, expectedMeetingRooms);
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Reservation r1 = new Reservation(0, Calendar.getInstance(), new ArrayList<>(), "test1", getRandomColor(), Calendar.getInstance(), subject);
        Reservation r2 = new Reservation(1, Calendar.getInstance(), new ArrayList<>(), "test2", getRandomColor(), Calendar.getInstance(), subject);
        Reservation r3 = new Reservation(2, Calendar.getInstance(), new ArrayList<>(), "test3", getRandomColor(), Calendar.getInstance(), subject);
        service.createMeeting(r1);
        service.createMeeting(r2);
        service.createMeeting(r3);
        Reservation reservationToDelete = service.getReservation().get(0);
        service.deleteMeeting(reservationToDelete);
        assertFalse(service.getReservation().contains(reservationToDelete));
    }

    @Test
    public void CreateNeighbourWithSuccess() {
        Reservation reservationToCreate = new Reservation(0, Calendar.getInstance(), new ArrayList<>(), "test1", getRandomColor(), Calendar.getInstance(), subject);
        service.createMeeting(reservationToCreate);
        assertTrue(service.getReservation().contains(reservationToCreate));
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return rnd.nextInt(256);
    }
}
