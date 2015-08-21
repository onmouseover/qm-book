<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <script async="" src="//www.google-analytics.com/analytics.js"></script><script src="//cdn.optimizely.com/js/1141215513.js"></script>
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

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">

    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="/index">首页</a>
        </li>
        <li><a href="/mybook">我的资源</a>
        </li>
        <li><a href="/shareBooks">分享资源</a>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-warning">Search</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">加入我们</a></li>
      </ul>
    </div>
  </div>
</div>

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
                <option value="0">文学</option>
                <option value="1">历史</option>
                <option value="2">java</option>
                <option value="3">Node</option>
                <option value="4">C++</option>
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
              <input type="input" maxlength="50"  name="introduce"  class="form-control" id="introduce">
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
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default">确认发布</button>
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