package ru.netology.cardDelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryCalendarTestTwo {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldDeliveryCardInteractingWithComplexElements () {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Во");
        $(Selectors.byText("Вологда")).click();
        $("[data-test-id=date] input").click();
        int defaultAddedDays = 3;                                     //количество дней после даты по умолчанию
        int dayToAdd = 7;
        $("[data-test-id='date'] input").click();
        if (!generateDate(defaultAddedDays, "MM").equals(generateDate(dayToAdd, "MM"))) {
            $("[data-step='1']").click();
        }
        $$(".calendar__day").findBy(text(generateDate(dayToAdd, "d"))).click();

        $("[data-test-id='name'] input").setValue("Соколов Василий");
        $("[data-test-id='phone'] input").setValue("+72557778899");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + generateDate(dayToAdd, "dd.MM.yyyy")));


    }
}