package com.wei.services;

import com.wei.dao.ProductDao;
import com.wei.entity.Product;
import com.wei.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
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
        System.out.println(action);

        if (action.equals("get_recommend_product")){
            HttpSession httpSession = req.getSession();
            User user = (User) httpSession.getAttribute("user");
            ProductDao productDao = new ProductDao();
            List<Product> list = null;
            List<Product> recommend_product = null;
            // 做一个排序算法，获取浏览记录，看那个浏览得时长多就推荐哪一个商品
            try {
                list = productDao.recommend_product(user.getUser_id());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(recommend_product);
            if (list.size() <= 3){
                recommend_product = list;
            }
            else{
                recommend_product.add(list.get(0));
                recommend_product.add(list.get(1));
                recommend_product.add(list.get(2));
            }
            httpSession.setAttribute("recommend_product", recommend_product);
            resp.sendRedirect("/shopping_war_exploded/User_recommend_product.jsp");
        }
    }
}
