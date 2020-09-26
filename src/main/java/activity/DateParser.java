package main.java.activity;

import java.time.LocalDateTime;

public class DateParser {

    /**
     * Functions converts a standard LocalDateTime object into DD Month YYYY, 12 hour time format
     *
     * @param when LocalDateTime object containing the date and time of the particular event
     * @return String containing the date and time in DD Month YYYY, 12 hour format.
     */
    public String convertToLongDateTime(LocalDateTime when) {
        int dateNumber = when.getDayOfMonth();
        int monthNumber = when.getMonthValue();
        int yearNumber = when.getYear();

        int hourNumber = when.getHour();
        int minNumber = when.getMinute();
        int secNumber = when.getSecond();



        return convertToLongDate(yearNumber, monthNumber, dateNumber) + " " + convert24To12(hourNumber, minNumber, secNumber);
    }

    private String convertToLongDate(int year, int month, int date) {
        String yearString;
        String monthString;
        String dateString;

        yearString = Integer.toString(year);
        dateString = Integer.toString(date);
        if (date < 10) { //adds the zero at the back to make the date have two digits
            dateString = "0" + dateString;
        }

        switch (month) {
        case 1:
            monthString = "Jan";
            break;
        case 2:
            monthString = "Feb";
            break;
        case 3:
            monthString = "Mar";
            break;
        case 4:
            monthString = "Apr";
            break;
        case 5:
            monthString = "May";
            break;
        case 6:
            monthString = "Jun";
            break;
        case 7:
            monthString = "Jul";
            break;
        case 8:
            monthString = "Aug";
            break;
        case 9:
            monthString = "Sep";
            break;
        case 10:
            monthString = "Oct";
            break;
        case 11:
            monthString = "Nov";
            break;
        default:
            monthString = "Dec";
            break;

        }

        return dateString + " " + monthString + " " + yearString;

    }

    private String convert24To12(int hour, int min, int sec) {
        String timePostFix;
        String hourString;
        String minuteString;
        String secondString;

        if (hour == 0) { //midnight
            hourString = Integer.toString(12);
            timePostFix = "a.m";

        } else if (hour == 12) { //noon
            hourString = Integer.toString(12);
            timePostFix = "p.m";

        } else if (hour <= 11) {
                timePostFix = "a.m";
                hourString = Integer.toString(hour);

        } else {
                timePostFix = "p.m";
                hourString = Integer.toString(hour-12);
        }

        minuteString = Integer.toString(min);
        secondString = Integer.toString(sec);

        if ((hour < 22 && hour > 12) || (hour < 10 && hour > 0)) {
            hourString = "0" + hourString;
        }
        if (min < 10) {
            minuteString = "0" + minuteString;
        }
        if (sec < 10) {
            secondString = "0" + secondString;
        }


        return hourString + ":" + minuteString + ":" + secondString + " " + timePostFix;
    }

}
