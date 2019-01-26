package cinemaproject.illiaderhun.com.github;

import cinemaproject.illiaderhun.com.github.controller.MovieController;
import cinemaproject.illiaderhun.com.github.controller.OrderController;
import cinemaproject.illiaderhun.com.github.controller.SсheduleController;
import cinemaproject.illiaderhun.com.github.controller.UserController;
import cinemaproject.illiaderhun.com.github.dao.entities.Movie;
import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import cinemaproject.illiaderhun.com.github.dao.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
        try {
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "INDEX";
            }

            switch (theCommand) {
                case "INDEX":
                    showIndexPage(request, response);
                    break;
                case "REGISTRATION":
                    registerNewUser(request, response);
                    break;
                case "LOGIN":
                    loginUser(request, response);
                    break;
                case "USER_PAGE":
                    showUserPage(request, response);
                    break;
                case "ADD_MOVIE":
                    addMovie(request, response);
                    break;
                case "MAKE_ORDER":
                    makeOrder(request, response);
                default: {
                    showIndexPage(request, response);
                }
            }

        } catch (Exception e) {
            /*RequestDispatcher dispatcher = request.getRequestDispatcher("/accessDenied.jsp");
            dispatcher.forward(request, response);*/
            throw new ServletException(e);
        }

    }

    private void makeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer row = Integer.parseInt(request.getParameter("row"));
        Integer col = Integer.parseInt(request.getParameter("col"));
        Integer movieId = Integer.parseInt(request.getParameter("movieId"));
        Integer userId = (Integer) request.getSession(true).getAttribute("userId");

        new OrderController().makeOrder(row, col, movieId, userId);

        showIndexPage(request, response);
    }

    private void addMovie(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String time = request.getParameter("time");
        String price = request.getParameter("price");
        String schedule = request.getParameter("schedule");
        String poster = request.getParameter("poster");
        System.out.println("Poster ===>> " + poster);

        new MovieController().addNewMovie(title, description, time, price, schedule);

        showUserPage(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserController userController = new UserController();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User theUser = userController.validateUserByEmailPassword(email, password);

        if (theUser == null) {
            request.setAttribute("err", "logPass");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            setSessionForUser(theUser, request.getSession(true));
            showUserPage(request, response);
        }
    }

    private void showUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession(true).getAttribute("userId");
        Integer userRole = (Integer) request.getSession(true).getAttribute("userRole");

        if (userRole == 1) {
            ArrayList<Order> orders = new OrderController().getCustomerOrders(userId);
            request.setAttribute("orders", orders);
        } else if (userRole == 2) {
            ArrayList<Movie> movies = new MovieController().getMovieForManagerPage();
            request.setAttribute("orders", movies);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userPage.jsp");
        dispatcher.forward(request, response);
    }

    private void registerNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String surname = request.getParameter("surname").trim();
        String password = request.getParameter("password").trim();

        User theUser = new UserController().createNewUser(name, email, surname, password);

        if (theUser == null) {
            request.setAttribute("err", "alreadyExist");
            request.setAttribute("existEmail", email);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }

        setSessionForUser(theUser, request.getSession(true));
        showUserPage(request, response);
    }

    private void showIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Movie theMovie = new MovieController().getMovieForIndexPage();
        request.setAttribute("movie", theMovie);

        Schedule theSchedule = new SсheduleController().getSсheduleForIndexPage(theMovie.getScheduleId());
        ArrayList<Order> theOrders = new OrderController().getOrderForIndexPage(theSchedule.getOrderId());
        request.setAttribute("places", theOrders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void setSessionForUser(User theUser, HttpSession session) {
        Integer userId = theUser.getId();
        Integer userRole = theUser.getRoleId();
        String firstName = theUser.getName();
        String lastName = theUser.getSurname();
        String email = theUser.getEmail();

        session.setAttribute("userId", userId);
        session.setAttribute("userRole", userRole);
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("email", email);
    }
}
