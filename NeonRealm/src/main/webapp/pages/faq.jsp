<%-- 
    Document   : faq.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>
<!-- page title -->
<section class="section section--first section--last section--head" data-bg="<%= getServletContext().getContextPath() %>/assets/img/bg.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section__wrap">
                    <!-- section title -->
                    <h2 class="section__title">Help center</h2>
                    <!-- end section title -->

                    <!-- breadcrumb -->
                    <ul class="breadcrumb">
                        <li class="breadcrumb__item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb__item breadcrumb__item--active">Help center</li>
                    </ul>
                    <!-- end breadcrumb -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end page title -->

<!-- section -->
<section class="section section--last section--catalog">
    <div class="container">
        <!-- catalog -->
        <div class="row catalog">
            <!-- filter wrap -->
            <div class="col-12 col-lg-20">
                <div class="row">
                    <div class="col-12">
                        <!-- filter -->
                        <div class="filter">
                            <h4 class="filter__title">Help center</h4>

                            <div class="filter__group">
                                <input type="text" class="filter__input" placeholder="Keyword">
                            </div>

                            <div class="filter__group">
                                <label class="filter__label">Navigation:</label>
                                <ul class="filter__nav">
                                    <li><a class="active" href="#">All</a></li>
                                    <li><a href="#">NeonRealm</a></li>
                                    <li><a href="#">Profile</a></li>
                                    <li><a href="#">Categories</a></li>
                                    <li><a href="#">Platforms</a></li>
                                    <li><a href="#">Discounts</a></li>
                                    <li><a href="#">Payment</a></li>
                                    <li><a href="#">Delete account</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end filter -->
                    </div>
                </div>
            </div>
            <!-- end filter wrap -->

            <!-- content wrap -->
            <div class="col-12 col-lg-80">
                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="faq">
                            <h3 class="faq__title">NeonRealm</h3>
                            <ul class="faq__list">
                                <li><a href="#">Many desktop publishing packages and web page?</a></li>
                                <li><a href="#">Various versions have evolved over the years?</a></li>
                                <li><a href="#">The point of using Lorem Ipsum?</a></li>
                                <li><a href="#">The generated Lorem Ipsum?</a></li>
                                <li><a href="#">All the Lorem Ipsum generators on the Internet?</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="faq">
                            <h3 class="faq__title">Profile</h3>
                            <ul class="faq__list">
                                <li><a href="#">Many desktop publishing packages and web page?</a></li>
                                <li><a href="#">Various versions have evolved over the years?</a></li>
                                <li><a href="#">The point of using Lorem Ipsum?</a></li>
                                <li><a href="#">The generated Lorem Ipsum?</a></li>
                                <li><a href="#">All the Lorem Ipsum generators on the Internet?</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="faq">
                            <h3 class="faq__title">Categories</h3>
                            <ul class="faq__list">
                                <li><a href="#">Many desktop publishing packages and web page?</a></li>
                                <li><a href="#">Various versions have evolved over the years?</a></li>
                                <li><a href="#">The point of using Lorem Ipsum?</a></li>
                                <li><a href="#">The generated Lorem Ipsum?</a></li>
                                <li><a href="#">All the Lorem Ipsum generators on the Internet?</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="faq">
                            <h3 class="faq__title">Platforms</h3>
                            <ul class="faq__list">
                                <li><a href="#">Many desktop publishing packages and web page?</a></li>
                                <li><a href="#">Various versions have evolved over the years?</a></li>
                                <li><a href="#">The point of using Lorem Ipsum?</a></li>
                                <li><a href="#">The generated Lorem Ipsum?</a></li>
                                <li><a href="#">All the Lorem Ipsum generators on the Internet?</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="faq">
                            <h3 class="faq__title">Discounts</h3>
                            <ul class="faq__list">
                                <li><a href="#">Many desktop publishing packages and web page?</a></li>
                                <li><a href="#">Various versions have evolved over the years?</a></li>
                                <li><a href="#">The point of using Lorem Ipsum?</a></li>
                                <li><a href="#">The generated Lorem Ipsum?</a></li>
                                <li><a href="#">All the Lorem Ipsum generators on the Internet?</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="faq">
                            <h3 class="faq__title">Payment</h3>
                            <ul class="faq__list">
                                <li><a href="#">Many desktop publishing packages and web page?</a></li>
                                <li><a href="#">Various versions have evolved over the years?</a></li>
                                <li><a href="#">The point of using Lorem Ipsum?</a></li>
                                <li><a href="#">The generated Lorem Ipsum?</a></li>
                                <li><a href="#">All the Lorem Ipsum generators on the Internet?</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end content wrap -->
        </div>
        <!-- end catalog -->	
    </div>
</section>
<!-- end section -->

<%@include file="/WEB-INF/include/footer1.jsp" %>
<%@include file="/WEB-INF/include/footer2.jsp" %>