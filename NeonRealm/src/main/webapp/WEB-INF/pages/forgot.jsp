<%-- 
    Document   : forgot.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>
    <!-- sign in -->
    <div class="sign section--full-bg" data-bg="<%= getServletContext().getContextPath() %>/assets/img/bg2.jpg">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="sign__content">
                        <!-- authorization form -->
                        <form action="#" class="sign__form">
                            <a href="<%= getServletContext().getContextPath() %>/index.jsp" class="sign__logo">
                                <img src="<%= getServletContext().getContextPath() %>/assets/img/logo.png" alt="">
                            </a>

                            <div class="sign__group">
                                <input type="text" class="sign__input" placeholder="Email">
                            </div>

                            <div class="sign__group sign__group--checkbox">
                                <input id="remember" name="remember" type="checkbox" checked="checked">
                                <label for="remember">I agree to the <a href="<%= getServletContext().getContextPath() %>/pages/privacy.jsp">Privacy Policy</a></label>
                            </div>

                            <button class="sign__btn" type="button">Send</button>

                            <span class="sign__text">We will send a password to your Email</span>
                        </form>
                        <!-- end authorization form -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end sign in -->

    <%@include file="/WEB-INF/include/footer2.jsp" %>