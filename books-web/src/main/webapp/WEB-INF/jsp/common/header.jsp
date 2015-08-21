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
        <c:if test="${userInfo ne null}">
        <li><a href="/mybook">我的资源</a>
        </li>
        <li><a href="/shareBooks">分享资源</a>
        </li>
        </c:if>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-warning">Search</button>
      </form>

        <ul class="nav navbar-nav navbar-right">
          <c:if test="${userInfo eq null}">
          <li><a href="/regist">加入我们</a></li>
          <li><a href="/login">登陆</a></li>
          </c:if>
          <c:if test="${userInfo ne null}">
            <li><a href="/userInfo">我的信息</a></li>
          </c:if>
        </ul>


    </div>
  </div>
</div>