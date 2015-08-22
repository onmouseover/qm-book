<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp"%>
<html lang="en"><head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>千米阅E库</title>
  <link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">
  <link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css" rel="stylesheet">
  <link href="/css/style.css" rel="stylesheet">
 </head>
<body>
<jsp:include page="common/header.jsp"></jsp:include>
<div class="container well well-lg">
  <div class="row-content home-hero">
    <div class="col-sm-12">
      <div class="row">
        <div class="col-sm-12 pad-xs">
          <div class="row-content">
            <div class="col-sm-8 col-sm-offset-2">
              <h1 id="home-header" class="text-center">期待你，加入我们！ <span id="cloud-hosting-for" class="animated fadeInLeft">You.</span></h1>
              <p id="high-performance" class="lead text-center">High performance SSD Linux servers for all of your infrastructure needs.</p>
            </div>
          </div>

          <div class="row pad-xs">


            <div class="col-sm-6 col-sm-offset-6 col-md-5 col-md-offset-3">

              <form action="/doRegist" method="post">
                <input type="hidden" name="pointUserId" value="${toUserId}" />
                <div class="form-group">
                  <input name="userName" type="text" class="form-control input-lg" placeholder="Choose a username">
                </div>
                <div class="form-group">
                  <input name="password" type="password" class="form-control input-lg" placeholder="Choose a password">
                </div>

                <div class="form-group">
                  <button type="submit" class="btn btn-success btn-lg btn-block btn-border">Create Account</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</div>

<footer>
  <div class="container">
    <p>© 千米 2013-2014</p>
  </div>
</footer>
<!-- /container -->

<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.min.js"></script>



</body></html>
