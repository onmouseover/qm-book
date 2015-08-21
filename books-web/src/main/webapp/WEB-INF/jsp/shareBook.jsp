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
        </li>
      </ul>
    </div>
  </div>
</div>

<div class="container well well-lg">
  <div class="row">
    <div class="col-sm-12 col-md-12 col-lg-12 col-md-offset-2">
      <div id="wrapper">
        <form class="form-horizontal">
          <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">书籍名称：</label>
            <div class="col-sm-4">
              <input type="input" maxlength="50" class="form-control" id="inputEmail3">
            </div>
          </div>
          <div class="form-group">
            <label for="bookType" class="col-sm-2 control-label">书籍类型：</label>
            <div class="col-sm-4" name="bookType" id="bookType">
              <select class="form-control">
                <option>文学</option>
                <option>历史</option>
                <option>java</option>
                <option>Node</option>
                <option>C++</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <div class="checkbox">
                <label>
                  <input type="checkbox"> Remember me
                </label>
              </div>
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
