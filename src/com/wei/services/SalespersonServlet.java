package com.wei.services;

import com.wei.dao.*;
import com.wei.entity.*;
import com.wei.utils.IpUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SalespersonServlet", value = "/SalespersonServlet")
public class  SalespersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");

        // 获得行为
        String action = req.getParameter("action");

        A_operationDao a_operationDao = new A_operationDao();
        AdministratorDao administratorDao = new AdministratorDao();
        B_recordDao b_recordDao = new B_recordDao();
        CartDao cartDao = new CartDao();
        CategoryDao categoryDao = new CategoryDao();
        P_recordDao p_recordDao = new P_recordDao();
        ProductDao productDao = new ProductDao();
        SalespersonDao salespersonDao = new SalespersonDao();
        S_operationDao s_operationDao = new S_operationDao();
        U_operationDao u_operationDao = new U_operationDao();
        UserDao userDao = new UserDao();

        HttpSession httpSession = req.getSession();

        // 销售人登陆
        if (action.equals("login")){
            String sal_name = req.getParameter("sal_name");
            String sal_password = req.getParameter("sal_password");
            Salesperson salesperson = null;

            try {
                salesperson = salespersonDao.select(sal_name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (salesperson == null){
                resp.sendRedirect("/shopping_war_exploded/Salespersonlogin.jsp");
            }
            else {
                if (salesperson.getSal_password().equals(sal_password)) {
                    // 名称密码相同
                    httpSession.setAttribute("salesperson", salesperson);
                    try {
                        s_operationDao.insert_log_in(salesperson.getSal_id(), IpUtils.getIp(req));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                } else {
                    resp.sendRedirect("/shopping_war_exploded/Salespersonlogin.jsp");
                }
            }
        }

        // 销售员登出
        if (action.equals("logout")){
            // 删掉session里面管理员信息
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_log_out(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpSession.invalidate();
            resp.sendRedirect("/shopping_war_exploded/index.jsp");
        }

        // 插入新商品
        if (action.equals("add_product")) {
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_update(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            DiskFileItemFactory dfif = new DiskFileItemFactory();
            File f = new File("/temp");//文件缓存目录
            if (!f.exists()) {
                f.mkdirs();
            }
            String path = req.getServletContext().getRealPath("/images/products");
            dfif.setRepository(new File(path));
            dfif.setSizeThreshold(1024 * 1024 * 10);
            ServletFileUpload upload = new ServletFileUpload(dfif);
            upload.setHeaderEncoding("utf-8");
            try {
                List<FileItem> items = upload.parseRequest(req);
                String pro_name = null;
                String pro_info = null;
                String pro_img_name = null;
                int pro_number = 0;
                float pro_price = 0;
                int pro_cat_id = 0;
                int pro_sold = 0;
                for (int i = 0; i < items.size(); i++) {
                    FileItem item = items.get(i);
                    if (item.isFormField()) {
                        //接收的是非文件
                        String filedName = item.getFieldName();
                        if (filedName.equals("pro_name")) { // 检查有没有重名的
                            pro_name = new String(item.getString().getBytes("ISO8859_1"), "UTF-8");
                            Product product = productDao.select_by_pro_name(pro_name);
                            if (product != null) { // 存在这个商品名字了，直接返回到主页面
                                resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                                break;
                            }
                            pro_img_name = pro_name + ".jpg";
                        } else if (filedName.equals("pro_cat_id")) { // 类别是不是归当前销售员管理
                            pro_cat_id = Integer.parseInt(item.getString("utf-8"));
                            Category category = categoryDao.select_one_cat(pro_cat_id);
                            if (category.getCat_sal_id() != salesperson.getSal_id()) {
                                resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                                break;
                            }
                        } else if (filedName.equals("pro_info")) {
                            pro_info = item.getString("utf-8");
                        } else if (filedName.equals("pro_number")) {
                            pro_number = Integer.parseInt(item.getString("utf-8"));
                        } else if (filedName.equals("pro_price")) {
                            pro_price = Float.valueOf(item.getString("utf-8"));
                        } else if (filedName.equals("pro_sold")) {
                            pro_sold = Integer.parseInt(item.getString("utf-8"));
                        }
                    } else {
                        String filedName = item.getFieldName();

                        String webpath = "/images/products";//储存路径
                        String rename = pro_name + ".jpg";
                        String filepath = getServletContext().getRealPath(webpath + filedName);
                        File dir = new File(webpath);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        File file = new File(path, rename);
                        try {
                            //System.out.print("go2");
                            System.out.println(file);
                            System.out.println(file.exists());
                            item.write(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                int result = productDao.insert(pro_name, pro_info, pro_img_name, pro_number, pro_price, pro_cat_id, pro_sold);
                if (result == 1) {
                    System.out.println("插入成功！");
                } else {
                    System.out.println("插入失败！");
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
        }

        // 删除商品
        if (action.equals("delete_product")){
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            String pro_id = req.getParameter("pro_id");
            try {
                s_operationDao.insert_update(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!pro_id.isEmpty()){
                try {
                    Product product = productDao.select_by_pro_id(Integer.parseInt(pro_id));
                    if (product == null){
                        resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                    }
                    else {
                        if (categoryDao.select_one_cat(product.getPro_cat_id()).getCat_sal_id() != salesperson.getSal_id()) {
                            System.out.println("删除商品不在当前销售员管理中！");
                            resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                        } else {
                            productDao.delete_by_id(Integer.parseInt(pro_id));
                            resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
            }
        }

        // 修改商品销售价格
        if (action.equals("update_product_price")){
            String pro_id = req.getParameter("pro_id");
            String pro_price = req.getParameter("pro_price");
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_update(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!pro_id.isEmpty() && !pro_price.isEmpty()){
                try {
                    if (categoryDao.select_one_cat(productDao.select_by_pro_id(Integer.parseInt(pro_id)).getPro_cat_id()).getCat_sal_id() != salesperson.getSal_id()){
                        System.out.println("商品不在当前销售员管理中！");
                    }
                    else {
                        int result = 0;
                        try {
                            result = productDao.update_pro_price(Integer.parseInt(pro_id), Float.parseFloat(pro_price));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (result == 0) {
                            System.out.println("修改商品销售价格错误！");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
        }

        // 修改商品总量
        if (action.equals("update_product_number")){
            String pro_id = req.getParameter("pro_id");
            String pro_number = req.getParameter("pro_number");
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_update(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!pro_id.isEmpty() && !pro_number.isEmpty()){
                try {
                    if (categoryDao.select_one_cat(productDao.select_by_pro_id(Integer.parseInt(pro_id)).getPro_cat_id()).getCat_sal_id() != salesperson.getSal_id()){
                        System.out.println("商品不在当前销售员管理中！");
                    }
                    else {
                        int result = 0;
                        result = productDao.update_pro_number(Integer.parseInt(pro_id), Integer.parseInt(pro_number));
                        if (result == 0) {
                            System.out.println("修改商品总量错误！");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
        }

        // 查看特定商品的浏览日志
        if (action.equals("search_given_product_b_record")){
            String pro_id = req.getParameter("pro_id");
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_search(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!pro_id.isEmpty()){
                try {
                    if (categoryDao.select_one_cat(productDao.select_by_pro_id(Integer.parseInt(pro_id)).getPro_cat_id()).getCat_sal_id() != salesperson.getSal_id()){
                        System.out.println("商品不在当前销售员管理中！");
                        resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                    }
                    else {
                        List<B_record> b_given_list = null;
                        b_given_list = b_recordDao.search_by_pro_id(Integer.parseInt(pro_id));
                        if (b_given_list == null) {
                            System.out.println("查看特定商品的浏览日志错误！");
                        }
                        httpSession.setAttribute("b_given_list", b_given_list);
                        resp.sendRedirect("/shopping_war_exploded/Salesperson_search_given_product_b_record.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
            }
        }

        // 查看所有商品的浏览日志
        if (action.equals("search_all_product_b_record")){
        }

        // 查看特定商品的购买日志
        if (action.equals("search_given_product_p_record")){
            String pro_id = req.getParameter("pro_id");
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_search(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!pro_id.isEmpty()){
                try {
                    if (categoryDao.select_one_cat(productDao.select_by_pro_id(Integer.parseInt(pro_id)).getPro_cat_id()).getCat_sal_id() != salesperson.getSal_id()){
                        System.out.println("商品不在当前销售员管理中！");
                        resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                    }
                    else {
                        List<P_record> p_given_list = null;
                        p_given_list = p_recordDao.search_by_pro_id(Integer.parseInt(pro_id));
                        if (p_given_list == null) {
                            System.out.println("查看特定商品的浏览日志错误！");
                        }
                        httpSession.setAttribute("p_given_list", p_given_list);
                        resp.sendRedirect("/shopping_war_exploded/Salesperson_search_given_product_p_record.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
            }
        }

        // 查看所有商品的购买日志
        if (action.equals("search_all_product_p_record")){
        }

        // 监控特定类别的销售状况
        if (action.equals("search_given_category")){
            String cat_id = req.getParameter("cat_id");
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_search(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!cat_id.isEmpty()){
                // 查看该类别是否存在
                Category category = null;
                try {
                    category = categoryDao.select_one_cat(Integer.parseInt(cat_id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (category != null) {
                    // 是否是当前销售员的管理范围
                    if (category.getCat_sal_id() == salesperson.getSal_id()) {
                        List<Product> pro_list = null;
                        try {
                            pro_list = productDao.select_by_pro_cat_id(Integer.parseInt(cat_id));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (pro_list == null){
                            System.out.println("监控特定类别的销售状况，获取商品列表时出错！");
                            resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                        }
                        else {
                            httpSession.setAttribute("pro_list", pro_list);
                            resp.sendRedirect("/shopping_war_exploded/Salesperson_search_given_category.jsp");
                        }
                    }
                    else{
                        System.out.println("该类别不在销售员管理内！");
                        resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                    }
                }
                else{
                    // 不存在的时候就跳转回到主页面
                    System.out.println("不存在该类别！");
                    resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
                }
            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
            }
        }

        // 监控所有类别的销售状况
        if (action.equals("search_all_category")){
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_search(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<Product> pro_list = new ArrayList<>();

            List<Category> cat_list = null;
            try {
                cat_list = categoryDao.select_by_cat_sal_id(salesperson.getSal_id());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cat_list != null) {
                for (Category category : cat_list) {
                    try {
                        pro_list.addAll(productDao.select_by_pro_cat_id(category.getCat_id()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                httpSession.setAttribute("pro_list", pro_list);
                resp.sendRedirect("/shopping_war_exploded/Salesperson_search_all_category.jsp");
            }
            else{
                // 为空的时候就跳转回主页面
                System.out.println("该销售员没有任何管理类别");
                resp.sendRedirect("/shopping_war_exploded/Salesperson_main.jsp");
            }
        }

        // 可视化
        if (action.equals("visual_performance")){
            Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
            try {
                s_operationDao.insert_search(salesperson.getSal_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Map<String, Float>> month_list = null;
            try {
                month_list = salespersonDao.count_every_month_money(salesperson.getSal_id());
            } catch (Exception e) {
                e.printStackTrace();
            }

            httpSession.setAttribute("month_list", month_list);
            resp.sendRedirect("/shopping_war_exploded/Salesperson_money.jsp");
        }
    }
}
