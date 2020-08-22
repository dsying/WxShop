package com.kodemamba.wxshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TelVerificationServiceTest {
    private static TelVerificationService telVerificationService = new TelVerificationService();

    @Test
    void returnTrueIfVerify() {
        assertTrue(telVerificationService.verifyTelParameter("15335181657"));
    }

    @Test
    void returnFalseIfNotVerify() {
        assertFalse(telVerificationService.verifyTelParameter("1533518165"));
        assertFalse(telVerificationService.verifyTelParameter("1533518165a"));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String tel) {
        assertFalse(telVerificationService.verifyTelParameter(tel));
    }
}
