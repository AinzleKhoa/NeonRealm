<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.ArrayList"%>
<%@page import="gameshop.model.Game"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/cdn.css.bootstrap.min">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/bootstrap-reboot.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/nouislider.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/jquery.mCustomScrollbar.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/paymentfont.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/main.css">

        <!-- Favicons -->
        <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/assets/icon/favicon-32x32.png" sizes="32x32">
        <link rel="apple-touch-icon" href="${pageContext.servletContext.contextPath}/assets/icon/favicon-32x32.png">

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
                        <figure><img src="${pageContext.servletContext.contextPath}/assets/img/banner/spiderman.png" alt="#"/></figure>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</header>
<!-- end banner -->

<!-- best sellers -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/img/bg.jpg">
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
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel0">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Best Sellers')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

                            <ul class="card__platforms">
                                <c:forEach var="platform" items="${g.platforms}">
                                    <c:set var="iconPath" value=""/>
                                    <c:set var="iconName" value=""/>
                                    <c:choose>
                                        <c:when test="${platform == 'PC'}">
                                            <c:set var="iconPath" value="pc.svg" />
                                            <c:set var="iconName" value="PC" />
                                        </c:when>
                                        <c:when test="${platform == 'PlayStation'}">
                                            <c:set var="iconPath" value="playstation.svg" />
                                            <c:set var="iconName" value="PlayStation" />
                                        </c:when>
                                        <c:when test="${platform == 'Xbox'}">
                                            <c:set var="iconPath" value="xbox.svg" />
                                            <c:set var="iconName" value="Xbox" />
                                        </c:when>
                                        <c:when test="${platform == 'Nintendo Switch'}">
                                            <c:set var="iconPath" value="nintendo.svg" />
                                            <c:set var="iconName" value="Nintendo Switch" />
                                        </c:when>
                                        <c:when test="${platform == 'Mobile'}">
                                            <c:set var="iconPath" value="mobile.svg" />
                                            <c:set var="iconName" value="Mobile" />
                                        </c:when>
                                        <c:when test="${platform == 'VR'}">
                                            <c:set var="iconPath" value="vr.svg" />
                                            <c:set var="iconName" value="VR" />
                                        </c:when>
                                        <c:when test="${platform == 'Mac'}">
                                            <c:set var="iconPath" value="mac.svg" />
                                            <c:set var="iconName" value="Mac" />
                                        </c:when>
                                        <c:when test="${platform == 'Linux'}">
                                            <c:set var="iconPath" value="linux.svg" />
                                            <c:set var="iconName" value="Linux" />
                                        </c:when>
                                    </c:choose>
                                    <li class="ps">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                    </li>
                                </c:forEach>
                            </ul>

                            <div class="card__price">
                                <span>$ ${g.price}</span>
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
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end best sellers -->

<!-- New & Trending -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>New & Trending</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel1">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel1">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel1">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('New & Trending')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

                            <ul class="card__platforms">
                                <c:forEach var="platform" items="${g.platforms}">
                                    <c:set var="iconPath" value=""/>
                                    <c:set var="iconName" value=""/>
                                    <c:choose>
                                        <c:when test="${platform == 'PC'}">
                                            <c:set var="iconPath" value="pc.svg" />
                                            <c:set var="iconName" value="PC" />
                                        </c:when>
                                        <c:when test="${platform == 'PlayStation'}">
                                            <c:set var="iconPath" value="playstation.svg" />
                                            <c:set var="iconName" value="PlayStation" />
                                        </c:when>
                                        <c:when test="${platform == 'Xbox'}">
                                            <c:set var="iconPath" value="xbox.svg" />
                                            <c:set var="iconName" value="Xbox" />
                                        </c:when>
                                        <c:when test="${platform == 'Nintendo Switch'}">
                                            <c:set var="iconPath" value="nintendo.svg" />
                                            <c:set var="iconName" value="Nintendo Switch" />
                                        </c:when>
                                        <c:when test="${platform == 'Mobile'}">
                                            <c:set var="iconPath" value="mobile.svg" />
                                            <c:set var="iconName" value="Mobile" />
                                        </c:when>
                                        <c:when test="${platform == 'VR'}">
                                            <c:set var="iconPath" value="vr.svg" />
                                            <c:set var="iconName" value="VR" />
                                        </c:when>
                                        <c:when test="${platform == 'Mac'}">
                                            <c:set var="iconPath" value="mac.svg" />
                                            <c:set var="iconName" value="Mac" />
                                        </c:when>
                                        <c:when test="${platform == 'Linux'}">
                                            <c:set var="iconPath" value="linux.svg" />
                                            <c:set var="iconName" value="Linux" />
                                        </c:when>
                                    </c:choose>
                                    <li class="ps">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                    </li>
                                </c:forEach>
                            </ul>

                            <div class="card__price">
                                <span>$ ${g.price}</span>
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
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end New & Trending -->

<!-- Pre-Orders -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Pre-Orders</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel2">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel2">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel2">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Pre-Orders')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

                            <ul class="card__platforms">
                                <c:forEach var="platform" items="${g.platforms}">
                                    <c:set var="iconPath" value=""/>
                                    <c:set var="iconName" value=""/>
                                    <c:choose>
                                        <c:when test="${platform == 'PC'}">
                                            <c:set var="iconPath" value="pc.svg" />
                                            <c:set var="iconName" value="PC" />
                                        </c:when>
                                        <c:when test="${platform == 'PlayStation'}">
                                            <c:set var="iconPath" value="playstation.svg" />
                                            <c:set var="iconName" value="PlayStation" />
                                        </c:when>
                                        <c:when test="${platform == 'Xbox'}">
                                            <c:set var="iconPath" value="xbox.svg" />
                                            <c:set var="iconName" value="Xbox" />
                                        </c:when>
                                        <c:when test="${platform == 'Nintendo Switch'}">
                                            <c:set var="iconPath" value="nintendo.svg" />
                                            <c:set var="iconName" value="Nintendo Switch" />
                                        </c:when>
                                        <c:when test="${platform == 'Mobile'}">
                                            <c:set var="iconPath" value="mobile.svg" />
                                            <c:set var="iconName" value="Mobile" />
                                        </c:when>
                                        <c:when test="${platform == 'VR'}">
                                            <c:set var="iconPath" value="vr.svg" />
                                            <c:set var="iconName" value="VR" />
                                        </c:when>
                                        <c:when test="${platform == 'Mac'}">
                                            <c:set var="iconPath" value="mac.svg" />
                                            <c:set var="iconName" value="Mac" />
                                        </c:when>
                                        <c:when test="${platform == 'Linux'}">
                                            <c:set var="iconPath" value="linux.svg" />
                                            <c:set var="iconName" value="Linux" />
                                        </c:when>
                                    </c:choose>
                                    <li class="ps">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                    </li>
                                </c:forEach>
                            </ul>

                            <div class="card__price">
                                <span>$ ${g.price}</span>
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
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Pre-Orders -->

<!-- Top Rated -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Top Rated</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel3">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel3">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel3">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Top Rated')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

                            <ul class="card__platforms">
                                <c:forEach var="platform" items="${g.platforms}">
                                    <c:set var="iconPath" value=""/>
                                    <c:set var="iconName" value=""/>
                                    <c:choose>
                                        <c:when test="${platform == 'PC'}">
                                            <c:set var="iconPath" value="pc.svg" />
                                            <c:set var="iconName" value="PC" />
                                        </c:when>
                                        <c:when test="${platform == 'PlayStation'}">
                                            <c:set var="iconPath" value="playstation.svg" />
                                            <c:set var="iconName" value="PlayStation" />
                                        </c:when>
                                        <c:when test="${platform == 'Xbox'}">
                                            <c:set var="iconPath" value="xbox.svg" />
                                            <c:set var="iconName" value="Xbox" />
                                        </c:when>
                                        <c:when test="${platform == 'Nintendo Switch'}">
                                            <c:set var="iconPath" value="nintendo.svg" />
                                            <c:set var="iconName" value="Nintendo Switch" />
                                        </c:when>
                                        <c:when test="${platform == 'Mobile'}">
                                            <c:set var="iconPath" value="mobile.svg" />
                                            <c:set var="iconName" value="Mobile" />
                                        </c:when>
                                        <c:when test="${platform == 'VR'}">
                                            <c:set var="iconPath" value="vr.svg" />
                                            <c:set var="iconName" value="VR" />
                                        </c:when>
                                        <c:when test="${platform == 'Mac'}">
                                            <c:set var="iconPath" value="mac.svg" />
                                            <c:set var="iconName" value="Mac" />
                                        </c:when>
                                        <c:when test="${platform == 'Linux'}">
                                            <c:set var="iconPath" value="linux.svg" />
                                            <c:set var="iconName" value="Linux" />
                                        </c:when>
                                    </c:choose>
                                    <li class="ps">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                    </li>
                                </c:forEach>
                            </ul>

                            <div class="card__price">
                                <span>$ ${g.price}</span>
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
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Top Rated -->

<!-- Cozy & Casual -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Cozy & Casual</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel4">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel4">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel4">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Cozy & Casual')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

                            <ul class="card__platforms">
                                <c:forEach var="platform" items="${g.platforms}">
                                    <c:set var="iconPath" value=""/>
                                    <c:set var="iconName" value=""/>
                                    <c:choose>
                                        <c:when test="${platform == 'PC'}">
                                            <c:set var="iconPath" value="pc.svg" />
                                            <c:set var="iconName" value="PC" />
                                        </c:when>
                                        <c:when test="${platform == 'PlayStation'}">
                                            <c:set var="iconPath" value="playstation.svg" />
                                            <c:set var="iconName" value="PlayStation" />
                                        </c:when>
                                        <c:when test="${platform == 'Xbox'}">
                                            <c:set var="iconPath" value="xbox.svg" />
                                            <c:set var="iconName" value="Xbox" />
                                        </c:when>
                                        <c:when test="${platform == 'Nintendo Switch'}">
                                            <c:set var="iconPath" value="nintendo.svg" />
                                            <c:set var="iconName" value="Nintendo Switch" />
                                        </c:when>
                                        <c:when test="${platform == 'Mobile'}">
                                            <c:set var="iconPath" value="mobile.svg" />
                                            <c:set var="iconName" value="Mobile" />
                                        </c:when>
                                        <c:when test="${platform == 'VR'}">
                                            <c:set var="iconPath" value="vr.svg" />
                                            <c:set var="iconName" value="VR" />
                                        </c:when>
                                        <c:when test="${platform == 'Mac'}">
                                            <c:set var="iconPath" value="mac.svg" />
                                            <c:set var="iconName" value="Mac" />
                                        </c:when>
                                        <c:when test="${platform == 'Linux'}">
                                            <c:set var="iconPath" value="linux.svg" />
                                            <c:set var="iconName" value="Linux" />
                                        </c:when>
                                    </c:choose>
                                    <li class="ps">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                    </li>
                                </c:forEach>
                            </ul>

                            <div class="card__price">
                                <span>$ ${g.price}</span>
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
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Cozy & Casual -->

<!-- Multiplayer & Online -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Multiplayer & Online</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel5">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel5">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel5">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Multiplayer & Online')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

                            <ul class="card__platforms">
                                <c:forEach var="platform" items="${g.platforms}">
                                    <c:set var="iconPath" value=""/>
                                    <c:set var="iconName" value=""/>
                                    <c:choose>
                                        <c:when test="${platform == 'PC'}">
                                            <c:set var="iconPath" value="pc.svg" />
                                            <c:set var="iconName" value="PC" />
                                        </c:when>
                                        <c:when test="${platform == 'PlayStation'}">
                                            <c:set var="iconPath" value="playstation.svg" />
                                            <c:set var="iconName" value="PlayStation" />
                                        </c:when>
                                        <c:when test="${platform == 'Xbox'}">
                                            <c:set var="iconPath" value="xbox.svg" />
                                            <c:set var="iconName" value="Xbox" />
                                        </c:when>
                                        <c:when test="${platform == 'Nintendo Switch'}">
                                            <c:set var="iconPath" value="nintendo.svg" />
                                            <c:set var="iconName" value="Nintendo Switch" />
                                        </c:when>
                                        <c:when test="${platform == 'Mobile'}">
                                            <c:set var="iconPath" value="mobile.svg" />
                                            <c:set var="iconName" value="Mobile" />
                                        </c:when>
                                        <c:when test="${platform == 'VR'}">
                                            <c:set var="iconPath" value="vr.svg" />
                                            <c:set var="iconName" value="VR" />
                                        </c:when>
                                        <c:when test="${platform == 'Mac'}">
                                            <c:set var="iconPath" value="mac.svg" />
                                            <c:set var="iconName" value="Mac" />
                                        </c:when>
                                        <c:when test="${platform == 'Linux'}">
                                            <c:set var="iconPath" value="linux.svg" />
                                            <c:set var="iconName" value="Linux" />
                                        </c:when>
                                    </c:choose>
                                    <li class="ps">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                    </li>
                                </c:forEach>
                            </ul>

                            <div class="card__price">
                                <span>$ ${g.price}</span>
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
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Multiplayer & Online -->

<!-- Single-Player Favorites -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/img/bg.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Single-Player Favorites</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel6">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel6">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel6">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Single-Player Favorites')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

                            <ul class="card__platforms">
                                <c:forEach var="platform" items="${g.platforms}">
                                    <c:set var="iconPath" value=""/>
                                    <c:set var="iconName" value=""/>
                                    <c:choose>
                                        <c:when test="${platform == 'PC'}">
                                            <c:set var="iconPath" value="pc.svg" />
                                            <c:set var="iconName" value="PC" />
                                        </c:when>
                                        <c:when test="${platform == 'PlayStation'}">
                                            <c:set var="iconPath" value="playstation.svg" />
                                            <c:set var="iconName" value="PlayStation" />
                                        </c:when>
                                        <c:when test="${platform == 'Xbox'}">
                                            <c:set var="iconPath" value="xbox.svg" />
                                            <c:set var="iconName" value="Xbox" />
                                        </c:when>
                                        <c:when test="${platform == 'Nintendo Switch'}">
                                            <c:set var="iconPath" value="nintendo.svg" />
                                            <c:set var="iconName" value="Nintendo Switch" />
                                        </c:when>
                                        <c:when test="${platform == 'Mobile'}">
                                            <c:set var="iconPath" value="mobile.svg" />
                                            <c:set var="iconName" value="Mobile" />
                                        </c:when>
                                        <c:when test="${platform == 'VR'}">
                                            <c:set var="iconPath" value="vr.svg" />
                                            <c:set var="iconName" value="VR" />
                                        </c:when>
                                        <c:when test="${platform == 'Mac'}">
                                            <c:set var="iconPath" value="mac.svg" />
                                            <c:set var="iconName" value="Mac" />
                                        </c:when>
                                        <c:when test="${platform == 'Linux'}">
                                            <c:set var="iconPath" value="linux.svg" />
                                            <c:set var="iconName" value="Linux" />
                                        </c:when>
                                    </c:choose>
                                    <li class="ps">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                    </li>
                                </c:forEach>
                            </ul>

                            <div class="card__price">
                                <span>$ ${g.price}</span>
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
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Single-Player Favorites -->

<%@include file="/WEB-INF/include/footer1.jsp" %>

<!-- JS -->
<script src="${pageContext.servletContext.contextPath}/assets/js/cdn.bootstrap.bundle.min"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/owl.carousel.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/wNumb.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/nouislider.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery.mousewheel.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery.mCustomScrollbar.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
</body>
</html>