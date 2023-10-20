//package seedu.address.model.person;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.function.Predicate;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.person.patient.Patient;
//import seedu.address.testutil.PatientBuilder;
//
//public class GenericPredicateListTest {
//
//    @Test
//    public void hashcode() {
//        List<Predicate<Patient>> predicateList =
//                List.of(new NameContainsKeywordsPatientPredicate(List.of("A", "Al")),
//                        new NameContainsKeywordsPatientPredicate(List.of("Ali", "Alic")));
//
//        // same value -> return same hashcode
//        GenericPredicateList<Patient> predicate = new GenericPredicateList<Patient>(predicateList);
//        assertEquals(predicate.hashCode(), Objects.hash(predicateList));
//    }
//    @Test
//    public void equals() {
//        List<Predicate<Patient>> firstPredicateList= List.of(new NameContainsKeywordsPatientPredicate(List.of("A", "Al")),
//                new NameContainsKeywordsPatientPredicate(List.of("Ali", "Alic")));
//        List<Predicate<Patient>> secondPredicateList = List.of(new NameContainsKeywordsPatientPredicate(List.of("B", "Be")),
//                new NameContainsKeywordsPatientPredicate(List.of("Ben", "Bens")));
//
//        GenericPredicateList<Patient> firstPredicate =
//                new GenericPredicateList<>(firstPredicateList);
//        GenericPredicateList<Patient> secondPredicate =
//                new GenericPredicateList<>(secondPredicateList);
//
//        // same object -> returns true
//        assertTrue(firstPredicate.equals(firstPredicate));
//
//        // same values -> returns true
//        GenericPredicateList<Patient> firstPredicateCopy =
//                new GenericPredicateList<>(firstPredicateList);
//        assertTrue(firstPredicate.equals(firstPredicateCopy));
//
//        // different types -> returns false
//        assertFalse(firstPredicate.equals(1));
//
//        // null -> returns false
//        assertFalse(firstPredicate.equals(null));
//
//        // different person -> returns false
//        assertFalse(firstPredicate.equals(secondPredicate));
//    }
//
//    @Test
//    public void test_nameContainsKeywords_returnsTrue() {
//        // One predicate in list
//        List<Predicate<Patient>> predicateList = List.of(new NameContainsKeywordsPatientPredicate(List.of("A", "Al")));
//        GenericPredicateList<Patient> predicate = new GenericPredicateList<>(predicateList);
//        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
//
////        // Multiple keywords
////        predicateList= List.of(new NameContainsKeywordsPatientPredicate(List.of("A", "Al")),
////                new NameContainsKeywordsPatientPredicate(List.of("B", "Bob")));
////        predicate = new GenericPredicateList<>(predicateList);
////        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
//    }
////
////    @Test
////    public void test_nameDoesNotContainKeywords_returnsFalse() {
////        // Zero keywords
////        NameContainsKeywordsPatientPredicate predicate =
////                new NameContainsKeywordsPatientPredicate(Collections.emptyList());
////        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));
////
////        // Non-matching keyword
////        predicate = new NameContainsKeywordsPatientPredicate(Arrays.asList("Carol"));
////        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
////
////        // Keywords match phone, but does not match name
////        predicate = new NameContainsKeywordsPatientPredicate(Arrays.asList("12345"));
////        assertFalse(predicate.test(new PatientBuilder().withName("Alice").withPhone("12345").build()));
////    }
////
////    @Test
////    public void toStringMethod() {
////        List<String> keywords = List.of("keyword1", "keyword2");
////        NameContainsKeywordsPatientPredicate predicate = new NameContainsKeywordsPatientPredicate(keywords);
////
////        String expected = NameContainsKeywordsPatientPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
////        assertEquals(expected, predicate.toString());
////    }
//}
