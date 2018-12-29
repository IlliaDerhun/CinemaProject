package cinemaproject.illiaderhun.com.github;

import cinemaproject.illiaderhun.com.github.controller.MovieController;
import cinemaproject.illiaderhun.com.github.controller.OrderController;
import cinemaproject.illiaderhun.com.github.controller.SheduleController;
import cinemaproject.illiaderhun.com.github.dao.entities.Movie;
import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ControllerDispatcherServlet")
public class ControllerDispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeChoice(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeChoice(request, response);
    }

    private void makeChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Movie theMovie =  new MovieController().getMovieForIndexPage();
        request.setAttribute("movie", theMovie);

        Schedule theSchedule = new SheduleController().getSheduleForIndexPage(theMovie.getScheduleId());
        ArrayList<Order> theOrders = new OrderController().getOrderForIndexPage(theSchedule.getOrderId());
        request.setAttribute("places", theOrders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
