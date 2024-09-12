package com.springJWT.yop.Persistens;

import com.springJWT.yop.Persistens.Permisos;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class PermisosConverter implements AttributeConverter<Set<Permisos>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Permisos> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<Permisos> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Set.of();
        }
        return Arrays.stream(dbData.split(","))
                .map(Permisos::valueOf)
                .collect(Collectors.toSet());
    }
}
