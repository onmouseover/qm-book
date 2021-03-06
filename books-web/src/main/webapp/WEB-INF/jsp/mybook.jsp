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
                                        <c:if test="${not empty item.pictureUrl}">
                                            <img src="${item.pictureUrl}" alt="java" class="img-rounded"
                                                 style="width: 120px; height: 160px; margin: auto">
                                        </c:if>
                                        <c:if test="${empty item.pictureUrl}">
                                            <img src="/images/default.jpg" alt="java" class="img-rounded"
                                                 style="width: 120px; height: 160px; margin: auto">
                                        </c:if>
                                    </h4>
                                    <h5>
                                    ${item.bookName}
                                    </h5>
                                </a>
                                <p>
                                    <c:if test="${item.state eq '1' || item.state eq '2'}">
                                        <a href="#" class="btn btn-success btn-xs" id="lend_${item.bookId}" data-toggle="modal" data-target="#exampleModal" data-type="1" data-whatever="${item.bookId}" data-state="${item.state}">借出</a>
                                    </c:if>
                                    <c:if test="${item.state eq '3'}">
                                        <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" id="lend_${item.bookId}" data-target="#exampleModal" data-type="2" data-whatever="${item.bookId}" data-state="${item.state}">归还</a>
                                    </c:if>
                                    <c:if test="${item.state ne '0'}">
                                        <a href="#" class="btn btn-danger btn-xs" data-toggle="modal" id="borrow_${item.bookId}" data-target="#exampleModal" data-whatever="${item.bookId}" data-type="3" data-state="${item.state}">下架</a>
                                    </c:if>
                                    <c:if test="${item.state eq '0'}">
                                        <a href="#" class="btn btn-info btn-xs" data-toggle="modal" id="borrow_${item.bookId}" data-target="#exampleModal" data-whatever="${item.bookId}" data-type="4" data-state="${item.state}">上架</a>
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
                <h4 class="modal-title" id="exampleModalLabel">借出</h4>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" value="" name="bookId" id="bookId" />
                    <input type="hidden" value="" name="type" id="type" />
                    <input type="hidden" value="" name="bookState" id="bookState" />
                    <div class="form-group" id="toUserName">
                        <label for="userName" class="control-label">用户名:</label>
                        <input type="text" class="form-control" id="userName">
                    </div>
                    <div class="form-group">
                        <label for="borrowCode" class="control-label">借出码:</label>
                        <input type="password" class="form-control" id="borrowCode">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn_borrow">确定</button>
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
        var type = button.data('type');
        if(type == '2'){
            $('#type').val('2');
            $('#toUserName').hide();
            $('#exampleModalLabel').html('归还');
        }else if(type == '1'){
            $('#type').val('1');
            $('#exampleModalLabel').html('借出');
        }else if(type == '3'){
            $('#type').val('3');
            $('#toUserName').hide();
            $('#exampleModalLabel').html('上架');
        }else if(type == '4'){
            $('#type').val('4');
            $('#toUserName').hide();
            $('#exampleModalLabel').html('下架');
        }
        if(bookState != '1' || type != '1'){
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
        var type = $('#type').val();
        $.ajax({
            type:"post",
            url:"/borrowBook",
            data:{userName:userName,type:type,borrowCode:borrowCode,bookId:bookId,bookState:bookState},
            success:function(data){
                if(data.result == 'ok'){
                    if(type == '1'){
                        $('#lend_'+bookId).addClass("btn-primary");
                        $('#lend_'+bookId).removeClass("btn-success");
                        $('#lend_'+bookId).attr('data-state','3');
                        $('#lend_'+bookId).attr('data-type','2');
                        $('#lend_'+bookId).text("归还");
                    }else{
                        $('#lend_'+bookId).addClass("btn-success");
                        $('#lend_'+bookId).removeClass("btn-primary");
                        $('#lend_'+bookId).attr('data-state','1');
                        $('#lend_'+bookId).attr('data-type','1');
                        $('#lend_'+bookId).text("借出");
                    }
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
