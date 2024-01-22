package reprexes.mdc;

import brave.baggage.BaggageField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyBaggageService {

    private final ApplicationContext ctx;

    public void updateValue(String name, String value) {
        if (value != null) {
            BaggageField field = ctx.getBean(name, BaggageField.class);
            field.updateValue(value);
        }
    }

    public String getValue(String name) {
        BaggageField field = ctx.getBean(name, BaggageField.class);
        return field.getValue();
    }

}
