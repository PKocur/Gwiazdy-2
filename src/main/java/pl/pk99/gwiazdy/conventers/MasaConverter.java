package pl.pk99.gwiazdy.conventers;

import pl.pk99.gwiazdy.Masa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter (autoApply = true)
public class MasaConverter implements AttributeConverter<Masa, Double> {

    @Override
    public Double convertToDatabaseColumn(Masa masa) {
        if(masa == null)
            return null;
        return masa.get();
    }

    @Override
    public Masa convertToEntityAttribute(Double aDouble) {
        if (aDouble == null)
            return null;
        return new Masa(aDouble);
    }
}
