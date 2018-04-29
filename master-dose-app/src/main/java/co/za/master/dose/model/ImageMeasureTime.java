package co.za.master.dose.model;

public enum ImageMeasureTime {
		
		FIRST_MEASUREMENT_TIME(6),
		SECOND_MEASUREMENT_TIME(12),
		THIRD_MEASUREMENT_TIME(24)
		;
		private final int measureTime;

		ImageMeasureTime(int measureTime) {
			this.measureTime = measureTime;
		}

		public int getMeasureTime() {
			return measureTime;
		}

}
