package reprexes.mdc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mdc")
@RequiredArgsConstructor
public class MyController {

    private final MyBusinessService businessService;

    @GetMapping
    public List<String> testMdc() {
        return businessService.businessLogic();
    }

}
