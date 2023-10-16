package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentEndTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentEndTime(null));
    }

    @Test
    public void constructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentEndTime(invalidName));
    }

    @Test
    public void isValidAppointmentEndTime() {
        // null name
        assertThrows(NullPointerException.class, () -> AppointmentEndTime.isValidAppointmentTime(null));

        // invalid name
        assertFalse(AppointmentEndTime.isValidAppointmentTime("")); // empty string
        assertFalse(AppointmentEndTime.isValidAppointmentTime(" ")); // spaces only
        assertFalse(AppointmentEndTime.isValidAppointmentTime("^*$")); // not numeric characters
        assertFalse(AppointmentEndTime.isValidAppointmentTime("12-01-2023 07:30")); // date in wrong format

        // valid name
        assertTrue(AppointmentEndTime.isValidAppointmentTime("2023-09-11 08:00")); // in yyyy-dd-mm HH:mm format
        assertTrue(AppointmentEndTime.isValidAppointmentTime("2023-09-11 16:00")); // in yyyy-dd-mm HH:mm 24h format
    }

    @Test
    public void equals() {
        AppointmentEndTime appointmentEndTime = new AppointmentEndTime("2023-09-11 16:00");

        // same values -> returns true
        assertTrue(appointmentEndTime.equals(new AppointmentEndTime("2023-09-11 16:00")));

        // same object -> returns true
        assertTrue(appointmentEndTime.equals(appointmentEndTime));

        // null -> returns false
        assertFalse(appointmentEndTime.equals(null));

        // different types -> returns false
        assertFalse(appointmentEndTime.equals(5.0f));

        // different values -> returns false
        assertFalse(appointmentEndTime.equals(new AppointmentEndTime("2023-09-11 04:00")));
    }
}

