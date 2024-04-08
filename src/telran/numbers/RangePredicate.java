package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range{
	private Predicate<Integer> predicate;
	protected RangePredicate(int min, int max) {
		super(min, max);
		
	}
	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
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
		long current = min - 1;
		RangePredicateIterator(){
			if(predicate == null) {
				predicate = x -> true;
			}
			setCurrent();
		}
		@Override
		public boolean hasNext() {
			
			return current <= max;
		}

		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			int result = (int)current;
			setCurrent();
			return result;
		}

		private void setCurrent() {
			current++;
			while(current <= max && !predicate.test((int) current)) {
				current++;
			}
			
		}
		
		
	}

}