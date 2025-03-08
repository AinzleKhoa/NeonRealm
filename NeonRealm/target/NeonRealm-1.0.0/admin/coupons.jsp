<%-- 
    Document   : coupons
    Created on : Mar 5, 2025, 1:28:03 AM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, gameshop.model.AdminCoupons" %>
<%
    List<AdminCoupons> coupons = (List<AdminCoupons>) request.getAttribute("coupons");
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
                <div class="col-sm-6"><h3 class="mb-0">Coupons List</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Coupons List</li>
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
        <!--begin::Container-->
        <div class="container-fluid">
            <!--begin::Row-->
            <div class="row">
                <div class="col-md-12">
                    <div class="card mb-4">
                        <div class="card-header">
                            <h3 class="card-title">Danh sách Mã Giảm Giá</h3>
                            <a href="add-coupon.jsp" class="btn btn-primary float-end">Thêm Mã Giảm Giá</a>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Mã Code</th>
                                        <th>Giảm Giá (%)</th>
                                        <th>Hạn Sử Dụng</th>
                                        <th>Giới Hạn Lượt Dùng</th>
                                        <th>Ngày Tạo</th>
                                        <th>Hành Động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (AdminCoupons coupon : coupons) {%>
                                    <tr class="align-middle">
                                        <td><%= coupon.getCouponId()%></td>
                                        <td><%= coupon.getCode()%></td>
                                        <td><%= coupon.getDiscountPercentage()%>%</td>
                                        <td><%= coupon.getExpirationDate()%></td>
                                        <td><%= coupon.getUsageLimit()%></td>
                                        <td><%= coupon.getCreatedAt()%></td>
                                        <td>
                                            <!-- Nút Edit -->
                                            <a href="${pageContext.request.contextPath}/admin/coupons?editId=<%= coupon.getCouponId()%>" class="btn btn-warning btn-sm">
                                                <i class="fas fa-edit"></i> Edit
                                            </a>
                                            <form action="${pageContext.request.contextPath}/admin/coupons" method="post" class="d-inline">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="couponId" value="<%= coupon.getCouponId()%>">
                                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
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
                    <!-- /.card -->
                </div>
                <!-- /.col -->
            </div>
            <!--end::Row-->
        </div>
        <!--end::Container-->
    </div>
    <!--end::App Content-->      
</main>
<!--end::App Main-->

<%@include file="../WEB-INF/include/admin-foot.jsp" %>