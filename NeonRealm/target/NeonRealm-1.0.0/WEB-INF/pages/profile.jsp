<%-- 
    Document   : profile.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>

<c:set var="user" value="${sessionScope.currentUser}"/>
<!-- page title -->
<section class="section section--first section--last section--head" data-bg="${pageContext.servletContext.contextPath}/assets/img/bg.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section__wrap">
                    <!-- section title -->
                    <h2 class="section__title">Profile</h2>
                    <!-- end section title -->

                    <!-- breadcrumb -->
                    <ul class="breadcrumb">
                        <li class="breadcrumb__item"><a href="${pageContext.servletContext.contextPath}/home">Home</a></li>
                        <li class="breadcrumb__item breadcrumb__item--active">Profile</li>
                    </ul>
                    <!-- end breadcrumb -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end page title -->

<!-- section -->
<section class="section section--last">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="profile">
                    <div class="profile__user">
                        <div class="profile__avatar">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/user.svg" alt="">
                        </div>
                        <div class="profile__meta">
                            <h3>${fn:toUpperCase(user.username)}</h3>
                            <span>ID: ${user.userId}</span>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${empty requestScope.errors}">
                            <c:set var="activeTab" value="tab-1"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="activeTab" value="tab-2"/>
                        </c:otherwise>
                    </c:choose>

                    <ul class="nav nav-tabs profile__tabs" id="profile__tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link ${activeTab == 'tab-1' ? 'active' : ''}" 
                               data-toggle="tab" href="#tab-1" role="tab" 
                               aria-controls="tab-1" aria-selected="${activeTab == 'tab-1' ? 'true' : 'false'}">
                                My purchases
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link ${activeTab == 'tab-2' ? 'active' : ''}" 
                               data-toggle="tab" href="#tab-2" role="tab" 
                               aria-controls="tab-2" aria-selected="${activeTab == 'tab-2' ? 'true' : 'false'}">
                                Settings
                            </a>
                        </li>
                    </ul>

                    <button class="profile__logout" type="button" onclick="confirmLogout()">
                        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 512 512'><path d='M304 336v40a40 40 0 01-40 40H104a40 40 0 01-40-40V136a40 40 0 0140-40h152c22.09 0 48 17.91 48 40v40M368 336l80-80-80-80M176 256h256' fill='none' stroke-linecap='round' stroke-linejoin='round' stroke-width='32'/></svg>
                        <span>Logout</span>
                    </button>
                </div>
            </div>	
        </div>

        <!-- Logout Confirmation Modal -->
        <div class="container profile__modal" id="logoutModal">
            <div class="row">
                <div class="col-12">
                    <div class="profile__modal__content">
                        <p>Are you sure you want to log out?</p>
                        <a class="btn-danger" type="button" href="${pageContext.servletContext.contextPath}/logout">Yes</a>
                        <button class="btn-primary" onclick="closeModal()">Cancel</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <!-- content tabs -->
            <div class="tab-content">
                <div class="tab-pane fade ${activeTab == 'tab-1' ? 'show active' : ''}" id="tab-1" role="tabpanel">
                    <div class="row">
                        <div class="col-12">
                            <div class="table-responsive table-responsive--border">
                                <table class="profile__table">
                                    <thead>
                                        <tr>
                                            <th>№</th>
                                            <th><a href="#">Product <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.71,10.21,12,7.91l2.29,2.3a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42l-3-3a1,1,0,0,0-1.42,0l-3,3a1,1,0,0,0,1.42,1.42Zm4.58,4.58L12,17.09l-2.29-2.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,1.42,0l3-3a1,1,0,0,0-1.42-1.42Z"/></svg></a></th>
                                            <th><a href="#" class="active">Title <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M17,13.41,12.71,9.17a1,1,0,0,0-1.42,0L7.05,13.41a1,1,0,0,0,0,1.42,1,1,0,0,0,1.41,0L12,11.29l3.54,3.54a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29A1,1,0,0,0,17,13.41Z"/></svg></a></th>
                                            <th><a href="#">Platform <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.71,10.21,12,7.91l2.29,2.3a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42l-3-3a1,1,0,0,0-1.42,0l-3,3a1,1,0,0,0,1.42,1.42Zm4.58,4.58L12,17.09l-2.29-2.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,1.42,0l3-3a1,1,0,0,0-1.42-1.42Z"/></svg></a></th>
                                            <th><a href="#" class="active">Date <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M17,9.17a1,1,0,0,0-1.41,0L12,12.71,8.46,9.17a1,1,0,0,0-1.41,0,1,1,0,0,0,0,1.42l4.24,4.24a1,1,0,0,0,1.42,0L17,10.59A1,1,0,0,0,17,9.17Z"/></svg></a></th>
                                            <th><a href="#">Price <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.71,10.21,12,7.91l2.29,2.3a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42l-3-3a1,1,0,0,0-1.42,0l-3,3a1,1,0,0,0,1.42,1.42Zm4.58,4.58L12,17.09l-2.29-2.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,1.42,0l3-3a1,1,0,0,0-1.42-1.42Z"/></svg></a></th>
                                            <th><a href="#">Status <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.71,10.21,12,7.91l2.29,2.3a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42l-3-3a1,1,0,0,0-1.42,0l-3,3a1,1,0,0,0,1.42,1.42Zm4.58,4.58L12,17.09l-2.29-2.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,1.42,0l3-3a1,1,0,0,0-1.42-1.42Z"/></svg></a></th>
                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td><a href="#modal-info" class="open-modal">8451</a></td>
                                            <td>
                                                <div class="profile__img">
                                                    <img src="${pageContext.servletContext.contextPath}/assets/img/cards/5.jpg" alt="">
                                                </div>
                                            </td>
                                            <td>Desperados III Digital Deluxe Edition</td>
                                            <td>XBOX</td>
                                            <td>Aug 22, 2021</td>
                                            <td><span class="profile__price">$49.00</span></td>
                                            <td><span class="profile__status">Not confirmed</span></td>
                                            <td><button class="profile__delete" type="button"><svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><line x1='368' y1='368' x2='144' y2='144' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><line x1='368' y1='144' x2='144' y2='368' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg></button></td>
                                        </tr>
                                        <tr>
                                            <td><a href="#modal-info" class="open-modal">8126</a></td>
                                            <td>
                                                <div class="profile__img">
                                                    <img src="${pageContext.servletContext.contextPath}/assets/img/cards/7.jpg" alt="">
                                                </div>
                                            </td>
                                            <td>Red Dead Redemption 2</td>
                                            <td>PC</td>
                                            <td>July 22, 2021</td>
                                            <td><span class="profile__price">$59.00</span></td>
                                            <td><span class="profile__status profile__status--confirmed">Confirmed</span></td>
                                            <td><button class="profile__delete" type="button"><svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><line x1='368' y1='368' x2='144' y2='144' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><line x1='368' y1='144' x2='144' y2='368' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg></button></td>
                                        </tr>
                                        <tr>
                                            <td><a href="#modal-info" class="open-modal">7314</a></td>
                                            <td>
                                                <div class="profile__img">
                                                    <img src="${pageContext.servletContext.contextPath}/assets/img/cards/3.jpg" alt="">
                                                </div>
                                            </td>
                                            <td>Baldur's Gate II: Enhanced Edition</td>
                                            <td>PC</td>
                                            <td>June 12, 2020</td>
                                            <td><span class="profile__price">$38.99</span></td>
                                            <td><span class="profile__status profile__status--cenceled">Canceled</span></td>
                                            <td><button class="profile__delete" type="button"><svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><line x1='368' y1='368' x2='144' y2='144' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><line x1='368' y1='144' x2='144' y2='368' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg></button></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- paginator -->
                        <div class="col-12">
                            <div class="paginator">
                                <div class="paginator__counter">
                                    3 from 9
                                </div>

                                <ul class="paginator__wrap">
                                    <li class="paginator__item paginator__item--prev">
                                        <a href="#">
                                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='244 400 100 256 244 112' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/><line x1='120' y1='256' x2='412' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                                        </a>
                                    </li>
                                    <li class="paginator__item paginator__item--active"><a href="#">1</a></li>
                                    <li class="paginator__item"><a href="#">2</a></li>
                                    <li class="paginator__item"><a href="#">3</a></li>
                                    <li class="paginator__item paginator__item--next">
                                        <a href="#">
                                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='268 112 412 256 268 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/><line x1='392' y1='256' x2='100' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- end paginator -->
                    </div>
                </div>

                <div class="tab-pane fade ${activeTab == 'tab-2' ? 'show active' : ''}" id="tab-2" role="tabpanel">
                    <div class="row">
                        <!-- details form -->
                        <div class="col-12 col-lg-6">
                            <form action="${pageContext.servletContext.contextPath}/profile" method="POST" class="form">
                                <div class="row">
                                    <div class="col-12">
                                        <h4 class="form__title">Profile details</h4>
                                    </div>
                                    <input hidden name="id" value="${user.userId}"/>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="username">Username</label>
                                        <input id="username" type="text" name="username" class="form__input" placeholder="${user.username}" value="${user.username}" required>
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="email">Email</label>
                                        <input id="email" type="text" name="email" class="form__input" placeholder="${user.email}" value="${user.email}" required>
                                    </div>

                                    <div class="col-12">
                                        <h4 class="form__title">Change password</h4>
                                    </div>

                                    <div class="col-12">
                                        <label class="form__label" for="oldpass">Old Password (Required to update profile)</label>
                                        <input id="oldpass" type="password" name="oldpass" class="form__input" required>
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="newpass">New Password</label>
                                        <input id="newpass" type="password" name="newpass" class="form__input">
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="confirmpass">Confirm New Password</label>
                                        <input id="confirmpass" type="password" name="confirmnewpass" class="form__input">
                                    </div>
                                    <%--
                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="firstname">First Name</label>
                                        <input id="firstname" type="text" name="firstname" class="form__input" placeholder="${user.fullName}">
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="lastname">Last Name</label>
                                        <input id="lastname" type="text" name="lastname" class="form__input" placeholder="${user.fullName}">
                                    </div>
                                    --%>

                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <c:choose>
                                            <c:when test="${not empty requestScope.errors}">
                                                <div style="color: red;">
                                                    <c:forEach var="error" items="${requestScope.errors}">
                                                        <p>${error}</p>
                                                    </c:forEach>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${not empty requestScope.success}"><p style="color: greenyellow;">${requestScope.success}</p></c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="col-12">
                                        <button class="form__btn" type="submit">Save</button>
                                    </div>

                                </div>
                        </div>
                        <!-- end details form -->

                        <%--
                        <!-- password form -->
                        <div class="col-12 col-lg-6">
                                <div class="row">
                                    <div class="col-12">
                                        <h4 class="form__title">Change password</h4>
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="oldpass">Old Password</label>
                                        <input id="oldpass" type="password" name="oldpass" class="form__input">
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="newpass">New Password</label>
                                        <input id="newpass" type="password" name="newpass" class="form__input">
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="confirmpass">Confirm New Password</label>
                                        <input id="confirmpass" type="password" name="confirmpass" class="form__input">
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="select">Select</label>
                                        <select name="select" id="select" class="form__select">
                                            <option value="0">Option</option>
                                            <option value="1">Option 2</option>
                                            <option value="2">Option 3</option>
                                            <option value="3">Option 4</option>
                                        </select>
                                    </div>

                                    <div class="col-12">
                                        <button class="form__btn" type="button">Change</button>
                                    </div>
                                </div>
                        </div>
                        <!-- end password form -->
                        --%>
                    </div>
                </div>
            </div>
            <!-- end content tabs -->
        </div>
</section>
<!-- end section -->

<!-- modal info -->
<div id="modal-info" class="zoom-anim-dialog mfp-hide modal">
    <button class="modal__close" type="button"><svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><line x1='368' y1='368' x2='144' y2='144' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><line x1='368' y1='144' x2='144' y2='368' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg></button>

    <h4 class="modal__title">Receipt</h4>

    <div class="modal__group">
        <label class="modal__label" for="value">Total cost:</label>
        <span class="modal__value">$59.00</span>
    </div>

    <div class="modal__group">
        <label class="modal__label" for="method">Payment method: <b>Paypal</b></label>

        <span class="modal__text">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.</span>
    </div>
</div>
<!-- end modal info -->

<%@include file="/WEB-INF/include/footer1.jsp" %>
<%@include file="/WEB-INF/include/footer2.jsp" %>