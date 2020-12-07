package io.github.abeelfelipe.Vendas.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter {

    public BigDecimal converter (String value) {
        if(value == null) {
            return new BigDecimal("0");
        }

        value = value.replace(".", "").replace(",",".");
        return new BigDecimal(value);
    }
}
