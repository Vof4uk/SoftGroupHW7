package ua.mykytenko.web;

import ua.mykytenko.model.Student;
import ua.mykytenko.service.StudentsService;
import ua.mykytenko.service.StudentsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentsServlet extends HttpServlet {
    private static final String ACTION = "action";
    private static final String ACTION_DELETE = "delete";
    private static final String ACTION_UPDATE = "update";
    private static final String ACTION_ADD = "add";
    private static final String ACTION_SAVE = "save";
    private static final String ACTION_SHOW_ALL = "showAll";
    private static final String TO_STUDENTS_LIST = "/students?action=showAll";


    private StudentsService service = new StudentsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        Integer id = getIdParam(req);

        if (action == null || action.equals(ACTION_SHOW_ALL)) { ///////////selectAll
            List<Student> students = service.getAll();
            req.setAttribute("students", students);
            req.getRequestDispatcher("students.jsp").forward(req, resp);
        } else if (action.equals(ACTION_DELETE)) { //////////delete
            if (id != null) {
                service.deleteById(id);
            }
            resp.sendRedirect(TO_STUDENTS_LIST);
        } else if (action.equals(ACTION_UPDATE)) {       //////update
            if (id == null) {
                resp.sendRedirect(TO_STUDENTS_LIST);
            } else {
                Student student = service.getById(id);
                req.setAttribute("student", student);
                req.getRequestDispatcher("studentForm.jsp").forward(req, resp);
            }
        } else if (action.equals(ACTION_ADD)) {    //////add new
            req.getRequestDispatcher("studentForm.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);

        if (action == null || action.equals("showAll") || action.equals(ACTION_DELETE)) { ///////////selectAll
            doGet(req, resp);
        } else if (action.equals(ACTION_UPDATE) || action.equals(ACTION_UPDATE)) {       //////update
            doGet(req, resp);
        } else if (action.equals(ACTION_SAVE)) {
            Student student = studentFromRequestParams(req);
            if (student == null) {
                resp.sendRedirect(TO_STUDENTS_LIST);
            } else {
                try {
                    if (student.isNew()) {
                        service.add(student);
                    } else {
                        service.update(student);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect(TO_STUDENTS_LIST);
        }
    }

    private Integer getIdParam(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        if (idStr == null || !idStr.matches("[0-9]{1,8}")) {
            return null;
        } else {
            return Integer.parseInt(idStr);
        }
    }

    private Student studentFromRequestParams(HttpServletRequest request) {
        Student student = new Student();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = request.getParameter("birthDate");
        Date birthdate = null;
        try {
            birthdate = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        student.setBirthDate(birthdate);
        student.setEmail(request.getParameter("email"));
        student.setPhoneNumber(request.getParameter("phoneNumber"));
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        Integer id = getIdParam(request);
        if (id != null) {
            student.setId(id);
        }
        return student;
    }

}
