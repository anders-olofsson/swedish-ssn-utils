package io.olofsson.ssnutil

class SsnChecksumCalculator {

    String getCompleteSsnFrom9FirstDigits(String first9Digits) {
        if (!(first9Digits =~ /\d{9}/)) {
            throw new IllegalArgumentException('Expected a string with 9 digits. Got: ' + first9Digits)
        }
        def checksumDigit = computeChecksumDigitFor9SsnDigits(first9Digits)
        first9Digits + checksumDigit
    }

    int computeChecksumDigitFor9SsnDigits(String first9Digits) {
        int sumOf9Digits = 0
        (0..8).each {
            def weightForIndex = 2 - it % 2
            def currentProduct = Integer.valueOf(first9Digits[it]) * weightForIndex
            sumOf9Digits += (currentProduct / 10) + (currentProduct % 10)
        }
        (10 - (sumOf9Digits % 10)) % 10
    }

}
