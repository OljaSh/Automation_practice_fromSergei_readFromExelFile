package uk.be2co.preset;

import forall.utils.PropertiesUtils;

import java.util.Optional;

import static forall.utils.PropertiesUtils.getStringValue;

/**
 * Author: Serhii Korol.
 */
public enum URL {
    DEV(getStringValue(PropertiesUtils.Constants.DEV_URL)),
    QA(getStringValue(PropertiesUtils.Constants.QA_URL));

    private String address;

    URL(final String address) {
        this.address = address;
    }

    public String toString() {
        return Optional.ofNullable(System.getenv("URL")).orElse(address);
    }
}
