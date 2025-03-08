<%-- 
    Document   : details.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gameshop.model.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>
<%
    Game g = (Game) request.getAttribute("singleGameDetails");
%>
<!-- section -->
<section class="section section--first section--bg" data-bg="<%= getServletContext().getContextPath()%>/assets/img/bg.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="details">
                    <div class="details__head">
                        <div class="details__cover">
                            <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= g.getImageUrl()%>" alt="">
                                <a href="http://www.youtube.com/watch?v=0O2aH4XLbto" class="details__trailer"><svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><path d='M112,111V401c0,17.44,17,28.52,31,20.16l247.9-148.37c12.12-7.25,12.12-26.33,0-33.58L143,90.84C129,82.48,112,93.56,112,111Z' style='fill:none;stroke-miterlimit:10;stroke-width:32px'/></svg> <span>Watch trailer</span></a>
                        </div>

                        <div class="details__wrap">
                            <h3 class="details__title"><%= g.getTitle()%></h3>

                            <ul class="details__list">
                                <li><span>Release date:</span><%= g.getReleaseDate()%></li>
                                <li><span>Genres:</span><%= g.getFormattedGenres()%><li>
                                        <li><span>Categories:</span><%= g.getFormattedCategories()%><li>
                                                <li><span>Developer:</span><%= g.getFormattedDevelopers()%></li>
                                                <li><span>Publisher:</span><%= g.getFormattedPublishers()%></li>
                                                </ul>

                                                <div class="details__text">
                                                    <p><%= g.getDescription()%></p>
                                                </div>
                                                </div>
                                                </div>

                                                <div class="details__gallery">
                                                    <div class="details__carousel owl-carousel" id="details__carousel">
                                                        <a href="<%= getServletContext().getContextPath()%>/assets/img/details/1-1.jpg" >
                                                            <img src="<%= getServletContext().getContextPath()%>/assets/img/details/1.jpg" alt="">
                                                        </a>

                                                        <a href="<%= getServletContext().getContextPath()%>/assets/img/details/2-2.jpg" >
                                                            <img src="<%= getServletContext().getContextPath()%>/assets/img/details/2.jpg" alt="">
                                                        </a>

                                                        <a href="<%= getServletContext().getContextPath()%>/assets/img/details/3-3.jpg" >
                                                            <img src="<%= getServletContext().getContextPath()%>/assets/img/details/3.jpg" alt="">
                                                        </a>

                                                        <a href="<%= getServletContext().getContextPath()%>/assets/img/details/4-4.jpg" >
                                                            <img src="<%= getServletContext().getContextPath()%>/assets/img/details/4.jpg" alt="">
                                                        </a>

                                                        <a href="<%= getServletContext().getContextPath()%>/assets/img/details/5-5.jpg" >
                                                            <img src="<%= getServletContext().getContextPath()%>/assets/img/details/5.jpg" alt="">
                                                        </a>

                                                        <a href="<%= getServletContext().getContextPath()%>/assets/img/details/6-6.jpg" >
                                                            <img src="<%= getServletContext().getContextPath()%>/assets/img/details/6.jpg" alt="">
                                                        </a>
                                                    </div>

                                                    <button class="details__nav details__nav--prev" data-nav="#details__carousel" type="button">
                                                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                                                    </button>
                                                    <button class="details__nav details__nav--next" data-nav="#details__carousel" type="button">
                                                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                                                    </button>
                                                </div>

                                                <div class="details__cart">
                                                    <span class="details__cart-title">Available on platforms:</span>
                                                    <ul class="details__platforms">
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

                                                    <span class="details__cart-title">PRICE</span>
                                                    <div class="details__price">
                                                        <span>$<%= g.getPrice()%></span>
                                                    </div>

                                                    <div class="details__actions">
                                                        <button class="details__buy" type="button">Buy now</button>

                                                        <button class="details__favorite" type="button">
                                                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>Add to Carts
                                                        </button>
                                                    </div>
                                                </div>

                                                <div class="details__content">
                                                    <div class="row">
                                                        <div class="col-12 col-xl-4 order-xl-2">
                                                            <!-- requirements -->
                                                            <div class="requirements">
                                                                <span class="requirements__title">Minimum requirements:</span>
                                                                <ul class="requirements__list">
                                                                    <li><span>OS:</span> Windows 7,Windows 8.1,Windows 10</li>
                                                                    <li><span>Processor:</span> Intel Core i5-2400s @ 2.5 GHz or AMD FX-6350 @ 3.9 GHz</li>
                                                                    <li><span>Memory:</span> 6 GB RAM</li>
                                                                    <li><span>Graphics:</span> NVIDIA GeForce GTX 660 or AMD R9 270 (2048 MB VRAM with Shader Model 5.0)</li>
                                                                    <li><span>Disk Space:</span> 42 GB available space</li>
                                                                    <li>Architecture: Requires a 64-bit processor and OS</li>
                                                                    <li><span>API:</span> DirectX 11</li>
                                                                    <li><span>Miscellaneous:</span> Video Preset: Lowest (720p)</li>
                                                                </ul>

                                                                <span class="requirements__title">Recommended requirements:</span>
                                                                <ul class="requirements__list">
                                                                    <li><span>OS:</span> Windows 7,Windows 8.1,Windows 10</li>
                                                                    <li><span>Processor:</span> Intel Core i5-2400s @ 2.5 GHz or AMD FX-6350 @ 3.9 GHz</li>
                                                                    <li><span>Memory:</span> 6 GB RAM</li>
                                                                    <li><span>Graphics:</span> NVIDIA GeForce GTX 660 or AMD R9 270 (2048 MB VRAM with Shader Model 5.0)</li>
                                                                    <li><span>Disk Space:</span> 42 GB available space</li>
                                                                    <li>Architecture: Requires a 64-bit processor and OS</li>
                                                                    <li><span>API:</span> DirectX 11</li>
                                                                    <li><span>Miscellaneous:</span> Video Preset: Lowest (720p)</li>
                                                                </ul>
                                                            </div>
                                                            <!-- end requirements -->
                                                        </div>
                                                    </div>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                </section>
                                                <!-- end section -->

                                                <!-- best sellers -->
                                                <section class="section section--bg section--first" data-bg="<%= getServletContext().getContextPath()%>/img/bg.jpg">
                                                    <div class="container">
                                                        <div class="row">
                                                            <!-- title -->
                                                            <div class="col-12">
                                                                <div class="section__title-wrap">
                                                                    <h2 class="section__title section__title--title"><b>Similar Genres</b></h2>

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
                                                    <div class="owl-carousel owl-carousel-gamedetails section__carousel section__carousel--big" id="carousel0">
                                                        <% ArrayList<Game> gameDetailsRecommendList = (ArrayList) request.getAttribute("gameDetailsRecommendList"); %> <%-- only once, used for all --%>
                                                        <%    if (gameDetailsRecommendList != null && !gameDetailsRecommendList.isEmpty()) { // Check if not null

                                                                // Extract game names and genres from the whole list
                                                                List<String> listGenreNames = new ArrayList<>(); // Take out 
                                                                for (Game game : gameDetailsRecommendList) {
                                                                    String[] genreNames = game.getFormattedGenres().split(", ");
                                                                    for (String names : genreNames) {
                                                                        listGenreNames.add(names.trim());
                                                                    }
                                                                }

                                                                // Get current page game's genres
                                                                String[] thisPageGenreNames = g.getFormattedGenres().split(", ");
                                                                List<String> matchingGenreNames = new ArrayList<>();

                                                                // Compare the genres of the current game (g) with genres from other games
                                                                for (String thisGenre : thisPageGenreNames) {
                                                                    if (listGenreNames.contains(thisGenre.trim())) {
                                                                        matchingGenreNames.add(thisGenre.trim());
                                                                    }
                                                                }

                                                                // **Use a Set to store unique games** (Prevents duplicate recommendations)
                                                                Set<Game> uniqueRecommendedGames = new HashSet<>();

                                                                for (Game gbc : gameDetailsRecommendList) { // Loop to get list out
                                                                    for (String genre : matchingGenreNames) {
                                                                        if (gbc.getGenres().contains(genre) && !gbc.getTitle().equals(g.getTitle())) {
                                                                            uniqueRecommendedGames.add(gbc);
                                                                        }
                                                                    }
                                                                }

                                                                // **Now loop through
                                                                for (Game gbc : uniqueRecommendedGames) {
                                                        %>
                                                        <!-- big card -->
                                                        <div class="card card--big">
                                                            <a href="<%= getServletContext().getContextPath()%>/gamedetails?id=<%= gbc.getGameId()%>" class="card__cover">
                                                                <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= gbc.getImageUrl()%>" alt="">
                                                            </a>

                                                            <div class="card__wrap">
                                                                <div class="card__title">
                                                                    <h3><a href="<%= getServletContext().getContextPath()%>/pages/details.jsp"><%= gbc.getTitle()%></a></h3>
                                                                </div>

                                                                <ul class="card__list">
                                                                    <li><span>Release date:</span><%= gbc.getReleaseDate()%></li>
                                                                    <li><span>Genres:</span><%= gbc.getFormattedGenres()%></li>
                                                                </ul>

                                                                <ul class="card__platforms">
                                                                    <% for (String platform : gbc.getPlatforms()) {

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
                                                                    <span>$<%= gbc.getPrice()%></span>
                                                                </div>
                                                                <div class="card__actions">
                                                                    <button class="card__buy" type="button">Buy now</button>

                                                                    <button class="card__favorite" type="button">
                                                                        <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- end big card -->
                                                        <% }
                                                            }%>
                                                    </div>
                                                    <!-- end carousel -->
                                                </section>
                                                <!-- end card -->
                                                <%@include file="/WEB-INF/include/footer1.jsp" %>
                                                <%@include file="/WEB-INF/include/footer2.jsp" %>