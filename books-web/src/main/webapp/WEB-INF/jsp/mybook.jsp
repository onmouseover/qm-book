<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp"%>
<html lang="en">
<head>
    <title>千米阅E库</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
 
<jsp:include page="common/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12">
            <div id="wrapper">
                <div id="repos" class="columns collapse-group"></div>
                <div class="section-title">
                    <div class="separator"></div>
                    <span class="title">所有图书</span>

                    <c:forEach items="${bookList}" var="item" varStatus="stat">
                        <c:if test="${stat.index % 5 == 0}">
                            <div class="columns section in" style="height: 0px;text-align: center;" >
                        </c:if>
                        <div class="updated-card col-sm-5 col-md-4 col-lg-3"
                             style="height:auto;width:175px;text-align:center;margin-left: 42px">
                            <div class="card pin css developer.bigfix.com" id="book_${item.bookId}">
                                <a href="#">
                                    <h4>
                                        <img src="/images/1.jpg" alt="java" class="img-rounded"
                                             style="width: 120px; height: 160px; margin: auto">
                                    </h4>
                                    <h5>${item.bookName}</h5>
                                </a>
                                <p>
                                    <c:if test="${item.state eq '1' || item.state eq '2'}">
                                        <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#exampleModal" data-whatever="${item.bookId}" data-state="${item.state}">借书</a>
                                    </c:if>

                                </p>
                            </div>
                        </div>

                        <c:if test="${stat.index % 5 == 0}">
                            </div>
                        </c:if>
                    </c:forEach>
                    <a data-toggle="collapse" data-target="#updated" class="twistie showdetails"></a>
                </div>
                <div class="separator gap"></div>
                <div class="section-title"></div>
            </div>
        </div>
    </div>
    <p align="center"><h5 align="center">More...</h5></p>
    <hr>
</div>


<footer>
    <div class="container">
        <p>© 千米 2015-2016</p>
    </div>
</footer>
<!-- /container -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">借书</h4>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" value="" name="bookId" id="bookId" />
                    <input type="hidden" value="" name="bookState" id="bookState" />
                    <div class="form-group" id="toUserName">
                        <label for="userName" class="control-label">用户名:</label>
                        <input type="text" class="form-control" id="userName">
                    </div>
                    <div class="form-group">
                        <label for="borrowCode" class="control-label">借书码:</label>
                        <input type="password" class="form-control" id="borrowCode">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn_borrow">确定借书</button>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/immutable.min.js"></script>
<script type="text/javascript">
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        var bookState = button.data('state');
        if(bookState == '2'){
            $('#toUserName').hide();
        }else{
            $('#toUserName').show();
        }
        var modal = $(this);
        modal.find('#bookId').val(recipient);
        modal.find('#bookState').val(bookState);
    })
    $('#btn_borrow').on('click',function(){
        var userName = $('#userName').val();
        var borrowCode = $('#borrowCode').val();
        var bookId = $('#bookId').val();
        var bookState =  $('#bookState').val();
        $.ajax({
            type:"post",
            url:"/borrowBook",
            data:{userName:userName,borrowCode:borrowCode,bookId:bookId,bookState:bookState},
            success:function(data){
                if(data.result == 'ok'){
                    $('#book_'+bookId).hide();
                    $('#exampleModal').modal('hide')
                    alert('操作成功');
                }else{
                    alert(data.msg);
                };
            },
            error:function(data){
                alert("让程序飞一会儿！");
            }

        });
    });
</script>
</body>
</html>
<style>
    body {
        padding-top: 50px;
        padding-bottom: 20px;
    }
</style>
