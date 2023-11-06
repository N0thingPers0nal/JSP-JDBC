<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
<section class="vh-100">
  <div class="container-fluid h-custom">
    <div class="row d-flex justify-content-center align-items-center h-100">

      <div class="col-md-8 col-lg-5 col-xl-4 offset-xl-1 bg-secondary-subtle p-5">

        <form method="POST" action="login" >

            <div class="d-flex flex-row align-items-center justify-content-center">
              <p class="lead fs-2 fw-semibold mb-3 me-3">Login</p>
            </div>
          <div class="form-outline mb-4">
            <input type="email"  class="form-control " name="email"
              placeholder="Enter email address" />

          </div>

          <div class="form-outline mb-2">

            <input type="password" class="form-control" name="password"
              placeholder="Enter password" />
          </div>
          <div class="text-center text-danger text-lg mt-1 pt-2">
          <%
          if(request.getAttribute("error")!=null){
             out.print("<i>Invalid Email or Password</i>");
          }
          %>
          </div>
          <div class="text-center text-lg mt-1 pt-2">
            <input type="submit" class="btn btn-primary" name="login" value="Login">

            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="register.jsp"
                class="link-danger">Register</a></p>
          </div>
        </form>
      </div>
    </div>
  </div>
  </div>
</section>
</body>
</html>
