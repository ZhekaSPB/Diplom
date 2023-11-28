package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.Year;
import java.util.Locale;

public class DataHelp {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelp() {
    }

    public static String getFirstCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getFirstCardExpectedStatus() {
        return "APPROVED";
    }

    public static String getSecondCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getSecondCardExpectedStatus() {
        return "DECLINED";
    }

    public static String getCardNumberIncomplete() {
        return "4444 4444 4444 444";
    }

    public static String getCardNumberZero() {
        return "0000 0000 0000 0000";
    }

    public static String getCardNumberNotExisting() {
        return "3333 3333 3333 3333";
    }

    public static String getCardNumberOverLimit() {
        return "4444 4444 4444 4441 1";
    }

    public static String getCardNumberUnderLimit() {
        return "4444";
    }

    public static String getValueText() {
        return "тест";
    }

    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getZeroValue() {
        return "00";
    }

    public static String getIncompleteMonth() {
        return "1";
    }

    public static String getValidYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(2));
    }

    public static String getInvalidYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.minusYears(2));
    }

    public static String getDurationOverLimit() {
        return "202";
    }

    public static String getIncompleteYear() {
        return "2";
    }

    public static String getValidOwner() {
        return faker.name().firstName() + " " + faker.name().lastName().replaceAll("[^A-Za-z]", "");
    }

    public static String getUnderLimitOwner() {
        return "V";
    }

    public static String getOverLimitOwner() {
        return "Testtesttesttesttest";
    }

    public static String getInvalidOwner() {
        return "Bla#*?%732";
    }

    public static String getDataWithoutSpaces() {
        return "BorisinPupk";
    }

    public static String getIncompleteOwner() {
        return "Boris";
    }

    public static String getOwnerOnlySurname() {
        return "Pupkin";
    }

    public static String getOwnerLowerCase() {
        return "boris pupkin";
    }

    public static String getOwnerNumbers() {
        return " 12345";
    }

    public static String getValidCvc() {
        return faker.numerify("###");
    }

    public static String getInvalidCvc() {
        return faker.numerify("##");
    }

    public static String getEmptyValue() {
        return "";
    }

    public static String getIncompleteCvc() {
        return "12";
    }

    public static String getZeroCvv() {
        return "000";
    }

    public static String getCvcOverLimit() {
        return "3555";
    }

    public static String getInvalidOwnerRus() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getPastMonth() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getThisYear() {
        return String.format("%ty", Year.now());
    }
}
