package reprexes.mdc;

import brave.baggage.BaggageField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MyBaggageService {

    public static void updateValue(String name, String value) {
        if (value == null) {
            return;
        }
        BaggageField field = BaggageField.getByName(name);
        if (field == null) {
            return;
        }
        field.updateValue(value);
    }

    public static String getValue(String name) {
        BaggageField field = BaggageField.getByName(name);
        if (field == null) {
            return null;
        }
        return field.getValue();
    }

}
