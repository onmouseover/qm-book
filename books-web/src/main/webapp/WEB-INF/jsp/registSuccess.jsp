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
  <div class="row">
    <div class="col-sm-12 col-md-12 col-lg-12">
      <div id="wrapper">
        <div class="alert alert-success" role="alert">
          恭喜您，注册成功！<a href="/shareBooks">分享图书</a>&nbsp;&nbsp;<a href="/index">返回首页</a>
        </div>
      </div>
    </div>
  </div>
  <hr>
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
