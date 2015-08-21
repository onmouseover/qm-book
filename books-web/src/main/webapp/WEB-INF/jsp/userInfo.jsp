<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/taglibs.jsp"></jsp:include>
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
    <div class="col-sm-12 col-md-12 col-lg-12 col-md-offset-2">
      <div id="wrapper">
        <form class="form-horizontal" action="/shareBook" method="post">
          <div class="form-group">
            <label for="bookName" class="col-sm-2 control-label">用户名：</label>
            <div class="col-sm-4">
              ${userInfo.userName}
            </div>
          </div>
          <div class="form-group">
            <label for="author" class="col-sm-2 control-label">借书码：</label>
            <div class="col-sm-4">
              ${userInfo.borrowKey}
            </div>
          </div>
          <div class="form-group">
            <label for="introduce" class="col-sm-2 control-label">推广链接：</label>
            <div class="col-sm-4">
              http://xxx.com/regist?toUserId=${userInfo.userId}
            </div>
          </div>
          </form>
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
