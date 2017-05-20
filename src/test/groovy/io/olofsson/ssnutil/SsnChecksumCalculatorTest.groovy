package io.olofsson.ssnutil

import spock.lang.Specification

class SsnChecksumCalculatorTest extends Specification {
    private SsnChecksumCalculator checksumCalculator = new SsnChecksumCalculator()

    def 'Should compute checksum for 9 given SSN digits'() {
        expect:
        checksumCalculator.computeChecksumDigitFor9SsnDigits('811218987') == 6
    }

    def 'Should complete a 9 digit SSN by adding the checksum digit'() {
        expect:
        checksumCalculator.getCompleteSsnFrom9FirstDigits('811218987') == '8112189876'
    }

    def 'Should get angry for invalid parameter values'() {
        when:
        checksumCalculator.getCompleteSsnFrom9FirstDigits('84')

        then:
        thrown(IllegalArgumentException)
    }

}
