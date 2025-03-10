<%-- 
    Document   : add-game
    Created on : Mar 5, 2025, 5:30:00 PM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include/admin-head.jsp" %>
<%@ page import="java.util.List" %>


<!--begin::App Main-->
<main class="app-main">
    <!--begin::App Content Header-->
    <div class="app-content-header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6"><h3 class="mb-0">Add Game</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Add Game</li>
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
                            <h3 class="card-title">Thêm Game Mới</h3>
                        </div>
                        <div class="card-body">
                            <% String error = (String) request.getAttribute("error");
                               if (error != null) { %>
                                <div class="alert alert-danger"><%= error %></div>
                            <% } %>

                            <form action="${pageContext.request.contextPath}/admin/games" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="add">

                                <div class="mb-3">
                                    <label for="title" class="form-label">Tên Game</label>
                                    <input type="text" class="form-control" id="title" name="title" required>
                                </div>

                                <div class="mb-3">
                                    <label for="description" class="form-label">Mô Tả</label>
                                    <textarea class="form-control" id="description" name="description"></textarea>
                                </div>

                                <div class="mb-3">
                                    <label for="image" class="form-label">Hình Ảnh</label>
                                    <input type="file" class="form-control" id="image" name="image" accept="image/*">
                                </div>

                                <div class="mb-3">
                                    <label for="price" class="form-label">Giá (VND)</label>
                                    <input type="number" class="form-control" id="price" name="price" min="0" step="0.01" required>
                                </div>

                                <div class="mb-3">
                                    <label for="release_date" class="form-label">Ngày Phát Hành</label>
                                    <input type="date" class="form-control" id="release_date" name="release_date">
                                </div>

                                <!-- Dropdowns for Genres, Categories, Developers, Publishers, Platforms -->
                                <div class="mb-3">
                                    <label for="genres" class="form-label">Thể Loại</label>
                                    <select class="form-select" id="genres" name="genres" multiple>
                                        <% for (String genre : (List<String>) request.getAttribute("allGenres")) { %>
                                            <option value="<%= genre %>"><%= genre %></option>
                                        <% } %>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="categories" class="form-label">Danh Mục</label>
                                    <select class="form-select" id="categories" name="categories" multiple>
                                        <% for (String category : (List<String>) request.getAttribute("allCategories")) { %>
                                            <option value="<%= category %>"><%= category %></option>
                                        <% } %>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="developers" class="form-label">Nhà Phát Triển</label>
                                    <select class="form-select" id="developers" name="developers" multiple>
                                        <% for (String developer : (List<String>) request.getAttribute("allDevelopers")) { %>
                                            <option value="<%= developer %>"><%= developer %></option>
                                        <% } %>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="publishers" class="form-label">Nhà Phát Hành</label>
                                    <select class="form-select" id="publishers" name="publishers" multiple>
                                        <% for (String publisher : (List<String>) request.getAttribute("allPublishers")) { %>
                                            <option value="<%= publisher %>"><%= publisher %></option>
                                        <% } %>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="platforms" class="form-label">Nền Tảng</label>
                                    <select class="form-select" id="platforms" name="platforms" multiple>
                                        <% for (String platform : (List<String>) request.getAttribute("allPlatforms")) { %>
                                            <option value="<%= platform %>"><%= platform %></option>
                                        <% } %>
                                    </select>
                                </div>

                                <button type="submit" class="btn btn-primary">Thêm Game</button>
                                <a href="<%= request.getContextPath()%>/admin/games" class="btn btn-secondary">Hủy</a>
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