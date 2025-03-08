<%-- 
    Document   : edit-coupon
    Created on : Mar 6, 2025, 7:14:16 AM
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
                <div class="col-sm-6"><h3 class="mb-0">Chỉnh Sửa Mã Giảm Giá</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Edit Coupon</li>
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
                            <h3 class="card-title">Sửa Mã Giảm Giá</h3>
                        </div>
                        <div class="card-body">
                            <%-- Lấy dữ liệu mã giảm giá --%>
                            <%
                                gameshop.model.AdminCoupons coupon = (gameshop.model.AdminCoupons) request.getAttribute("coupon");
                                if (coupon == null) {
                            %>
                                <div class="alert alert-danger">Không tìm thấy mã giảm giá.</div>
                            <% } else { %>

                            <form action="${pageContext.request.contextPath}/admin/coupons" method="post">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="couponId" value="<%= coupon.getCouponId()%>">

                                <div class="mb-3">
                                    <label for="code" class="form-label">Mã Coupon</label>
                                    <input type="text" class="form-control" id="code" name="code" value="<%= coupon.getCode() %>" required>
                                </div>
                                <div class="mb-3">
                                    <label for="discount" class="form-label">Phần Trăm Giảm (%)</label>
                                    <input type="number" class="form-control" id="discount" name="discount_percentage" min="1" max="100" value="<%= coupon.getDiscountPercentage()%>" required>
                                </div>
                                <div class="mb-3">
                                    <label for="expiration_date" class="form-label">Ngày Hết Hạn</label>
                                    <input type="date" class="form-control" id="expiration_date" name="expiration_date" value="<%= coupon.getExpirationDate()%>" required>
                                </div>
                                <div class="mb-3">
                                    <label for="usage_limit" class="form-label">Giới Hạn Sử Dụng</label>
                                    <input type="number" class="form-control" id="usage_limit" name="usage_limit" min="1" value="<%= coupon.getUsageLimit() %>" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Cập Nhật Coupon</button>
                                
                                <a href="<%= request.getContextPath()%>/admin/coupons" class="btn btn-secondary">Hủy</a>
                            </form>

                            <% } %> <!-- Kết thúc kiểm tra coupon -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>      
</main>
<!--end::App Main-->

<%@ include file="../WEB-INF/include/admin-foot.jsp" %>
