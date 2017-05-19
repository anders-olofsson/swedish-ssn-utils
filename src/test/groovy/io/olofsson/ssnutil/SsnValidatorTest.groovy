package io.olofsson.ssnutil

import spock.lang.Specification

class SsnValidatorTest extends Specification {

    private SsnValidator validator = new SsnValidator()

    def 'Should validate checksum and return true for a valid SSN'() {
        expect:
        validator.isValidSsn('19811111-1111')
        !validator.isValidSsn('19811111-1112')
        validator.isValidSsn('8112189876')
        !validator.isValidSsn('8112189875')
    }

    def 'Should also support organization numbers for swedish Aktiebolag'() {
        expect:
        validator.isValidSsn('556999-9989')
    }
}
