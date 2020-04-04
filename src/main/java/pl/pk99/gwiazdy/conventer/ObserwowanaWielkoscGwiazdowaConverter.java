package pl.pk99.gwiazdy.conventer;

import pl.pk99.gwiazdy.ObserwowanaWielkoscGwiazdowa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter (autoApply = true)
public class ObserwowanaWielkoscGwiazdowaConverter implements AttributeConverter
        <ObserwowanaWielkoscGwiazdowa, Double> {


    @Override
    public Double convertToDatabaseColumn(ObserwowanaWielkoscGwiazdowa obserwowanaWielkoscGwiazdowa) {
        if(obserwowanaWielkoscGwiazdowa == null)
            return null;
        return obserwowanaWielkoscGwiazdowa.get();
    }

    @Override
    public ObserwowanaWielkoscGwiazdowa convertToEntityAttribute(Double aDouble) {
        if (aDouble == null)
            return null;
        return new ObserwowanaWielkoscGwiazdowa(aDouble);
    }
}
