package tobyspring.helloboot;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hello") // 부트2는 RequestMapping만, 부트3이상부터 Controller + RequestMapping 이 있어야 한다.
@MyComponent
@Controller
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("") // 매핑 정보
    @ResponseBody
    public String hello(String name) {
        Arrays.stream(new String[3])
                .sorted(Comparator.reverseOrder())
                .toList();

        Arrays.stream(new String[3])
                .sorted(String::indexOf)
                .toList();
        return helloService.sayHello( Objects.requireNonNull(name));
    }
}
