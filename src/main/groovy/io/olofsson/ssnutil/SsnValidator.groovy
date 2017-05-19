package io.olofsson.ssnutil

class SsnValidator {

    private static final SsnNormalizer SSN_NORMALIZER = new SsnNormalizer()
    private static final SsnChecksumCalculator CHECKSUM_CALCULATOR = new SsnChecksumCalculator()

    boolean isValidSsn(String ssn) {
        def digits = SSN_NORMALIZER.normalizeTo10Digits(ssn)
        def actualChecksumDigit = digits[9]
        def expectedChecksumDigit = CHECKSUM_CALCULATOR.computeChecksumDigitFor9SsnDigits(digits)
        Integer.valueOf(actualChecksumDigit) == expectedChecksumDigit
    }

}
