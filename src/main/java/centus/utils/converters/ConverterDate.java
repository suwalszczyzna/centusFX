package centus.utils.converters;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConverterDate {
    public static Date convertToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String formatDateToDBQuery(Date date){
        String pattern = "yyyy-MM-dd 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date resetDate(Date date){
        LocalDate ld = ConverterDate.convertToLocalDate(date);
        return ConverterDate.convertToDate(LocalDate.of(ld.getYear(), ld.getMonthValue(), 1));
    }

    public static Date lastDayOfMonth(Date date){
        LocalDate tempDate = ConverterDate.convertToLocalDate(date);
        LocalDate lastDay = LocalDate.of(tempDate.getYear(), tempDate.getMonthValue(), tempDate.getMonth().length(tempDate.isLeapYear()));
        return ConverterDate.convertToDate(lastDay);
    }
}
