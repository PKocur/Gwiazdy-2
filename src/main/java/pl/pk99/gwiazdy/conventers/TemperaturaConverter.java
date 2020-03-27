package pl.pk99.gwiazdy.conventers;

import pl.pk99.gwiazdy.Temperatura;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter (autoApply = true)
public class TemperaturaConverter implements AttributeConverter<Temperatura, Double> {

    @Override
    public Double convertToDatabaseColumn(Temperatura temperatura) {
        if(temperatura == null)
            return null;
        return temperatura.get();
    }

    @Override
    public Temperatura convertToEntityAttribute(Double aDouble) {
        if (aDouble == null)
            return null;
        return new Temperatura(aDouble);
    }
}
