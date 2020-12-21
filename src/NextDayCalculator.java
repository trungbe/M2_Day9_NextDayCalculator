public class NextDayCalculator {

    public static final int FIRST_OF_MONTH = 1;
    public static final int LAST_OF_YEAR = 12;


    public static String nextDayCalculator(int dayTest, int monthTest, int yearTest) {
        int LAST_OF_MONTH = getLast_of_month(monthTest, yearTest);
        GetCheckLeapYear getCheckLeapYear = new GetCheckLeapYear(dayTest, monthTest, yearTest, LAST_OF_MONTH).invoke();
        dayTest = getCheckLeapYear.getDayTest();
        monthTest = getCheckLeapYear.getMonthTest();
        yearTest = getCheckLeapYear.getYearTest();

        String result = dayTest + "-" + monthTest + "-" + yearTest;
        return result;
    }

    private static int getLast_of_month(int monthTest, int yearTest) {
        int LAST_OF_MONTH = 0;
        switch (monthTest) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                LAST_OF_MONTH = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                LAST_OF_MONTH = 30;
                break;
            case 2:
                LAST_OF_MONTH = getCheck_Day_of_Leap_Year(yearTest);
                break;
        }

        return LAST_OF_MONTH;
    }

    private static int getCheck_Day_of_Leap_Year(int yearTest) {
        int LAST_OF_MONTH;
        if (isLeapYear(yearTest)) {
            LAST_OF_MONTH = 29;
        } else {
            LAST_OF_MONTH = 28;
        }
        return LAST_OF_MONTH;
    }

    private static boolean isLeapYear(int yearTest) {
        boolean isDivideBy4 = yearTest % 4 == 0;
        boolean isDivideBy100 = yearTest % 100 == 0;
        boolean isDivideBy400 = yearTest % 400 == 0;
        if (isDivideBy400) {
            return true;
        }
        if (isDivideBy4 && !isDivideBy100) {
            return true;
        }
        return false;
    }


    private static class GetCheckLeapYear {
        private int dayTest;
        private int monthTest;
        private int yearTest;
        private int last_of_month;

        public GetCheckLeapYear(int dayTest, int monthTest, int yearTest, int LAST_OF_MONTH) {
            this.dayTest = dayTest;
            this.monthTest = monthTest;
            this.yearTest = yearTest;
            last_of_month = LAST_OF_MONTH;
        }

        public int getDayTest() {
            return dayTest;
        }

        public int getMonthTest() {
            return monthTest;
        }

        public int getYearTest() {
            return yearTest;
        }

        public GetCheckLeapYear invoke() {
            if (dayTest == last_of_month) {
                dayTest = FIRST_OF_MONTH;
                if (monthTest == LAST_OF_YEAR) {
                    monthTest = FIRST_OF_MONTH;
                    yearTest++;
                } else monthTest++;
            } else dayTest++;
            return this;
        }
    }
}
