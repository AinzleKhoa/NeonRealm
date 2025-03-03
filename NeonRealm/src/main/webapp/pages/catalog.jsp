<%-- 
    Document   : catalog
    Created on : Feb 23, 2025, 9:41:54 PM
    Author     : Le Anh Khoa - CE190449
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="gameshop.model.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>

<% ArrayList<Game> gameList = (ArrayList) request.getAttribute("catalogGameList");%> <%-- Only once --%>

<!-- page title -->
<section class="section section--first section--last section--head" data-bg="<%= getServletContext().getContextPath()%>/assets/img/bg.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section__wrap">
                    <!-- section title -->
                    <h2 class="section__title">Catalog <span>(35430 products)</span></h2>
                    <!-- end section title -->

                    <!-- breadcrumb -->
                    <ul class="breadcrumb">
                        <li class="breadcrumb__item"><a href="<%= getServletContext().getContextPath()%>/home">Home</a></li>
                        <li class="breadcrumb__item breadcrumb__item--active">Catalog</li>
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
                        <div class="filter-wrap">
                            <button class="filter-wrap__btn" type="button" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="false" aria-controls="collapseFilter">Open filter</button>

                            <div class="collapse filter-wrap__content" id="collapseFilter">
                                <!-- filter -->
                                <div class="filter">
                                    <h4 class="filter__title">Filters <button type="button">Clear all</button></h4>

                                    <div class="filter__group">
                                        <label class="filter__label">Keyword:</label>
                                        <input type="text" class="filter__input" placeholder="Keyword">
                                    </div>

                                    <div class="filter__group">
                                        <label for="sort" class="filter__label">Sort by:</label>

                                        <div class="filter__select-wrap">
                                            <select name="sort" id="sort" class="filter__select">
                                                <option value="0">Relevance</option>
                                                <option value="1">Newest</option>
                                                <option value="2">Oldest</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="filter__group">
                                        <label class="filter__label">Price:</label>

                                        <div class="filter__range">
                                            <div id="filter__range-start"></div>
                                            <div id="filter__range-end"></div>
                                        </div>

                                        <div id="filter__range"></div>
                                    </div>

                                    <div class="filter__group">
                                        <label class="filter__label">Platforms:</label>
                                        <ul class="filter__checkboxes">
                                            <li>
                                                <input id="type1" type="checkbox" name="type1" checked="">
                                                <label for="type1">Playstation</label>
                                            </li>
                                            <li>
                                                <input id="type2" type="checkbox" name="type2">
                                                <label for="type2">XBOX</label>
                                            </li>
                                            <li>
                                                <input id="type3" type="checkbox" name="type3">
                                                <label for="type3">Windows</label>
                                            </li>
                                            <li>
                                                <input id="type4" type="checkbox" name="type4">
                                                <label for="type4">Mac OS</label>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="filter__group">
                                        <label class="filter__label">Genres:</label>
                                        <ul class="filter__checkboxes">
                                            <li>
                                                <input id="type5" type="checkbox" name="type5" checked="">
                                                <label for="type5">Action</label>
                                            </li>
                                            <li>
                                                <input id="type6" type="checkbox" name="type6">
                                                <label for="type6">Adventure</label>
                                            </li>
                                            <li>
                                                <input id="type7" type="checkbox" name="type7" checked="">
                                                <label for="type7">Fighting</label>
                                            </li>
                                            <li>
                                                <input id="type8" type="checkbox" name="type8" checked="">
                                                <label for="type8">Flight simulation</label>
                                            </li>
                                            <li>
                                                <input id="type9" type="checkbox" name="type9">
                                                <label for="type9">Platform</label>
                                            </li>
                                            <li>
                                                <input id="type10" type="checkbox" name="type10">
                                                <label for="type10">Racing</label>
                                            </li>
                                            <li>
                                                <input id="type11" type="checkbox" name="type11">
                                                <label for="type11">RPG</label>
                                            </li>
                                            <li>
                                                <input id="type12" type="checkbox" name="type12">
                                                <label for="type12">Sports</label>
                                            </li>
                                            <li>
                                                <input id="type13" type="checkbox" name="type13">
                                                <label for="type13">Strategy</label>
                                            </li>
                                            <li>
                                                <input id="type14" type="checkbox" name="type14">
                                                <label for="type14">Survival horror</label>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="filter__group">
                                        <button class="filter__btn" type="button">APPLY FILTER</button>
                                    </div>
                                </div>
                                <!-- end filter -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end filter wrap -->

            <!-- content wrap -->
            <div class="col-12 col-lg-80">
                <div class="row">
                    <% for (Game g : gameList) {
                    %>
                    <!-- card -->
                    <div class="col-12 col-sm-6 col-md-4 col-xl-3">
                        <div class="card card--catalog">
                            <a href="<%= getServletContext().getContextPath()%>/gamedetails?id=<%= g.getGameId()%>" class="card__cover">
                                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="<%= g.getTitle()%>">
                                <%--<span class="card__new">New</span>--%>
                            </a>

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

                            <div class="card__title">
                                <h3><a href="<%= getServletContext().getContextPath()%>/gamedetails?id=<%= g.getGameId()%>"><%= g.getTitle()%></a></h3>
                                <span>$<%= g.getPrice()%></span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" type="button">Buy</button>

                                <button class="card__favorite" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end card -->
                    <% }%>

                    <!-- paginator -->
                    <div class="col-12">
                        <div class="paginator">
                            <div class="paginator__counter">
                                12 from 144
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
            <!-- end content wrap -->
        </div>
        <!-- end catalog -->	
    </div>
</section>
<!-- end section -->

<%@include file="/WEB-INF/include/footer1.jsp" %>
<%@include file="/WEB-INF/include/footer2.jsp" %>