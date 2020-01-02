package com.baizhi.action;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpAction{
    @Autowired
    private EmpService empService;

    //---------查询全部的方法-----------
    @RequestMapping("selectEmp")
    public String selectEmp(HttpServletRequest request){
        //调用方法
        List<Emp> emps = empService.selectEmp();
        request.setAttribute("emps",emps);

        //跳转页面
        return "emplist";
    }

    //---------删除方法-------
    @RequestMapping("deleteEmp")
    public String deleteEmp(int id) {
        empService.deleteEmp(id);
        return "redirect:/emp/selectEmp";
    }

    //-------id查询员的方法-----
    @RequestMapping("selectById")
    public String selectById(int id,HttpServletRequest request) {
        //调用方法
        List<Emp> e  = empService.selectEmp();
        for (Emp em : e) {
            if (em.getId() == id) {
                //赋给成员变量传达数据库
                request.setAttribute("emp",em);
                break;
            }
        }
        return "updateEmp";
    }

    //----------修改方法-------
    @RequestMapping("updateEmp")
    public String updateEmp(Emp emp) {
        //接收修改的信息
        empService.updateEmp(emp);
        return "redirect:/emp/selectEmp";
    }

    //----添加方法-------
    @RequestMapping("insertEmp")
    public String insertEmp(Emp emp) {
        //调用添加的方法
        empService.insertEmp(emp);
        return "redirect:/emp/selectEmp";
    }

    //-------批量删除的方法-------------
    @RequestMapping("plDelete")
    public String plDelete(int[] plId) {
        for (int i : plId) {
            //批量删除
            empService.deleteEmp(i);
        }
        return "redirect:/emp/selectEmp";
    }

    //-----名字模糊查询--------
    @RequestMapping("selectByName")
    public String selectByName(String name,HttpServletRequest request){
        System.out.println("++++++_"+name);
        List<Emp> emps = empService.selectByName(name);
        request.setAttribute("emps",emps);
        return "emplist";
    }

    //--------文件上传--------
    @RequestMapping("upLoadFile")
    public String upLoadFile(MultipartFile file,HttpServletRequest request)throws IOException {
        //文件处理  将文件放置到服务的指定文件中
        String filename = file.getOriginalFilename();//当前上传的文件名  名字和后缀
        String fileType = file.getContentType();//当前上传文件的类型
        //获取文件放置的路径？servletContext.getRealPath(相对路径获取绝对路径)
        String path = request.getSession().getServletContext().getRealPath("/uplocad");
        file.transferTo(new File(path+"/"+filename));
        return "redirect:/emp/selectEmp";
    }

    /**
     * ------文件下载----------------------------
     * */
    @RequestMapping("downLoadFile")
    public String downLoadFile(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException{
        //接收文件  文件标识  文件名
        //调用业务  从服务器上获取对应的文件  响应给客户端
        //获取文件 文件路径（通过相对路径获取服务器上的绝对路径） IO输入流
        String realPath = request.getSession().getServletContext().getRealPath("/uplocad");
        //为文件名设置编码
        String newFileName = URLEncoder.encode(fileName,"UTF-8");
        /**
            设置响应前的下载前方法 以及下载后的文件名
                content-disposition：以附件的形式
                inline:直接打开
        // */
        response.setHeader("content-disposition","attachment;fileName="+newFileName);
        //[]设置响应类型 响应文件类型 解决方案：1文件分类 TXT/png..  动态获取
        //文件类型通过文件名获取文件后缀  没有“.”
        String houzui = FilenameUtils.getExtension(fileName);
        System.out.println("后缀名："+houzui);
        //在通过后最获取mime类型
        String mimeType = request.getSession().getServletContext().getMimeType("."+houzui);
        System.out.println("文件类型是："+mimeType);
        //设置响应类型
        response.setContentType(mimeType);
        //输出文件
        FileInputStream is = new FileInputStream(new File(realPath,fileName));
        //通过响应输出流给Client打印数据
        ServletOutputStream os = response.getOutputStream();
        //文件传输  读取过程中给client响应数据
        byte[] bytes = new byte[2048];
        while (true){
            //返回值代表读取数据的个数  如果到达文件末尾  返回-1
            int i = is.read(bytes,0,bytes.length);
            if (i==-1)break;
            //向外响应
            os.write(bytes,0,i);
        }
        // 关闭资源
        is.close();
        os.close();
        //跳转  只能给client一个响应 下载文件就不要跳转页面

        return null;

    }

    //------下载的副本-------------------------------------------------------------------
    @RequestMapping("downFile")//注册方法
    public String downFile(String fileName,HttpServletResponse response,HttpServletRequest request)throws IOException{
        //通过相对路径获取绝对路径
        String path = request.getSession().getServletContext().getRealPath("/uplocad");
        //设置文件名的编码格式  通过IO输入流
        String newFileName = URLEncoder.encode(fileName,"UTF-8");
        //设置响应前的下载方式
        response.setHeader("content-disposition","attachment;fileName="+newFileName);
        //通过文件名获取文件后缀
        String extension = FilenameUtils.getExtension(fileName);
        System.out.println("后缀名："+extension);
        //通过文件后缀，获取MIME类型
        String mimeType = request.getSession().getServletContext().getMimeType("." + extension);
        System.out.println("MIME类型："+mimeType);
        //设置响应类型
        response.setContentType(mimeType);
        //输入文件
        FileInputStream is = new FileInputStream(new File(path, fileName));
        //通过输出流向页面输出数据
        ServletOutputStream os = response.getOutputStream();
        //文件传输数据  通过数据传输 速度比较快
        byte[] bytes = new byte[2048];
        while (true){
            //读取服务器永久储存设备的数据到内存中
            int i = is.read(bytes, 0, bytes.length);
            //判断读取的是不是最后一位
            if (i==-1)break;
            //把内存中的数据输出到永久内存中
            os.write(bytes,0,bytes.length);
        }
        //释放资源
        is.close();
        os.close();
        //跳转
        return null;
    }


}
