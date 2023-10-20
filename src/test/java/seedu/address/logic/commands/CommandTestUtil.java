package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Database;
import seedu.address.model.Model;
import seedu.address.model.NewModel;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEqualDoctorNricPredicate;
import seedu.address.model.person.NameContainsKeywordsPatientPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NRIC_AMY = "T0123456A";
    public static final String VALID_NRIC_BOB = "S9987654Z";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_PATIENT_NRIC = "S9348573F";
    public static final String VALID_DOCTOR_NRIC = "T0123456J";
    public static final String VALID_APPOINTMENT_START_TIME = "2023-12-01 07:30";
    public static final String VALID_APPOINTMENT_END_TIME = "2023-12-01 08:00";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NRIC_DESC_AMY = " " + PREFIX_NRIC + VALID_NRIC_AMY;
    public static final String NRIC_DESC_BOB = " " + PREFIX_NRIC + VALID_NRIC_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String APPOINTMENT_START_TIME_DESC = " "
            + PREFIX_APPOINTMENT_START_TIME
            + VALID_APPOINTMENT_START_TIME;
    public static final String APPOINTMENT_END_TIME_DESC = " "
            + PREFIX_APPOINTMENT_END_TIME
            + VALID_APPOINTMENT_END_TIME;
    public static final String PATIENT_NRIC_DESC = " " + PREFIX_PATIENT_NRIC + VALID_PATIENT_NRIC;
    public static final String DOCTOR_NRIC_DESC = " " + PREFIX_DOCTOR_NRIC + VALID_DOCTOR_NRIC;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_NRIC_DESC = " " + PREFIX_NRIC + "T012#456A"; // '#' not allowed in nric
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_NRIC = "S9348573FF"; // invalid number of digits
    public static final String INVALID_APPOINTMENT_START_TIME_DESC = " "
            + PREFIX_APPOINTMENT_START_TIME
            + "11-09-2023 07:30"; // invalid format for date
    public static final String INVALID_APPOINTMENT_END_TIME_DESC = " "
            + PREFIX_APPOINTMENT_END_TIME
            + "11-09-2023 07:30"; // invalid format for date
    public static final String INVALID_PATIENT_NRIC_DESC = " " + PREFIX_PATIENT_NRIC + INVALID_NRIC;
    public static final String INVALID_DOCTOR_NRIC_DESC = " " + PREFIX_DOCTOR_NRIC + INVALID_NRIC;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final EditPatientCommand.EditPatientDescriptor PATIENT_DESC_AMY;
    public static final EditPatientCommand.EditPatientDescriptor PATIENT_DESC_BOB;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_APPOINTMENT_1;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_APPOINTMENT_2;
    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        PATIENT_DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withNric(VALID_NRIC_AMY).build();
        PATIENT_DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withNric(VALID_NRIC_BOB).build();
        DESC_APPOINTMENT_1 = new EditAppointmentDescriptorBuilder()
                .withPatientNric(VALID_NRIC_AMY)
                .withDoctorNric(VALID_NRIC_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME)
                .withEndTime(VALID_APPOINTMENT_END_TIME)
                .build();
        DESC_APPOINTMENT_2 = new EditAppointmentDescriptorBuilder()
                .withPatientNric(VALID_NRIC_BOB)
                .withDoctorNric(VALID_NRIC_AMY)
                .withStartTime(VALID_APPOINTMENT_START_TIME)
                .withEndTime(VALID_APPOINTMENT_END_TIME)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertNewCommandSuccess(NewCommand command, NewModel actualModel,
                                               CommandResult expectedCommandResult,
                                               NewModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertNewCommandSuccess(NewCommand, NewModel, CommandResult, NewModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertNewCommandSuccess(NewCommand newCommand, NewModel actualModel, String expectedMessage,
                                            NewModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertNewCommandSuccess(newCommand, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code newcommand}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertNewCommandFailure(NewCommand newCommand, NewModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Database expectedAddressBook = new Database(actualModel.getDatabase());
        List<Appointment> expectedFilteredAppointmentList = new ArrayList<>(actualModel.getFilteredAppointmentList());
        List<Patient> expectedFilteredPatientList = new ArrayList<>(actualModel.getFilteredPatientList());
        List<Doctor> expectedFilteredDoctorList = new ArrayList<>(actualModel.getFilteredDoctorList());

        assertThrows(CommandException.class, expectedMessage, () -> newCommand.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getDatabase());
        assertEquals(expectedFilteredAppointmentList, actualModel.getFilteredAppointmentList());
        assertEquals(expectedFilteredPatientList, actualModel.getFilteredPatientList());
        assertEquals(expectedFilteredDoctorList, actualModel.getFilteredDoctorList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the Patient at the given {@code targetIndex} in the
     * {@code model}'s database.
     */
    public static void showPatientAtIndex(NewModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPatientPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the Appointment at the given {@code targetIndex} in the
     * {@code model}'s database.
     */
    public static void showAppointmentAtIndex(NewModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        final Nric doctorNric = appointment.getDoctorNric();
        model.updateFilteredAppointmentList(new AppointmentEqualDoctorNricPredicate(doctorNric));
        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
