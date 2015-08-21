<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <title>千米阅E库</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">

        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="javascript:void(0);">首页</a>
                </li>
                <li>
                    <a href="javascript:void(0);">我的资源</a>
                </li>
                <li>
                    <a href="javascript:void(0);">分享资源</a>
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

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <div style="text-align: center;" style="padding-top:40px"><h1>QM Hackathon In NanJing</h1></div>
        <div style="text-align: center;">
            <a href="javascript:void(0);">
                <p class="btn btn-primary btn-lg btn-custom-jazz" style="margin: 15px">
                    时间，时间，还是时间 »
                </p>
            </a>
            <a href="javascript:void(0);">
                <p class="btn btn-primary btn-lg btn-custom-bm" style="margin: 15px">
                    任彩虹划破天空 »
                </p>
            </a>
            <a href="javascript:void(0);">
                <p class="btn btn-primary btn-lg btn-custom-dw" style="margin: 15px;">
                    遥望昨夜，此生一梦是云间...
                </p>
            </a>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12">
            <div id="wrapper">
                <div id="repos" class="columns collapse-group">
                </div>
                <div class="separator"></div>
                <div class="section-title">
                    <span class="title">热门图书</span>


                    <div class="columns section in" style="height: auto;">
                        <div class="updated-card col-sm-5 col-md-4 col-lg-3"
                             style="height:auto;width:175px;text-align:center;">
                            <div class="card pin css developer.bigfix.com">
                                <a href="#">
                                    <h4>
                                        <img src="/images/1.jpg" alt="java" class="img-rounded"
                                             style="width: 100px; height: 140px; margin: auto">
                                    </h4>
                                    <h5>java程序与设计</h5>
                                </a>

                                <p>
                                    <a href="#" class="btn btn-primary btn-xs">预约</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="#" class="btn btn-primary btn-xs">借书</a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <a data-toggle="collapse" data-target="#updated" class="twistie showdetails"></a>
                </div>
                <div class="separator gap"></div>
                <div class="section-title"></div>
            </div>
        </div>
    </div>
    <hr>
</div>


<footer>
    <div class="container" align="center">
        <p>© 千米 2015-2016</p>
    </div>
</footer>
<!-- /container -->

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/immutable.min.js"></script>
<script type="text/javascript">
    var searchBooks = function (condition) {
        location.href = '/index?condition=' + condition;
    };
</script>
</body>
</html>
<style>
    body {
        padding-top: 50px;
        padding-bottom: 20px;
    }
</style>
