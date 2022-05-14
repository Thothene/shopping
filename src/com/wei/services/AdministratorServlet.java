package com.wei.services;

import com.wei.dao.*;
import com.wei.entity.Administrator;
import com.wei.entity.Category;
import com.wei.entity.Salesperson;
import com.wei.entity.User;
import com.wei.utils.IpUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdministratorServlet", value = "/AdministratorServlet")
public class AdministratorServlet extends HttpServlet {
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

        // 登陆
        if (action.equals("login")){
            String a_name = req.getParameter("a_name");
            String a_password = req.getParameter("a_password");
            Administrator administrator = null;

            try {
                administrator = administratorDao.select_by_name(a_name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (administrator == null){
                resp.sendRedirect("/shopping_war_exploded/Administratorlogin.jsp");
            }
            else {
                if (a_password.equals(administrator.getA_password())){
                    // 用户名和密码一致
                    httpSession.setAttribute("administrator", administrator);
                    try {
                        int result = a_operationDao.insert_log_in(administrator.getA_id(), IpUtils.getIp(req));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("/shopping_war_exploded/Administrator_main.jsp");
                }
                else{
                    resp.sendRedirect("/shopping_war_exploded/Administratorlogin.jsp");
                }
            }
        }

        // 登出
        if (action.equals("logout")){
            // 删掉session里面管理员信息
            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
            try {
                int result = a_operationDao.insert_log_out(administrator.getA_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpSession.invalidate();
            resp.sendRedirect("/shopping_war_exploded/index.jsp");
        }

        // 新增类别
        if (action.equals("add_category")){
            String cat_name = req.getParameter("cat_name");
            String str_cat_sal_id = req.getParameter("cat_sal_id");
            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");

            try {
                if (!cat_name.isEmpty() && !str_cat_sal_id.isEmpty()) { // 确保不为空
                    int cat_sal_id = Integer.parseInt(str_cat_sal_id);
                    if (categoryDao.select_one_cat(cat_name) == null && salespersonDao.select(cat_sal_id) != null) {
                        // 确保不存在这种类别
                        // 确保该销售员存在
                            int result = categoryDao.insert(cat_name, cat_sal_id);
                            a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                            if (result == 0) {
                                System.out.println("新增类别错误！");
                            }
                            resp.sendRedirect("/shopping_war_exploded/Administrator_main.jsp");
                    } else {
                        resp.sendRedirect("/shopping_war_exploded/Administrator_main.jsp");
                    }
                }
                else {
                    resp.sendRedirect("/shopping_war_exploded/Administrator_main.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 删除种类
        if (action.equals("delete_category")){
            String cat_name = req.getParameter("cat_name");
            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");

            if (!cat_name.isEmpty()) {
                try {
                    categoryDao.delete_by_name(cat_name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("/shopping_war_exploded/Administrator_category_management.jsp");
        }

        // 新增销售人员
        if (action.equals("add_salesperson")){
            String sal_name = req.getParameter("sal_name");
            String sal_password = req.getParameter("sal_password");
            String sal_email = req.getParameter("sal_email");
            String sal_activation = req.getParameter("sal_activation");

            if (!sal_name.isEmpty() && !sal_password.isEmpty() && !sal_email.isEmpty() && !sal_activation.isEmpty()){
                try {
                    if (salespersonDao.select(sal_name) == null){
                        Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                        int result = 0;
                        try {
                            result = salespersonDao.insert(sal_name, sal_password, sal_email, administrator.getA_id(), Integer.parseInt(sal_activation));
                            a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (result == 0){
                            System.out.println("新增销售人员错误！");
                        }
                        resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                    }
                    else{
                        resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
            }
        }

        // 删除销售人员
        if (action.equals("delete_salesperson")){
            String sal_id = req.getParameter("sal_id");
            String sal_name = req.getParameter("sal_name");

            if (!sal_id.isEmpty() && !sal_name.isEmpty()){
                try {
                    if (salespersonDao.select(Integer.parseInt(sal_id)) != null) {
                        if (sal_name.equals(salespersonDao.select(Integer.parseInt(sal_id)).getSal_name())) {
                            // 两者相同时才正确
                            salespersonDao.delete(sal_name);
                            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                            a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                            resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                        } else {
                            resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                        }
                    }
                    else {
                        resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(!sal_id.isEmpty()){
                try {
                    salespersonDao.delete(Integer.parseInt(sal_id));
                    Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                    a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");

            }
            else if(!sal_name.isEmpty()){
                try {
                    salespersonDao.delete(sal_name);
                    Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                    a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");

            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
            }
        }

        // 修改销售人员的管理类别
        if (action.equals("update_salesperson_category")){
            String cat_id = req.getParameter("cat_id");
            String cat_name = req.getParameter("cat_name");
            String new_sal_id = req.getParameter("new_sal_id");

            if (!cat_id.isEmpty() && !cat_name.isEmpty() && !new_sal_id.isEmpty()){
                try {
                    if (categoryDao.select_one_cat(Integer.parseInt(cat_id)) != null) {
                        if (cat_name.equals(categoryDao.select_one_cat(Integer.parseInt(cat_id)).getCat_name())) {
                            int result = categoryDao.update_cat_sal_id(cat_name, Integer.parseInt(new_sal_id));
                            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                            a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                            if (result == 0) {
                                System.out.println("修改销售人员的管理类别错误!");
                            }
                            resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                        } else {
                            resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (!cat_id.isEmpty() && !new_sal_id.isEmpty()){
                int result = 0;
                try {
                    result = categoryDao.update_cat_sal_id(Integer.parseInt(cat_id), Integer.parseInt(new_sal_id));
                    Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                    a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result == 0){
                    System.out.println("修改销售人员的管理类别错误!");
                }
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
            }
            else if (!cat_name.isEmpty() && !new_sal_id.isEmpty()){
                int result = 0;
                try {
                    result = categoryDao.update_cat_sal_id(cat_name, Integer.parseInt(new_sal_id));
                    Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                    a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result == 0){
                    System.out.println("修改销售人员的管理类别错误!");
                }
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
            }
        }

        // 修改销售人员的口令（修改密码）
        if (action.equals("update_salesperson_password")){
            String sal_id = req.getParameter("sal_id");
            String sal_name = req.getParameter("sal_name");
            String old_password = req.getParameter("old_password");
            String new_password = req.getParameter("new_password");

            if (!sal_id.isEmpty() && !sal_name.isEmpty() && !old_password.isEmpty() && !new_password.isEmpty()){
                try {
                    if (salespersonDao.select(Integer.parseInt(sal_id)) != null) {
                        if (sal_name.equals(salespersonDao.select(Integer.parseInt(sal_id)).getSal_name())) {
                            int result = salespersonDao.update_password(Integer.parseInt(sal_id), new_password, old_password);
                            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                            a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                            if (result == 0) {
                                System.out.println("修改销售人员的口令错误！");
                            }
                            resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                        } else {
                            resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (!sal_name.isEmpty() && !old_password.isEmpty() && !new_password.isEmpty()){
                int result = 0;
                try {
                    result = salespersonDao.update_password(sal_name, new_password, old_password);
                    Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                    a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result == 0){
                    System.out.println("修改销售人员的口令错误！");
                }
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");

            }
            else if (!sal_id.isEmpty() && !old_password.isEmpty() && !new_password.isEmpty()){
                int result = 0;
                try {
                    result = salespersonDao.update_password(Integer.parseInt(sal_id), new_password, old_password);
                    Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
                    a_operationDao.insert_update(administrator.getA_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result == 0){
                    System.out.println("修改销售人员的口令错误！");
                }
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");

            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Administrator_salesperson_management.jsp");
            }
         }

        // 监控特定类别的销售状况（与销售人员的功能相同）
        if (action.equals("search_given_category")){
            String cat_name = req.getParameter("cat_name");
            String cat_id_string = req.getParameter("cat_id");
            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
            try {
                a_operationDao.insert_search(administrator.getA_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!cat_name.isEmpty() && !cat_id_string.isEmpty()){
                int cat_id = Integer.parseInt(cat_id_string);
                try {
                    if (categoryDao.select_one_cat(cat_id) != null){
                        if (categoryDao.select_one_cat(cat_id).getCat_name().equals(cat_name)){
                            // 检查两者是否相等
                            Category category = categoryDao.select_one_cat(cat_id);
                            if (category==null){
                                resp.sendRedirect("/shopping_war_exploded/Administrator_category_management.jsp");
                            }
                            else{
                                httpSession.setAttribute("given_category", category);
                                resp.sendRedirect("/shopping_war_exploded/Administrator_search_given_category.jsp");
                            }
                        }
                        else{
                            resp.sendRedirect("/shopping_war_exploded/Administrator_category_management.jsp");
                        }
                    }
                    else{
                        resp.sendRedirect("/shopping_war_exploded/Administrator_category_management.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (!cat_name.isEmpty()){
                Category category = null;
                try {
                    category = categoryDao.select_one_cat(cat_name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (category==null){
                    resp.sendRedirect("/shopping_war_exploded/Administrator_category_management.jsp");
                }
                else{
                    httpSession.setAttribute("given_category", category);
                    resp.sendRedirect("/shopping_war_exploded/Administrator_search_given_category.jsp");
                }
            }
            else if (!cat_id_string.isEmpty()){
                int cat_id = Integer.parseInt(cat_id_string);
                Category category = null;
                try {
                    category = categoryDao.select_one_cat(cat_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (category==null){
                    resp.sendRedirect("/shopping_war_exploded/Administrator_category_management.jsp");
                }
                else{
                    httpSession.setAttribute("given_category", category);
                    resp.sendRedirect("/shopping_war_exploded/Administrator_search_given_category.jsp");
                }
            }
            else{
                resp.sendRedirect("/shopping_war_exploded/Administrator_category_management.jsp");
            }
        }

        // 监控所有类别的销售状况
        if (action.equals("search_all_category")){
            Category category = null;
            List<Category> list = null;
            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
            try {
                a_operationDao.insert_search(administrator.getA_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                list = categoryDao.select_all_cat();
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpSession.setAttribute("cat_list", list);
            resp.sendRedirect("/shopping_war_exploded/Administrator_search_all_category.jsp");
        }

        // 监控销售人员的销售业绩  这个错了，用search_salesperson的动作
        if (action.equals("search_all_salesperson")){
            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
            try {
                a_operationDao.insert_search(administrator.getA_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                List<Salesperson> sal_list = salespersonDao.select_by_a_id(administrator.getA_id());
                List<Category> cat_list = new ArrayList<>();
                for(Salesperson salesperson : sal_list){
                    cat_list.addAll(categoryDao.select_by_cat_sal_id(salesperson.getSal_id()));
                }
                httpSession.setAttribute("sal_list", sal_list);
                httpSession.setAttribute("cat_list", cat_list);
                resp.sendRedirect("/shopping_war_exploded/Administrator_search_all_salesperson.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (action.equals("search_salesperson")){
            Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
            try {
                a_operationDao.insert_search(administrator.getA_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sal_id = req.getParameter("sal_id");
            if (sal_id.isEmpty()){
                resp.sendRedirect("/shopping_war_exploded/Administrator_statistical_chart.jsp");
            }
            else{
                Salesperson salesperson = null;
                try {
                    salesperson = salespersonDao.select(Integer.parseInt(sal_id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (salesperson == null){
                    resp.sendRedirect("/shopping_war_exploded/Administrator_statistical_chart.jsp");
                }
                else{
                    List<Map<String, Float>> month_list = null;
                    try {
                        month_list = salespersonDao.count_every_month_money(salesperson.getSal_id());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    httpSession.setAttribute("salesperson", salesperson);
                    httpSession.setAttribute("month_list", month_list);
                    resp.sendRedirect("/shopping_war_exploded/Administrator_search_all_salesperson.jsp");
                }
            }
        }
    }
}
