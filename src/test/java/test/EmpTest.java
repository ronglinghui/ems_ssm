package test;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EmpTest {
    @Test
    public void update(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext_annotation.xml");
        EmpService empDao = (EmpService) ctx.getBean("empService");
        //empDao.deleteEmp(1);
        List<Emp> emps = empDao.selectEmp();
        for (Emp emp : emps) {
            System.out.println(emp);
        }
        //empDao.insertEmp(new Emp(null,"wnag",11.9,12,"1999-12-12","男"));
        //empDao.insertEmp(new Emp(null,"w",11.9,12,"1999-12-12","男"));
        //empDao.insertEmp(new Emp(null,"n",11.9,12,"1999-12-12","男"));
        //empDao.deleteEmp(1);
        //empDao.updateEmp(new Emp(2,null,null,null,null,"男"));
        System.out.println("成功");
    }
}
