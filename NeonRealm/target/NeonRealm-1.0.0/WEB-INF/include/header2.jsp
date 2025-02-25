<%-- 
    Document   : header
    Created on : Feb 15, 2025, 9:46:24 AM
    Author     : Ainzle
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <body>
        <!-- header -->
        <header class="header">
            <div class="header__wrap">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="header__content">
                                <button class="header__menu" type="button">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </button>

                                <a href="<%= getServletContext().getContextPath()%>/index.jsp" class="header__logo">
                                    <img src="<%= getServletContext().getContextPath()%>/assets/img/logo.png" alt="">
                                </a>

                                <ul class="header__nav">
                                    <li class="header__nav-item">
                                        <a class="header__nav-link" href="<%= getServletContext().getContextPath()%>/index.jsp">Home</a>
                                    </li>								
                                    <li class="header__nav-item">
                                        <a class="header__nav-link" href="<%= getServletContext().getContextPath() %>/pages/catalog.jsp">Catalog</a>
                                    </li>
                                    <li class="header__nav-item">
                                        <a class="header__nav-link" href="<%= getServletContext().getContextPath() %>/pages/faq.jsp">Help Center</a>
                                    </li>
                                    <li class="header__nav-item">
                                        <a class="header__nav-link header__nav-link--more" href="#" role="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='256' cy='256' r='32' style='fill:none; stroke-miterlimit:10;stroke-width:32px'/><circle cx='416' cy='256' r='32' style='fill:none;stroke-miterlimit:10;stroke-width:32px'/><circle cx='96' cy='256' r='32' style='fill:none;stroke-miterlimit:10;stroke-width:32px'/></svg>
                                        </a>

                                        <ul class="dropdown-menu header__nav-menu header__nav-menu--scroll" aria-labelledby="dropdownMenu3">
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/checkout.jsp">Checkout</a></li>
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/about.jsp">About</a></li>
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/profile.jsp">Profile</a></li>
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/signin.jsp">Sign in</a></li>
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/signup.jsp">Sign up</a></li>
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/forgot.jsp">Forgot password</a></li>
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/privacy.jsp">Privacy policy</a></li>
                                            <li><a href="<%= getServletContext().getContextPath() %>/pages/404.jsp">404 Page</a></li>
                                        </ul>
                                    </li>
                                </ul>

                                <div class="header__actions">
                                    <div class="header__lang">
                                        <a href="signin.jsp" class="header__login">
                                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M192,176V136a40,40,0,0,1,40-40H392a40,40,0,0,1,40,40V376a40,40,0,0,1-40,40H240c-22.09,0-48-17.91-48-40V336' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='288 336 368 256 288 176' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><line x1='80' y1='256' x2='352' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                            <span>Sign in</span>
                                        </a>
                                        <a href="checkout.jsp" class="header__link">
                                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                            <span>$00.00</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </header>
        <!-- end header -->