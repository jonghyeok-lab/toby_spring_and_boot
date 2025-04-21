package tobyspring.helloboot;

import java.util.Objects;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        return helloService.sayHello( Objects.requireNonNull(name));
    }
}
