<%@page import="java.util.ArrayList"%>
<%@page import="gameshop.model.Game"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- CSS -->
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/cdn.css.bootstrap.min">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/bootstrap-reboot.min.css">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/magnific-popup.css">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/nouislider.min.css">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/jquery.mCustomScrollbar.min.css">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/paymentfont.min.css">
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/css/main.css">

        <!-- Favicons -->
        <link rel="icon" type="image/png" href="<%= getServletContext().getContextPath()%>/assets/icon/favicon-32x32.png" sizes="32x32">
        <link rel="apple-touch-icon" href="<%= getServletContext().getContextPath()%>/assets/icon/favicon-32x32.png">

        <meta name="description" content="Digital marketplace HTML Template by Dmitry Volkov">
        <meta name="keywords" content="">
        <meta name="author" content="Dmitry Volkov">
        <title>NeonRealm</title>

    </head>
    <%@include file="/WEB-INF/include/header2.jsp" %>

    <!-- banner -->
    <section class="banner_main">
        <div class="container-fluid">
            <div class="row d_flex">
                <div class="col-md-5">
                    <div class="text-bg">
                        <h1>NeonRealm</h1>
                        <strong>Next-Gen Gaming & Digital Store</strong>
                        <span>Dive into the ultimate gaming experience with exclusive deals, cutting-edge tech, and neon-inspired aesthetics. Whether you're a casual gamer or a hardcore enthusiast, NeonRealm is your gateway to the future of gaming.</span>
                        <a href="#">Browse Now</a>
                    </div>
                </div>
                <div class="col-md-7 padding_right1">
                    <div class="text-img">
                        <figure><img src="<%= getServletContext().getContextPath()%>/assets/img/banner/spiderman.png" alt="#"/></figure>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</header>
<!-- end banner -->

<!-- best sellers -->
<section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Best Sellers</b> of this month</h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel section__carousel section__carousel--big" id="carousel0">
        <% ArrayList<Game> gamesByCategory = (ArrayList) request.getAttribute("gamesByCategory"); %>
        
        <%    if (gamesByCategory != null && !gamesByCategory.isEmpty()) {
                for (Game g : gamesByCategory) {
                    if (g.getCategories().contains("Best Sellers")) {
        %>
        <!-- big card -->
        <div class="card card--big">
            <a href="<%= getServletContext().getContextPath()%>/pages/details.jsp" class="card__cover">
                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
            </a>

            <div class="card__wrap">
                <div class="card__title">
                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= g.getTitle()%></a></h3>
                </div>

                <ul class="card__list">
                    <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                    <li><span>Genres:</span><%= g.getGenres()%></li>
                </ul>

                <ul class="card__platforms">
                    <% for (String platform : g.getPlatforms()) {

                            String iconPath = "";
                            String iconName = "";

                            switch (platform) {
                                case "PC":
                                    iconPath = "pc.svg";
                                    iconName = "PC";
                                    break;
                                case "PlayStation":
                                    iconPath = "playstation.svg";
                                    iconName = "PlayStation";
                                    break;
                                case "Xbox":
                                    iconPath = "xbox.svg";
                                    iconName = "Xbox";
                                    break;
                                case "Nintendo Switch":
                                    iconPath = "nintendo.svg";
                                    iconName = "Nintendo Switch";
                                    break;
                                case "Mobile":
                                    iconPath = "mobile.svg";
                                    iconName = "Mobile";
                                    break;
                                case "VR":
                                    iconPath = "vr.svg";
                                    iconName = "VR";
                                    break;
                                case "Mac":
                                    iconPath = "mac.svg";
                                    iconName = "Mac";
                                    break;
                                case "Linux":
                                    iconPath = "linux.svg";
                                    iconName = "Linux";
                                    break;
                                default:
                                    break;
                            }
                    %>
                    <li class="ps">
                        <img src="<%= getServletContext().getContextPath()%>/assets/img/platforms/<%= iconPath%>" alt="<%= iconName%>">
                    </li>
                    <% }%>
                </ul>
                <div class="card__price">
                    <span><%= g.getPrice()%></span>
                </div>
                <div class="card__actions">
                    <button class="card__buy" type="button">Buy now</button>

                    <button class="card__favorite" type="button">
                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M352.92,80C288,80,256,144,256,144s-32-64-96.92-64C106.32,80,64.54,124.14,64,176.81c-1.1,109.33,86.73,187.08,183,252.42a16,16,0,0,0,18,0c96.26-65.34,184.09-143.09,183-252.42C447.46,124.14,405.68,80,352.92,80Z' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- end big card -->
        <% }
                }
            }%>
    </div>
    <!-- end carousel -->
</section>
<!-- end best sellers -->

<!-- New & Trending -->
<section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>New & Trending</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel section__carousel section__carousel--big" id="carousel1">
        <%
            if (gamesByCategory != null && !gamesByCategory.isEmpty()) {
                for (Game g : gamesByCategory) {
                    if (g.getCategories().contains("New & Trending")) {
        %>
        <!-- big card -->
        <div class="card card--big">
            <a href="<%= getServletContext().getContextPath()%>/pages/details.jsp" class="card__cover">
                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
            </a>

            <div class="card__wrap">
                <div class="card__title">
                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= g.getTitle()%></a></h3>
                </div>

                <ul class="card__list">
                    <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                    <li><span>Genres:</span><%= g.getGenres()%></li>
                </ul>

                <ul class="card__platforms">
                    <% for (String platform : g.getPlatforms()) {

                            String iconPath = "";
                            String iconName = "";

                            switch (platform) {
                                case "PC":
                                    iconPath = "pc.svg";
                                    iconName = "PC";
                                    break;
                                case "PlayStation":
                                    iconPath = "playstation.svg";
                                    iconName = "PlayStation";
                                    break;
                                case "Xbox":
                                    iconPath = "xbox.svg";
                                    iconName = "Xbox";
                                    break;
                                case "Nintendo Switch":
                                    iconPath = "nintendo.svg";
                                    iconName = "Nintendo Switch";
                                    break;
                                case "Mobile":
                                    iconPath = "mobile.svg";
                                    iconName = "Mobile";
                                    break;
                                case "VR":
                                    iconPath = "vr.svg";
                                    iconName = "VR";
                                    break;
                                case "Mac":
                                    iconPath = "mac.svg";
                                    iconName = "Mac";
                                    break;
                                case "Linux":
                                    iconPath = "linux.svg";
                                    iconName = "Linux";
                                    break;
                                default:
                                    break;
                            }
                    %>
                    <li class="ps">
                        <img src="<%= getServletContext().getContextPath()%>/assets/img/platforms/<%= iconPath%>" alt="<%= iconName%>">
                    </li>
                    <% }%>
                </ul>
                <div class="card__price">
                    <span><%= g.getPrice()%></span>
                </div>
                <div class="card__actions">
                    <button class="card__buy" type="button">Buy now</button>

                    <button class="card__favorite" type="button">
                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M352.92,80C288,80,256,144,256,144s-32-64-96.92-64C106.32,80,64.54,124.14,64,176.81c-1.1,109.33,86.73,187.08,183,252.42a16,16,0,0,0,18,0c96.26-65.34,184.09-143.09,183-252.42C447.46,124.14,405.68,80,352.92,80Z' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- end big card -->
        <% }
                }
            }%>
    </div>
    <!-- end carousel -->
</section>
<!-- end New & Trending -->


<!-- Pre-Orders -->
<section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Pre-Orders</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel section__carousel section__carousel--big" id="carousel2">
        <%
            if (gamesByCategory != null && !gamesByCategory.isEmpty()) {
                for (Game g : gamesByCategory) {
                    if (g.getCategories().contains("Pre-Orders")) {
        %>
        <!-- big card -->
        <div class="card card--big">
            <a href="<%= getServletContext().getContextPath()%>/pages/details.jsp" class="card__cover">
                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
            </a>

            <div class="card__wrap">
                <div class="card__title">
                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= g.getTitle()%></a></h3>
                </div>

                <ul class="card__list">
                    <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                    <li><span>Genres:</span><%= g.getGenres()%></li>
                </ul>

                <ul class="card__platforms">
                    <% for (String platform : g.getPlatforms()) {

                            String iconPath = "";
                            String iconName = "";

                            switch (platform) {
                                case "PC":
                                    iconPath = "pc.svg";
                                    iconName = "PC";
                                    break;
                                case "PlayStation":
                                    iconPath = "playstation.svg";
                                    iconName = "PlayStation";
                                    break;
                                case "Xbox":
                                    iconPath = "xbox.svg";
                                    iconName = "Xbox";
                                    break;
                                case "Nintendo Switch":
                                    iconPath = "nintendo.svg";
                                    iconName = "Nintendo Switch";
                                    break;
                                case "Mobile":
                                    iconPath = "mobile.svg";
                                    iconName = "Mobile";
                                    break;
                                case "VR":
                                    iconPath = "vr.svg";
                                    iconName = "VR";
                                    break;
                                case "Mac":
                                    iconPath = "mac.svg";
                                    iconName = "Mac";
                                    break;
                                case "Linux":
                                    iconPath = "linux.svg";
                                    iconName = "Linux";
                                    break;
                                default:
                                    break;
                            }
                    %>
                    <li class="ps">
                        <img src="<%= getServletContext().getContextPath()%>/assets/img/platforms/<%= iconPath%>" alt="<%= iconName%>">
                    </li>
                    <% }%>
                </ul>
                <div class="card__price">
                    <span><%= g.getPrice()%></span>
                </div>
                <div class="card__actions">
                    <button class="card__buy" type="button">Buy now</button>

                    <button class="card__favorite" type="button">
                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M352.92,80C288,80,256,144,256,144s-32-64-96.92-64C106.32,80,64.54,124.14,64,176.81c-1.1,109.33,86.73,187.08,183,252.42a16,16,0,0,0,18,0c96.26-65.34,184.09-143.09,183-252.42C447.46,124.14,405.68,80,352.92,80Z' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- end big card -->
        <% }
                }
            }%>
    </div>
    <!-- end carousel -->
</section>
<!-- end Pre-Orders -->

<!-- Top Rated -->
<section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Top Rated</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel section__carousel section__carousel--big" id="carousel3">
        <%
            if (gamesByCategory != null && !gamesByCategory.isEmpty()) {
                for (Game g : gamesByCategory) {
                    if (g.getCategories().contains("Top Rated")) {
        %>
        <!-- big card -->
        <div class="card card--big">
            <a href="<%= getServletContext().getContextPath()%>/pages/details.jsp" class="card__cover">
                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
            </a>

            <div class="card__wrap">
                <div class="card__title">
                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= g.getTitle()%></a></h3>
                </div>

                <ul class="card__list">
                    <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                    <li><span>Genres:</span><%= g.getGenres()%></li>
                </ul>

                <ul class="card__platforms">
                    <% for (String platform : g.getPlatforms()) {

                            String iconPath = "";
                            String iconName = "";

                            switch (platform) {
                                case "PC":
                                    iconPath = "pc.svg";
                                    iconName = "PC";
                                    break;
                                case "PlayStation":
                                    iconPath = "playstation.svg";
                                    iconName = "PlayStation";
                                    break;
                                case "Xbox":
                                    iconPath = "xbox.svg";
                                    iconName = "Xbox";
                                    break;
                                case "Nintendo Switch":
                                    iconPath = "nintendo.svg";
                                    iconName = "Nintendo Switch";
                                    break;
                                case "Mobile":
                                    iconPath = "mobile.svg";
                                    iconName = "Mobile";
                                    break;
                                case "VR":
                                    iconPath = "vr.svg";
                                    iconName = "VR";
                                    break;
                                case "Mac":
                                    iconPath = "mac.svg";
                                    iconName = "Mac";
                                    break;
                                case "Linux":
                                    iconPath = "linux.svg";
                                    iconName = "Linux";
                                    break;
                                default:
                                    break;
                            }
                    %>
                    <li class="ps">
                        <img src="<%= getServletContext().getContextPath()%>/assets/img/platforms/<%= iconPath%>" alt="<%= iconName%>">
                    </li>
                    <% }%>
                </ul>
                <div class="card__price">
                    <span><%= g.getPrice()%></span>
                </div>
                <div class="card__actions">
                    <button class="card__buy" type="button">Buy now</button>

                    <button class="card__favorite" type="button">
                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M352.92,80C288,80,256,144,256,144s-32-64-96.92-64C106.32,80,64.54,124.14,64,176.81c-1.1,109.33,86.73,187.08,183,252.42a16,16,0,0,0,18,0c96.26-65.34,184.09-143.09,183-252.42C447.46,124.14,405.68,80,352.92,80Z' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- end big card -->
        <% }
                }
            }%>
    </div>
    <!-- end carousel -->
</section>
<!-- end Top Rated -->

<!-- Cozy & Casual -->
<section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Cozy & Casual</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel section__carousel section__carousel--big" id="carousel4">
        <%
            if (gamesByCategory != null && !gamesByCategory.isEmpty()) {
                for (Game g : gamesByCategory) {
                    if (g.getCategories().contains("Cozy & Casual")) {
        %>
        <!-- big card -->
        <div class="card card--big">
            <a href="<%= getServletContext().getContextPath()%>/pages/details.jsp" class="card__cover">
                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
            </a>

            <div class="card__wrap">
                <div class="card__title">
                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= g.getTitle()%></a></h3>
                </div>

                <ul class="card__list">
                    <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                    <li><span>Genres:</span><%= g.getGenres()%></li>
                </ul>

                <ul class="card__platforms">
                    <% for (String platform : g.getPlatforms()) {

                            String iconPath = "";
                            String iconName = "";

                            switch (platform) {
                                case "PC":
                                    iconPath = "pc.svg";
                                    iconName = "PC";
                                    break;
                                case "PlayStation":
                                    iconPath = "playstation.svg";
                                    iconName = "PlayStation";
                                    break;
                                case "Xbox":
                                    iconPath = "xbox.svg";
                                    iconName = "Xbox";
                                    break;
                                case "Nintendo Switch":
                                    iconPath = "nintendo.svg";
                                    iconName = "Nintendo Switch";
                                    break;
                                case "Mobile":
                                    iconPath = "mobile.svg";
                                    iconName = "Mobile";
                                    break;
                                case "VR":
                                    iconPath = "vr.svg";
                                    iconName = "VR";
                                    break;
                                case "Mac":
                                    iconPath = "mac.svg";
                                    iconName = "Mac";
                                    break;
                                case "Linux":
                                    iconPath = "linux.svg";
                                    iconName = "Linux";
                                    break;
                                default:
                                    break;
                            }
                    %>
                    <li class="ps">
                        <img src="<%= getServletContext().getContextPath()%>/assets/img/platforms/<%= iconPath%>" alt="<%= iconName%>">
                    </li>
                    <% }%>
                </ul>
                <div class="card__price">
                    <span><%= g.getPrice()%></span>
                </div>
                <div class="card__actions">
                    <button class="card__buy" type="button">Buy now</button>

                    <button class="card__favorite" type="button">
                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M352.92,80C288,80,256,144,256,144s-32-64-96.92-64C106.32,80,64.54,124.14,64,176.81c-1.1,109.33,86.73,187.08,183,252.42a16,16,0,0,0,18,0c96.26-65.34,184.09-143.09,183-252.42C447.46,124.14,405.68,80,352.92,80Z' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- end big card -->
        <% }
                }
            }%>
    </div>
    <!-- end carousel -->
</section>
<!-- end Cozy & Casual -->

<!-- Multiplayer & Online -->
<section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Multiplayer & Online</b> of this month</h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel section__carousel section__carousel--big" id="carousel5">
        <%
            if (gamesByCategory != null && !gamesByCategory.isEmpty()) {
                for (Game g : gamesByCategory) {
                    if (g.getCategories().contains("Multiplayer & Online")) {
        %>
        <!-- big card -->
        <div class="card card--big">
            <a href="<%= getServletContext().getContextPath()%>/pages/details.jsp" class="card__cover">
                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
            </a>

            <div class="card__wrap">
                <div class="card__title">
                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= g.getTitle()%></a></h3>
                </div>

                <ul class="card__list">
                    <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                    <li><span>Genres:</span><%= g.getGenres()%></li>
                </ul>

                <ul class="card__platforms">
                    <% for (String platform : g.getPlatforms()) {

                            String iconPath = "";
                            String iconName = "";

                            switch (platform) {
                                case "PC":
                                    iconPath = "pc.svg";
                                    iconName = "PC";
                                    break;
                                case "PlayStation":
                                    iconPath = "playstation.svg";
                                    iconName = "PlayStation";
                                    break;
                                case "Xbox":
                                    iconPath = "xbox.svg";
                                    iconName = "Xbox";
                                    break;
                                case "Nintendo Switch":
                                    iconPath = "nintendo.svg";
                                    iconName = "Nintendo Switch";
                                    break;
                                case "Mobile":
                                    iconPath = "mobile.svg";
                                    iconName = "Mobile";
                                    break;
                                case "VR":
                                    iconPath = "vr.svg";
                                    iconName = "VR";
                                    break;
                                case "Mac":
                                    iconPath = "mac.svg";
                                    iconName = "Mac";
                                    break;
                                case "Linux":
                                    iconPath = "linux.svg";
                                    iconName = "Linux";
                                    break;
                                default:
                                    break;
                            }
                    %>
                    <li class="ps">
                        <img src="<%= getServletContext().getContextPath()%>/assets/img/platforms/<%= iconPath%>" alt="<%= iconName%>">
                    </li>
                    <% }%>
                </ul>
                <div class="card__price">
                    <span><%= g.getPrice()%></span>
                </div>
                <div class="card__actions">
                    <button class="card__buy" type="button">Buy now</button>

                    <button class="card__favorite" type="button">
                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M352.92,80C288,80,256,144,256,144s-32-64-96.92-64C106.32,80,64.54,124.14,64,176.81c-1.1,109.33,86.73,187.08,183,252.42a16,16,0,0,0,18,0c96.26-65.34,184.09-143.09,183-252.42C447.46,124.14,405.68,80,352.92,80Z' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- end big card -->
        <% }
                }
            }%>
    </div>
    <!-- end carousel -->
</section>
<!-- end Multiplayer & Online -->

<!-- Single-Player Favorites -->
<section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Single-Player Favorites</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel section__carousel section__carousel--big" id="carousel6">
        <%
            if (gamesByCategory != null && !gamesByCategory.isEmpty()) {
                for (Game g : gamesByCategory) {
                    if (g.getCategories().contains("Single-Player Favorites")) {
        %>
        <!-- big card -->
        <div class="card card--big">
            <a href="<%= getServletContext().getContextPath()%>/pages/details.jsp" class="card__cover">
                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
            </a>

            <div class="card__wrap">
                <div class="card__title">
                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= g.getTitle()%></a></h3>
                </div>

                <ul class="card__list">
                    <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                    <li><span>Genres:</span><%= g.getGenres()%></li>
                </ul>

                <ul class="card__platforms">
                    <% for (String platform : g.getPlatforms()) {

                            String iconPath = "";
                            String iconName = "";

                            switch (platform) {
                                case "PC":
                                    iconPath = "pc.svg";
                                    iconName = "PC";
                                    break;
                                case "PlayStation":
                                    iconPath = "playstation.svg";
                                    iconName = "PlayStation";
                                    break;
                                case "Xbox":
                                    iconPath = "xbox.svg";
                                    iconName = "Xbox";
                                    break;
                                case "Nintendo Switch":
                                    iconPath = "nintendo.svg";
                                    iconName = "Nintendo Switch";
                                    break;
                                case "Mobile":
                                    iconPath = "mobile.svg";
                                    iconName = "Mobile";
                                    break;
                                case "VR":
                                    iconPath = "vr.svg";
                                    iconName = "VR";
                                    break;
                                case "Mac":
                                    iconPath = "mac.svg";
                                    iconName = "Mac";
                                    break;
                                case "Linux":
                                    iconPath = "linux.svg";
                                    iconName = "Linux";
                                    break;
                                default:
                                    break;
                            }
                    %>
                    <li class="ps">
                        <img src="<%= getServletContext().getContextPath()%>/assets/img/platforms/<%= iconPath%>" alt="<%= iconName%>">
                    </li>
                    <% }%>
                </ul>
                <div class="card__price">
                    <span><%= g.getPrice()%></span>
                </div>
                <div class="card__actions">
                    <button class="card__buy" type="button">Buy now</button>

                    <button class="card__favorite" type="button">
                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M352.92,80C288,80,256,144,256,144s-32-64-96.92-64C106.32,80,64.54,124.14,64,176.81c-1.1,109.33,86.73,187.08,183,252.42a16,16,0,0,0,18,0c96.26-65.34,184.09-143.09,183-252.42C447.46,124.14,405.68,80,352.92,80Z' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- end big card -->
        <% }
                }
            }%>
    </div>
    <!-- end carousel -->
</section>
<!-- end Single-Player Favorites -->

<%@include file="/WEB-INF/include/footer1.jsp" %>

<!-- JS -->
<script src="<%= getServletContext().getContextPath()%>/assets/js/cdn.bootstrap.bundle.min"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/owl.carousel.min.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/jquery.magnific-popup.min.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/wNumb.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/nouislider.min.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/jquery.mousewheel.min.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/jquery.mCustomScrollbar.min.js"></script>
<script src="<%= getServletContext().getContextPath()%>/assets/js/main.js"></script>
</body>
</html>