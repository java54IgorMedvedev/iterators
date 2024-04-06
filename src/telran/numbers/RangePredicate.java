package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range {
    private Predicate<Integer> predicate = n -> true; // Инициализируем предикат значением по умолчанию, которое принимает все числа.

    protected RangePredicate(int min, int max) {
        super(min, max);
    }

    public void setPredicate(Predicate<Integer> predicate) {
        this.predicate = predicate; // Исправляем здесь на правильное присваивание
    }

    public static RangePredicate getRange(int min, int max) {
        checkMinMax(min, max);
        return new RangePredicate(min, max);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangePredicateIterator();
    }

    private class RangePredicateIterator implements Iterator<Integer> {
        private int current = min;

        @Override
        public boolean hasNext() {
            while (current <= max && !predicate.test(current)) {
                current++;
            }
            return current <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return current++;
        }
    }
}
