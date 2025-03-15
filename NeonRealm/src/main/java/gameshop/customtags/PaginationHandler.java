/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.customtags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationHandler extends TagSupport {

    private String url;
    private int currentPage;
    private int totalPages;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = (url == null) ? "" : url; // Prevent NullPointerException
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public int doStartTag() throws JspException {
        StringBuilder pagination = new StringBuilder();

        try {
            pagination.append("<ul class='paginator__wrap'>");

            // Previous Button
            if (currentPage > 1) {
                pagination.append(String.format("<li class='paginator__item'><a href='%s?page=1'>&laquo;</a></li>", url));
                pagination.append(String.format(
                        "<li class='paginator__item paginator__item--prev'><a href='%s?page=%d'>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='244 400 100 256 244 112' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='120' y1='256' x2='412' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></a></li>", url, currentPage - 1));
            } else {
                pagination.append(
                        "<li class='paginator__item paginator__item--prev disabled'><span>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='244 400 100 256 244 112' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='120' y1='256' x2='412' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></span></li>");
            }

            // Determine range of pages to show
            int range = 3; // Show 3 pages before and after the current page
            int startPage = Math.max(1, currentPage - range); // Ensures it doesn't go below 1
            int endPage = Math.min(totalPages, currentPage + range); // Ensures it doesn't exceed totalPages

            // Always ensure first few pages are visible when close to the start
            if (currentPage <= range) {
                startPage = 1;
                endPage = Math.min(totalPages, 2 * range + 1);
            }

            // Always ensure last few pages are visible when close to the end
            if (currentPage >= totalPages - range) {
                startPage = Math.max(1, totalPages - 2 * range);
                endPage = totalPages;
            }

            // Generate the page numbers
            for (int i = startPage; i <= endPage; i++) {
                if (i == currentPage) {
                    pagination.append(String.format("<li class='paginator__item paginator__item--active'><a href='%s?page=%d'>%d</a></li>", url, i, i));
                } else {
                    pagination.append(String.format("<li class='paginator__item'><a href='%s?page=%d'>%d</a></li>", url, i, i));
                }
            }

            // Next Button
            if (currentPage < totalPages) {
                pagination.append(String.format(
                        "<li class='paginator__item paginator__item--next'><a href='%s?page=%d'>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='268 112 412 256 268 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='392' y1='256' x2='100' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></a></li>", url, currentPage + 1));
                pagination.append(String.format("<li class='paginator__item'><a href='%s?page=%d'>&raquo;</a></li>", url, totalPages));

            } else {
                pagination.append(
                        "<li class='paginator__item paginator__item--next disabled'><span>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='268 112 412 256 268 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='392' y1='256' x2='100' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></span></li>");
            }

            pagination.append("</ul>");

            pageContext.getOut().write(pagination.toString());
        } catch (IOException e) {
            throw new JspException("Error in PaginationHandler", e);
        }

        return SKIP_BODY;
    }
}
