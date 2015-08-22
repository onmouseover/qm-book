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
    <div class="col-sm-12 col-md-12 col-lg-12 col-md-offset-2">
      <div id="wrapper">
        <form class="form-horizontal" action="/shareBook" method="post">
          <div class="form-group">
            <label for="bookName" class="col-sm-2 control-label">书籍名称：</label>
            <div class="col-sm-4">
              <input type="input" maxlength="50" name="bookName" class="form-control" id="bookName">
            </div>
          </div>
          <div class="form-group">
            <label for="bks" class="col-sm-2 control-label">书籍类型：</label>
            <div class="col-sm-4" id="bks">
              <select class="form-control" name="bookType" id="bookType">
                <option value="1">文学</option>
                <option value="2">技术</option>
                <option value="3">历史</option>
                <option value="4">其他</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="author" class="col-sm-2 control-label">作者：</label>
            <div class="col-sm-4">
              <input type="input" maxlength="50" name="author" class="form-control" id="author">
            </div>
          </div>
          <div class="form-group">
            <label for="introduce" class="col-sm-2 control-label">书籍简介：</label>
            <div class="col-sm-4">
              <textarea class="form-control"  name="introduce" id="introduce" rows="3"></textarea>
            </div>
          </div>
          <div class="form-group">
            <label for="exampleInputFile" class="col-sm-2 control-label">书籍封面：</label>
            <div class="col-sm-4">
              <input type="file" id="exampleInputFile" name="exampleInputFile">
              <p class="help-block">上传书籍封面.</p>
            </div>
          </div>
          <div class="form-group">
            <label for="pageSize" class="col-sm-2 control-label">书籍页数：</label>
            <div class="col-sm-1">
              <input type="input" maxlength="4" name="pageSize" class="form-control" id="pageSize">
            </div>
          </div>
          <div class="form-group">
            <label for="borrowCash" class="col-sm-2 control-label">借阅价格：</label>
            <div class="col-sm-1">
              <input type="input" maxlength="50" name="borrowCash" class="form-control" id="borrowCash">
            </div>
          </div>
          <div class="form-group">
            <label for="borrowCash" class="col-sm-2 control-label">还书到网点：</label>
            <div class="col-sm-5">
              <label class="radio-inline">
                <input type="radio" name="backPoint" checked="checked" value="0"> 支持
              </label>
              <label class="radio-inline">
                <input type="radio" name="backPoint" value="1"> 不支持
              </label>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-primary">确认发布</button>
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
