<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Todo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
 <%
    if(session.getAttribute("id") == null){
             response.sendRedirect(request.getContextPath());
    }
 %>
<div class="d-flex justify-content-around mt-2">
    <div>
        <p class="fs-3 fw-semi-bold fst-italic">Welcome <c:out value="${userName}" /></p>
    </div>
    <div>
        <p><form action="logout.jsp" method="link">
            <input type="submit" class="btn btn-danger " value="Logout"/>
            </form>
        </p>
    </div>
</div>

<div class="container-fluid h-custom">
    <div class="d-flex justify-content-center mt-2">
      <div class="col-md-8 col-lg-5 col-xl-4 offset-xl-1 bg-secondary-subtle px-5 py-3 rounded-5">
      <p class="fs-3 fw-semibold mb-3 me-3 text-center">Add Name to Register</p>
        <form method="POST" action="home">
            <p><input type="text" class="form-control" name="todo" placeholder="Enter name"></p>
            <%
                if(request.getAttribute("error")!=null){
                    out.print("<p class='text-danger fst-italic text-center'>Please enter content before adding it</p>");
                }
            %>
            <div class="text-center">
                <input type="submit" class="btn btn-warning" value="Add">
            </div>
        </form>
      </div>
    </div>
</div>
<div><c:if test="${a eq 0}"><p>asdfsa</p></c:if></div>
<div class="container-fluid h-custom">
    <div class="d-flex justify-content-center mt-5">
      <div class="col-md-8 col-lg-5 col-xl-4 offset-xl-1 bg-secondary-subtle px-5 py-3 rounded-5">
      <p class="fs-3 fw-semibold mb-3 me-3 text-center">Names in Register</p>
        <form method="POST" action="home">

            <div class="text-center">
            <c:if test="${todos.size() eq 0}">
                <p>No items in the Register</p>
                </c:if>
            <c:if test="${todos.size() gt 0}">
                    <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Todo</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="i" value="0" />
                                <c:forEach var="todo" items="${todos}">

                                    <tr>
                                        <td>
                                        <c:set var="i" value="${i + 1}"/>
                                            <c:out value="${i}" />
                                        </td>
                                        <td>
                                            <c:out value="${todo.todo}" />
                                        </td>
                                        <td><a href="home?id=<c:out value='${todo.id}' />">Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </c:if>
            </div>
        </form>
      </div>
    </div>
</div>
</body>
</html>