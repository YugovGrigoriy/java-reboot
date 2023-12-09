package ru.sberbank.edu;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;

public class FinancialServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getRequestURI();
        if (path.endsWith("/finance")) {

            getServletContext().getRequestDispatcher("/finance.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String sum = req.getParameter("sum");
        String percent = req.getParameter("percent");
        String years = req.getParameter("years");
        if (!sum.isEmpty() && !percent.isEmpty() && !years.isEmpty()) {
            try {
                double doubleSum, doublePercent, doubleYears;
                doubleSum= Integer.parseInt(sum);
                doublePercent= Integer.parseInt(percent);
                doubleYears= Integer.parseInt(years);
                if(doubleSum<50_000){
                    //go to catch
                    throw new RuntimeException();

                }

                req.setAttribute("calculation", calculation(doubleSum,doublePercent,doubleYears));
                getServletContext().getRequestDispatcher("/calculation.jsp").forward(req, resp);
            }catch (Exception e){
                getServletContext().getRequestDispatcher("/finance-error.jsp").forward(req, resp);
            }

        } else {
            getServletContext().getRequestDispatcher("/finance-error.jsp").forward(req, resp);
        }
    }
    private double calculation(double sum,double percent,double years){
        return(sum*(percent/100)*years)+sum;
    }


}
