package rental;

public class RentalPriceCalculator {

	private static final int AGENOTALLOWEDTODRIVE = 17;
	private static final int AGEALLOWEDTORENTCLASSONECAR = 21;
	private static final int MAXRENTALPRICEPERDAY = 1000;

	/**
	 * Calculate price for 24 hours of car rental.
	 * @param age age of the driver
	 * @param licence number of full years person holds driving licence
	 * @param clazz class of the car from 1 (smallest) to 5 (largest) that person wishes to rent
	 * @param acc has driver caused any accidents within last year
	 * @param season if it is high season or nor
	 * @return price for 24 hours of car rental
	 */
	public double priceForTwentyFourHoursRent(int age, int licence, int clazz, boolean acc, boolean season) {
		
		if (isYoungerThanAllowed(age, AGENOTALLOWEDTODRIVE)) {
			throw new IllegalArgumentException("Driver too young - cannot quote the priceForTwentyFourHoursRent");
		}
		if (isYoungerThanAllowed(age, AGEALLOWEDTORENTCLASSONECAR) && clazz > 2) {
			throw new UnsupportedOperationException("Drivers 21 y/o or less can only rent Class 1 vehicles");
		}

		return calculateRentalPrice(age, season, acc, clazz, licence);
	}


	private boolean canNotRentCar(int licence) {
		return licence < 1;
	}

	private boolean licenceLessThanThreeYears(int licence) {
		return licence < 3;
	}


	private boolean isYoungerThanAllowed(int age, int allowance) {
		return age <= allowance;
	}


	private double calculateRentalPrice(int age, boolean season, boolean acc, int clazz, int licence) {

		double rentalPrice = age;

		if (clazz >=4 && isYoungerThanAllowed(age, 25) && season) {
			rentalPrice = rentalPrice * 2;
		}

		if (canNotRentCar(licence)) {
			throw new IllegalArgumentException("Driver must hold driving licence at least for one year. Can not rent a car!");
		}

		if (licenceLessThanThreeYears(licence)) {
			rentalPrice *= 1.3;
		}

		if (acc && isYoungerThanAllowed(age, 30)) {
			rentalPrice += 15;
		}

		if (rentalPrice > MAXRENTALPRICEPERDAY) {
			return 1000.00;
		}

		return rentalPrice;
	}
}