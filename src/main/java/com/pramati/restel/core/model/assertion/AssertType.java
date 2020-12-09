package com.pramati.restel.core.model.assertion;

import com.pramati.restel.exception.RestelException;
import org.apache.commons.lang3.StringUtils;

public enum AssertType {
    EQUAL, GREATER, LESSER, NULL, NOT_NULL, NOT_EQUAL, TRUE, FALSE;

    public static AssertType getType(String type) {
        for (AssertType ty : AssertType.values()) {
            if (StringUtils.equalsIgnoreCase(type, ty.toString())) {
                return ty;
            }
        }
        throw new RestelException("Invalid AssertType defined:" + type);
    }
}
