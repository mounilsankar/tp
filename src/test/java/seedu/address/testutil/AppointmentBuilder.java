package seedu.address.testutil;

import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.GEORGE_NRIC;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_DOCTOR_NRIC = ALICE_NRIC;
    public static final String DEFAULT_PATIENT_NRIC = GEORGE_NRIC;
    public static final String DEFAULT_START_TIME = "2023-09-11 07:30";
    public static final String DEFAULT_END_TIME = "2023-09-11 08:00";
    private Nric doctorNric;
    private Nric patientNric;
    private AppointmentStartTime startTime;
    private AppointmentEndTime endTime;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        doctorNric = new Nric(DEFAULT_DOCTOR_NRIC);
        patientNric = new Nric(DEFAULT_PATIENT_NRIC);
        startTime = new AppointmentStartTime(DEFAULT_START_TIME);
        endTime = new AppointmentEndTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        doctorNric = appointmentToCopy.getDoctorNric();
        patientNric = appointmentToCopy.getPatientNric();
        startTime = appointmentToCopy.getStartTime();
        endTime = appointmentToCopy.getEndTime();
    }

    /**
     * Sets the {@code DoctorNRIC} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctorNric(String doctorNric) {
        this.doctorNric = new Nric(doctorNric);
        return this;
    }

    /**
     * Sets the {@code PatientNRIC} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientNric(String patientNric) {
        this.patientNric = new Nric(patientNric);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartTime(String startTime) {
        this.startTime = new AppointmentStartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withEndTime(String endTime) {
        this.endTime = new AppointmentEndTime(endTime);
        return this;
    }

    public Appointment build() {
        return new Appointment(doctorNric, patientNric, startTime, endTime);
    }
}