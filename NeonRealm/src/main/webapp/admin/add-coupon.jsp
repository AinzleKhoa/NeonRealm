<%-- 
    Document   : add-coupon
    Created on : Mar 5, 2025, 5:04:28 PM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include/admin-head.jsp" %>

<!--begin::App Main-->
<main class="app-main">
    <!--begin::App Content Header-->
    <div class="app-content-header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6"><h3 class="mb-0">Add Coupon</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Add Coupon</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <!--end::App Content Header-->

    <!--begin::App Content-->
    <div class="app-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">Thêm Mã Giảm Giá</h3>
                        </div>
                        <div class="card-body">
                            <%-- Hiển thị thông báo lỗi nếu có --%>
                            <% String error = (String) request.getAttribute("error");
                               if (error != null) { %>
                                <div class="alert alert-danger"><%= error %></div>
                            <% } %>

                            <form action="${pageContext.request.contextPath}/admin/coupons" method="post">
                                <%-- Xác định action là "add" để Servlet xử lý đúng --%>
                                <input type="hidden" name="action" value="add">

                                <div class="mb-3">
                                    <label for="code" class="form-label">Mã Coupon</label>
                                    <input type="text" class="form-control" id="code" name="code" required>
                                </div>

                                <div class="mb-3">
                                    <label for="discount" class="form-label">Phần Trăm Giảm (%)</label>
                                    <input type="number" class="form-control" id="discount" name="discount" min="1" max="100" required>
                                </div>

                                <div class="mb-3">
                                    <label for="expiration" class="form-label">Ngày Hết Hạn</label>
                                    <input type="date" class="form-control" id="expiration" name="expiration" required>
                                </div>

                                <div class="mb-3">
                                    <label for="usage_limit" class="form-label">Giới Hạn Sử Dụng</label>
                                    <input type="number" class="form-control" id="usage_limit" name="usageLimit" min="1" required>
                                </div>

                                <button type="submit" class="btn btn-primary">Thêm Coupon</button>
                                <a href="<%= request.getContextPath()%>/admin/coupons" class="btn btn-secondary">Hủy</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>      
</main>
<!--end::App Main-->

<%@ include file="../WEB-INF/include/admin-foot.jsp" %>
