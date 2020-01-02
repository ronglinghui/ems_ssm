package test;

import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ManagerTest {
    @Test
    public void login(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext_annotation.xml");
        ManagerService managerService = (ManagerService) ctx.getBean("managerService");
        Manager manager = managerService.login("张三");
        System.out.println(manager);
        System.out.println("成功");
    }
    @Test
    public void regis(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ManagerService managerService = (ManagerService) ctx.getBean("managerService");
        managerService.register(new Manager(null,"历史","历史","123","男",null));
        System.out.println("成功");
    }
}
