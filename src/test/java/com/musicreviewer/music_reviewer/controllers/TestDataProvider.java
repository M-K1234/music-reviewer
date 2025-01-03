package com.musicreviewer.music_reviewer.controllers;

import java.util.stream.Stream;

public class TestDataProvider {

    public static Stream<String> invalidEmailAddresses() {
        return Stream.of(INVALID_EMAIL_TEST_CASES);
    }

    public static Stream<String> validEmailAddresses() {
        return Stream.of(VALID_EMAIL_TEST_CASES);
    }

    public static Stream<String> invalidFullNames() {
        return Stream.of(INVALID_FULL_NAME_TEST_CASES);
    }

    private static final String[] INVALID_EMAIL_TEST_CASES = {
            "plainaddress",
            "@noLocalPart.com",
            "Outlook Contact <outlook-contact@domain.com>",
            "no-at.domain.com",
            //"no.tld@domain",
            ";beginning-semicolon@domain.co.uk",
            "middle-semicolon@domain.co;uk",
            "trailing-semicolon@domain.com;",
            "\"email+leading-quotes@domain.com",
            "email+middle\"-quotes@domain.com",
            //"\"quoted-local-part\"@domain.com",
            "lots-of-dots@domain..com",
            "two-dots..in-local@domain.com",
            "multiple@domains@domain.com",
            "spaces in local@domain.com",
            "spaces-in-domain@dom ain.com",
            //"underscores-in-domain@dom_ain.com",
            //"pipe-in-domain@example.com|gov.uk",
            "comma,in-local@domain.com",
            "comma-in-domain@domain,com",
            "pound-sign-in-local#domain.com",
            //"local-with-’-apostrophe@domain.com",
            //"local-with-”-quotes@domain.com",
            //"invalid-characters-in-domain@domain!.com",
            //"invalid-characters-in-local@domain.com!",
            "missing-username@.com",
            "missing-domain@domain.",
            "missing-at-sign.com",
            "missing-local-and-at@.com",
            //"missing-tld@domain",
            "invalid@domain,com",
            "invalid@domain..com",
            "invalid@-domain.com",
            "invalid@domain-.com",
            //"invalid@domain.c",
            //"invalid@domain.toolongtld",
            "invalid@.domain.com"};

    private static final String[] VALID_EMAIL_TEST_CASES = {
            "simple@example.com",               // Basic ascii
            "very.common@example.com",          // Local part with period
            "!#$%&'*+/=?^_`{|}~@example.org",   // Local part with special characters
            "alice+tag@example.co.uk",
            "kittycat@sub-domain.co.uk",        // Hyphenated domain
            "bob_123@example.net",
            "email-with-dash@sub.domain.com",
            "quoted.name@domain.io",
            "mixedCASE@DOMAIN.COM",
            "d@k.dk"
    };

    private static final String[] INVALID_FULL_NAME_TEST_CASES = {
            "@Jane Doe",        // @ at start
            "Jane@ Doe",        // @ in middle
            "Jane Doe#",        // # at end
            "Jane! Doe",        // ! in middle
            "Jane,Doe",         // , comma
            "Jane Doe;",        // ; semicolon
            "Jane:Doe",         // : colon
            "Jane?Doe",         // ? question mark
            "Jane=Doe",         // = equal sign
            "Jane%Doe",         // % percent
            "Jane^Doe",         // ^ caret
            "Jane&Doe",         // & ampersand
            "Jane*Doe",         // * asterisk
            "Jane(Doe)",        // () parentheses
            "Jane+Doe",         // + plus
            "Jane{Doe}",        // {} curly braces
            "Jane[Doe]",        // [] square brackets
            "Jane|Doe",         // | pipe
            "Jane\\Doe",        // \ backslash
            "Jane<Doe>",        // <> angle brackets
            "Jane~Doe",         // ~ tilde
            "Jane`Doe",         // ` backtick
            "Jane\"Doe\"",      // " double quotes
    };
}
