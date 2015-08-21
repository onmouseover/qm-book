<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      <c:if test="${userInfo eq null}">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="/regist">加入我们</a></li>
          <li><a href="/login">登陆</a></li>
        </ul>
      </c:if>

    </div>
  </div>
</div>