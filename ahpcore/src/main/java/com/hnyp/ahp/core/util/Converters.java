package com.hnyp.ahp.core.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;

public class Converters {

    public static <S, T> List<T> convertAll(List<S> sourceItems, Converter<S, T> converter) {
        if (sourceItems != null) {
            return sourceItems.stream().map(converter::convert).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    
}
