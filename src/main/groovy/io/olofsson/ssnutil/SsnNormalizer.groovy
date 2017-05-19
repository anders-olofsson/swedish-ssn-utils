package io.olofsson.ssnutil

import java.time.LocalDate

class SsnNormalizer {

    String normalizeTo10Digits(String ssn) {
        requireCorrectAmountOfDigits(ssn)
        String result = removeDashes(ssn)
        result.length() == 10 ? result : result.substring(2)
    }

    String normalizeTo12Digits(String ssn) {
        requireCorrectAmountOfDigits(ssn)
        String result = removeDashes(ssn)
        result.length() == 12 ? result : addCenturyDigits(result)
    }

    private String removeDashes(String ssn) {
        String result = ssn.replace('-', '')
        result
    }

    private void requireCorrectAmountOfDigits(String ssn) {
        if (!(ssn =~ /(\d{6}|\d{8})-?\d{4}/)) {
            throw new IllegalArgumentException('Invalid SSN format, expected 10 or 12 digits with an optional dash. Got: ' + ssn)
        }
    }

    static addCenturyDigits(String ssn) {
        def yearDigits = Integer.parseInt(ssn.substring(0, 2))
        def currentYear = LocalDate.now().getYear() % 100
        yearDigits > currentYear ? '19' + ssn : '20' + ssn
    }

}
