//package seedu.address.model.person;
//
//import seedu.address.commons.util.ToStringBuilder;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.function.Predicate;
//
//public class GenericPredicateList<T> implements Predicate<T> {
//    public final List<Predicate<T>> predicateList;
//
//    public GenericPredicateList(List<Predicate<T>> predicateList) {
//        this.predicateList = predicateList;
//    }
//
//    @Override
//    public boolean test(T item) {
//        return predicateList.stream().allMatch(predicate -> predicate.test(item));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof GenericPredicateList<?>)) {
//            return false;
//        }
//
//        GenericPredicateList<?> otherGenericPredicateList =
//                (GenericPredicateList<?>) other;
//        return predicateList.equals(otherGenericPredicateList.predicateList);
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(predicateList);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).add("predicateList", predicateList).toString();
//    }
//
//}
