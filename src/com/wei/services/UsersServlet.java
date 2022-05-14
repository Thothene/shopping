package com.wei.services;

import com.wei.dao.*;
import com.wei.entity.*;
import com.wei.utils.IpUtils;
import com.wei.utils.MailUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@WebServlet(name = "UsersServlet", value = "/UsersServlet")
public class UsersServlet extends HttpServlet {
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
            String user_name = req.getParameter("user_name");
            String user_password = req.getParameter("user_password");
            User user = null;

            try {
                user = userDao.select_self_by_name(user_name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (user != null) {
                if (user_password.equals(user.getUser_password()) && user.getUser_activation() == 1) {
                    // 用户名和密码一致
                    // 用户已激活
                    int uope_result = 0;
                    try {
                        uope_result = u_operationDao.insert_log_in(user.getUser_id(), IpUtils.getIp(req));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (uope_result != 1){
                        System.out.println("用户登陆操作插入错误！");
                    }
                    httpSession.setAttribute("user", user);
                    resp.sendRedirect("/shopping_war_exploded/Products.jsp");
                } else {
                    resp.sendRedirect("/shopping_war_exploded/Userlogin.jsp");
                }
            }
            else {
                resp.sendRedirect("/shopping_war_exploded/Userlogin.jsp");
            }
        }

        // 登出
        if (action.equals("logout")){
            count_total_time(req, resp);
            User user = (User) httpSession.getAttribute("user");
            int uope_result = 0;
            try {
                uope_result = u_operationDao.insert_log_out(user.getUser_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (uope_result != 1){
                System.out.println("用户登出操作插入错误！");
            }
            httpSession.invalidate();
            resp.sendRedirect("/shopping_war_exploded/index.jsp");
        }

        // 注册
        if (action.equals("register")){
            //获取用户输入的用户名和密码
            String user_name = req.getParameter("user_name");
            String user_password = req.getParameter("user_password");
            String user_re_password = req.getParameter("user_re_password");
            String user_email = req.getParameter("user_email");
            User user = null;
            try {
                if (user_password.equals(user_re_password) && userDao.select_self_by_name(user_name) == null) {
                    // 密码一致
                    // 查无此人
                    int send_email_result = MailUtils.sendmail(user_email, "正在注册中！");
                    if (send_email_result == 0){
                        System.out.println("邮箱错误，重新输入注册信息");
                        resp.sendRedirect("/shopping_war_exploded/Register.jsp");
                        return;
                    }
                    int flag = userDao.insert(user_name, user_password, user_email, 1);
                    if (flag == 1){
                        System.out.println("注册成功！");
                        MailUtils.sendmail(user_email, "亲爱的用户，恭喜你注册成功！\n您的名称是："+user_name+"\n您的密码是："+user_password);
                    }
                    else{
                        System.out.println("注册失败！");
                        resp.sendRedirect("/shopping_war_exploded/Register.jsp");
                        return;
                    }
                    //回到登陆页面
                    resp.sendRedirect("/shopping_war_exploded/Products.jsp");
                }
                else{
                    //不存在此人或密码不一致，跳转回登陆注册页面
                    System.out.println("密码不一致，重新输入注册信息");
                    //重定向回到登陆页面
                    resp.sendRedirect("/shopping_war_exploded/Register.jsp");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 查看购物车
        if (action.equals("search_cart")){
            count_total_time(req, resp);
            User user = (User) httpSession.getAttribute("user");
            if (user == null){
                resp.sendRedirect("/shopping_war_exploded/Error.jsp");
            }
            else {

                int uope_result = 0;
                try {
                    uope_result = u_operationDao.insert_search(user.getUser_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (uope_result != 1){
                    System.out.println("用户查询操作插入错误！");
                }

                try {
                    List<Cart> cart_list = cartDao.select_by_user_id(user.getUser_id());
                    if (cart_list == null) {
                        System.out.println("用户查看购物车错误！");
                        resp.sendRedirect("/shopping_war_exploded/Products.jsp");
                    } else {
                        httpSession.setAttribute("cart_list", cart_list);
                        resp.sendRedirect("/shopping_war_exploded/User_search_cart.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 查看特定商品
        if (action.equals("search_given_product")){
            String str_pro_id = req.getParameter("pro_id");
            User user = (User) httpSession.getAttribute("user");
            if (user == null){
                resp.sendRedirect("/shopping_war_exploded/Error.jsp");
            }
            else {
                int uope_result = 0;
                try {
                    uope_result = u_operationDao.insert_browse(user.getUser_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (uope_result != 1){
                    System.out.println("用户浏览商品操作插入错误！");
                }

                if (str_pro_id != null) {
                    int pro_id = Integer.parseInt(str_pro_id);
                    Product product = null;
                    try {
                        product = productDao.select_by_pro_id(pro_id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (product != null) {
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        httpSession.setAttribute("b_record_start_time", formatter.format(date));
                        httpSession.setAttribute("given_product", product);
                        resp.sendRedirect("/shopping_war_exploded/Product.jsp");
                    } else {
                        System.out.println("根据pro_id未找到对应商品！");
                    }
                } else {
                    System.out.println("查看特定商品\n字符串为空！");
                }
            }
        }

        // 查看特定商品的浏览记录
        if (action.equals("search_given_browse_records")){

        }

        // 查看所有商品的浏览记录
        if (action.equals("search_all_browse_records")){
            count_total_time(req, resp);
            User user = (User) httpSession.getAttribute("user");
            List<B_record> b_recordList = new ArrayList<>();
            if (user == null){
                resp.sendRedirect("/shopping_war_exploded/Error.jsp");
            }
            else{
                int uope_result = 0;
                try {
                    uope_result = u_operationDao.insert_search(user.getUser_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (uope_result != 1){
                    System.out.println("用户查询操作插入错误！");
                }
                try {
                    b_recordList = b_recordDao.search_by_user_id(user.getUser_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                httpSession.setAttribute("b_recordList", b_recordList);
                resp.sendRedirect("/shopping_war_exploded/User_search_b_record.jsp");
            }
        }

        // 查看特定商品的购买记录
        if (action.equals("search_given_purchased_records")){

        }

        // 查看所有商品的购买记录
        if (action.equals("search_all_purchased_records")){
            count_total_time(req, resp);
            User user = (User) httpSession.getAttribute("user");
            List<P_record> p_recordList = new ArrayList<>();
            if (user == null){
                resp.sendRedirect("/shopping_war_exploded/Error.jsp");
            }
            else{
                int uope_result = 0;
                try {
                    uope_result = u_operationDao.insert_search(user.getUser_id(), IpUtils.getIp(req));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (uope_result != 1){
                    System.out.println("用户查询操作插入错误！");
                }
                try {
                    p_recordList = p_recordDao.search_by_user_id(user.getUser_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                httpSession.setAttribute("p_recordList", p_recordList);
                resp.sendRedirect("/shopping_war_exploded/User_search_p_record.jsp");
            }
        }

        // 添加商品到购物车
        if (action.equals("add_product_to_cart")){
            count_total_time(req, resp);
            Cart cart = null;
            String str_pro_id = req.getParameter("pro_id");
            String str_pro_number = req.getParameter("pro_number");
            User user = (User) httpSession.getAttribute("user");
            Product given_product = (Product) httpSession.getAttribute("given_product");

            if (str_pro_id != null && str_pro_number != null){
                int pro_id = Integer.parseInt(str_pro_id);
                int pro_number = Integer.parseInt(str_pro_number);
                int result = 0;
                try {
                    cart = cartDao.select_by_user_pro_id(user.getUser_id(), pro_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (cart == null) {
                    try {
                        result = cartDao.insert(user.getUser_id(), pro_id, pro_number);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    int pro_stock = given_product.getPro_number() - given_product.getPro_sold();
                    if (pro_stock <= cart.getCart_pro_number()){
                        System.out.println("没有库存了！");
                    }
                    else if(pro_stock < cart.getCart_pro_number() + pro_number){
                        System.out.println("剩余库存不足！");
                    }
                    else{
                        try {
                            result = cartDao.update_cart_pro_number(cart.getCart_pro_number() + pro_number, user.getUser_id(), pro_id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (result != 1){
                    System.out.println("添加商品到购物车\n插入失败！");
                }
                else{
                    resp.sendRedirect("/shopping_war_exploded/Products.jsp");
                }
            }
            else{
                System.out.println("添加商品到购物车\n获取信息失败！");
            }
        }

        // 购买商品
        if (action.equals("purchase_product")){
            User user = (User) httpSession.getAttribute("user");

            int pro_number = 0;
            float total_money = 0;

//            获得所有商品的信息列表
            List<Product> pro_list = null;
            try {
                pro_list = productDao.select_all_products();
            } catch (Exception e) {
                e.printStackTrace();
            }

//            对所有商品进行如下操作：
//            通过拼接商品的id号，获取对应内容，如果为空则表示购物车中没有此类
            for (Product product : pro_list){
                // 名字是商品id 值是商品数量
                // 拿到商品id
                int p_id = product.getPro_id();
                // 拿到商品数量
                String s = req.getParameter("purchase_pro_id_"+p_id);
                if (s == null){
                    continue;
                }
                pro_number = Integer.parseInt(s);

                // 插入到已购商品表中
                int purchase_result = 0;
                int p_record_result = 0;
                int cart_result = 0;
                try {
                    purchase_result = productDao.update_pro_sold(product.getPro_id(), product.getPro_sold() + pro_number);
                    p_record_result = p_recordDao.insert(user.getUser_id(), product.getPro_id(), pro_number, product.getPro_price() * pro_number, IpUtils.getIp(req));
                    cart_result = cartDao.delete_by_user_pro_id(user.getUser_id(), product.getPro_id());
                    total_money += product.getPro_price() * pro_number;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (purchase_result + p_record_result + cart_result != 3){
                    System.out.println("Wrong!");
                    resp.sendRedirect("/shopping_war_exploded/User_search_cart.jsp");
                }
                else{
                    System.out.println(purchase_result + p_record_result);
                }
            }

            int send_email_result = MailUtils.sendmail(user.getUser_email(), "购买成功！总金额为："+total_money+"￥，欢迎下次光临~榨干你的钱包~");
            if (send_email_result != 1){
                System.out.println("发送邮件失败！");
            }
            int uope_result = 0;
            try {
                uope_result = u_operationDao.insert_purchase(user.getUser_id(), IpUtils.getIp(req));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (uope_result != 1){
                System.out.println("用户购买操作插入错误！");
            }
            resp.sendRedirect("/shopping_war_exploded/Products.jsp");
        }

        // 查看特定类别商品
        if (action.equals("search_given_category")){
            User user = (User) httpSession.getAttribute("user");
            if (user == null){
                resp.sendRedirect("/shopping_war_exploded/Error.jsp");
            }
            else {
                count_total_time(req, resp);
                String cat_name = req.getParameter("given_category");
                try {
                    httpSession.setAttribute("cat_id", categoryDao.select_one_cat(cat_name).getCat_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("/shopping_war_exploded/Products_by_category.jsp");
            }
        }

        // 彻底删除用户
        if (action.equals("delete_user")){
            User user = (User) httpSession.getAttribute("user");

            if (user == null){
                resp.sendRedirect("/shopping_war_exploded/Error.jsp");
            }
            else {
                count_total_time(req, resp);
                int result = 0;
                try {
                    result = userDao.update_sign_by_id(user.getUser_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result == 0) {
                    System.out.println("用户激活码置零错误1！");
                    resp.sendRedirect("/shopping_war_exploded/Products.jsp");
                } else if (result == 1) {
                    resp.sendRedirect("/shopping_war_exploded/index.jsp");
                } else {
                    System.out.println("用户激活码置零错误2！");
                    resp.sendRedirect("/shopping_war_exploded/Products.jsp");
                }
            }
        }

        if (action.equals("get_recommend_product")){
            User user = (User) httpSession.getAttribute("user");
            List<Product> list = null;
            List<Product> recommend_product = null;
            if (user == null){
                resp.sendRedirect("/shopping_war_exploded/Error.jsp");
            }
            else {
                count_total_time(req, resp);
                // 做一个排序算法，获取浏览记录，看那个浏览得时长多就推荐哪一个商品
                try {
                    list = productDao.recommend_product(user.getUser_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (list.size() <= 3) {
                    recommend_product = list;
                } else {
                    recommend_product = list.subList(0, 3);
                }
                httpSession.setAttribute("recommend_product", recommend_product);
                resp.sendRedirect("/shopping_war_exploded/User_recommend_product.jsp");
            }
        }
    }

    // 计算浏览时间
    public static void count_total_time(HttpServletRequest req, HttpServletResponse resp){
        HttpSession httpSession = req.getSession();
        B_recordDao b_recordDao = new B_recordDao();
        Date past_time = null;

        // 获取当前用户和商品的信息, 获取之前的时间信息
        User user = (User) httpSession.getAttribute("user");
        Product product = (Product) httpSession.getAttribute("given_product");
        String str_past_time = (String) httpSession.getAttribute("b_record_start_time");
        if (user != null && product != null && str_past_time != null) {
            // 需要移除此时间，此次浏览已经结束
            httpSession.removeAttribute("b_record_start_time");
            // 给出时间的格式
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 得到浏览的初始时间
            try {
                past_time = dateFormat.parse(str_past_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 获取现在的时间，两者相减得到浏览的时间
            Date now_time = new Date();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long total_time = (now_time.getTime() - past_time.getTime()) / 1000;
            try {
                // 插入此次浏览记录
                int b_result = b_recordDao.insert(user.getUser_id(), product.getPro_id(), str_past_time, String.valueOf(total_time), IpUtils.getIp(req));
                if (b_result != 1) {
                    System.out.println("记录浏览记录时出错！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
