<%-- 
    Document   : games
    Created on : Mar 7, 2025, 1:54:15 AM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, gameshop.model.AdminGames" %>
<%
    List<AdminGames> games = (List<AdminGames>) request.getAttribute("games");
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
                <div class="col-sm-6"><h3 class="mb-0">Games List</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Games List</li>
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
                    <div class="card-header"><h3 class="card-title">Danh sách Games</h3></div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Game ID</th>
                                    <th>Image</th>
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Release Date</th>
                                    <th>Created At</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (AdminGames game : games) { %>
                                <tr class="align-middle">
                                    <td><%= game.getGameId() %></td>
                                    <td>
                                        <img src="<%= request.getContextPath() + "/assets/img/cards/" + game.getImageUrl() %>" 
                                             alt="Game Image" width="100">
                                    </td>
                                    <td><%= game.getTitle() %></td>
                                    <td><%= game.getDescription() %></td>
                                    <td>$<%= game.getPrice() %></td>
                                    <td><%= game.getReleaseDate() %></td>
                                    <td><%= game.getCreatedAt() %></td>
                                </tr>
                                <% } %>
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
