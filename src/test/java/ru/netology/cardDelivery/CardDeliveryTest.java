package ru.netology.cardDelivery;

import com.codeborne.selenide.Condition;
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

class CardDeliveryTest {
    public static String setLocalDate(int addDays) {
        String date = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    @Test
    void shouldDeliveryCard() {
        open("http://localhost:9999");

        $("[data-test-id=city] .input__control").setValue("Вологда");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(setLocalDate(3));
        $("[data-test-id=name] .input__control").setValue("Соколов Василий");
        $("[data-test-id=phone] .input__control").setValue("+72557778899");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification] .notification__title").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + setLocalDate(3)));

    }
}