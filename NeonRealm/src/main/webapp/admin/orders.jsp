<%-- 
    Document   : orders
    Created on : Mar 5, 2025, 1:41:32 PM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, gameshop.model.AdminOrders" %>
<%
    List<AdminOrders> orders = (List<AdminOrders>) request.getAttribute("orders");
%>

<%@include file="../WEB-INF/include/admin-head.jsp" %>
<!--begin::App Main-->
<main class="app-main">
    <!--begin::App Content Header-->
    <div class="app-content-header">
        <!--begin::Container-->
        <div class="container-fluid">
            <!--begin::Row-->
            <div class="row">
                <div class="col-sm-6"><h3 class="mb-0">Orders List</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Orders List</li>
                    </ol>
                </div>
            </div>
            <!--end::Row-->
        </div>
        <!--end::Container-->
    </div>
    <!--end::App Content Header-->
    <!--begin::App Content-->
<div class="app-content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card mb-4">
                    <div class="card-header"><h3 class="card-title">Danh sách Đơn Hàng</h3></div>
                    <div class="card-body">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Người Mua</th>
                                    <th>Tên Game</th>
                                    <th>Tổng giá</th>
                                    <th>Mã giảm giá</th>
                                    <th>Ngày tạo</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (AdminOrders order : orders) { %>
                                <tr class="align-middle">
                                    <td><%= order.getOrderId() %></td>
                                    <td><%= order.getUsername() %></td>
                                    <td><a target="_blank" style="text-decoration: none;" href="<%= getServletContext().getContextPath()%>/gamedetails?id=<%= order.getGameId() %>"><%= order.getGameTitle() %></a></td>
                                    <td><%= order.getTotalPrice() %></td>
                                    <td><%= order.getDiscountCode() != null ? order.getDiscountCode() : "Không có" %></td>
                                    <td><%= order.getCreatedAt() %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-end">
                            <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--end::App Content-->      
</main>
<!--end::App Main-->
<%@include file="../WEB-INF/include/admin-foot.jsp" %>
