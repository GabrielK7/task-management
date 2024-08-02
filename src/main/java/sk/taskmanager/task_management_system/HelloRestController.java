package sk.taskmanager.task_management_system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prefix")
public class HelloRestController {
  private final  MessageService messageService;

    public HelloRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("hello")
    public String hello(){
        return messageService.getMsg();
    }
}
