package io.olofsson.ssnutil

import spock.lang.Specification

class SsnChecksumCalculatorTest extends Specification {
    private SsnChecksumCalculator checksumCalculator = new SsnChecksumCalculator()

    def 'Should compute checksum for 9 given SSN digits'() {
        expect:
        checksumCalculator.computeChecksumDigitFor9SsnDigits('811218987') == 6
    }
}
