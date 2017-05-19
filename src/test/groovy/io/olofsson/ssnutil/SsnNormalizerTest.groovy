package io.olofsson.ssnutil

import spock.lang.Specification
import spock.lang.Unroll

class SsnNormalizerTest extends Specification {

    SsnNormalizer normalizer = new SsnNormalizer()

    def 'Dashes should be removed'() {
        expect:
        normalizer.normalizeTo10Digits('830303-1212') == '8303031212'
        normalizer.normalizeTo12Digits('19830303-1212') == '198303031212'
    }

    def 'Century digits should be added when missing when using normalizeTo12Digits'() {
        expect:
        normalizer.normalizeTo12Digits('830303-1212') == '198303031212'
        normalizer.normalizeTo12Digits('030303-1212') == '200303031212'
        normalizer.normalizeTo12Digits('160303-1212') == '201603031212'
        normalizer.normalizeTo12Digits('200303-1212') == '192003031212'
    }

    def 'Century digits should be trimmed when using normalizeTo10Digits'() {
        expect:
        normalizer.normalizeTo10Digits('19830303-1212') == '8303031212'
        normalizer.normalizeTo10Digits('20030303-1212') == '0303031212'
        normalizer.normalizeTo10Digits('20160303-1212') == '1603031212'
        normalizer.normalizeTo10Digits('19200303-1212') == '2003031212'
    }

    @Unroll
    "Ssn #invalidSsn which should throw exception for reason: #reason"() {
        when:
        normalizer.normalizeTo10Digits(invalidSsn)

        then:
        IllegalArgumentException e = thrown()
        e.message == 'Invalid SSN format, expected 10 or 12 digits with an optional dash. Got: ' + invalidSsn

        where:
        invalidSsn       | reason
        '850505-112'     | 'Missing last digit'
        '85-0505-1124'   | 'Too many dashes'
        '19850505-112'   | 'Missing last digit'
        '198505-05-1123' | 'Too many dashes'
        null             | 'Null is invalid'
    }

}