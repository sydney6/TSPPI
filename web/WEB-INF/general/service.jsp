<%-- 
    Document   : services
    Created on : 10 15, 16, 1:16:37 AM
    Author     : SAM
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <link href="imports/bootstrap.css" rel="stylesheet">
    <link href="css/display.css" rel="stylesheet">

</head>

<body>

    <!-- Navigation -->
    <%@include file="../source/navigation/navbar.jsp"%>
    
    <header id="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <c:choose>
                        <c:when test="${sb.size() > 0}">
                            <h1>SERVICES</h1>
                        </c:when>
                        <c:otherwise>
                            <h1>No Services to be shown</h1>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </header>
    <c:if test="${sb.size() > 0}">
    <section>
        <div class="container">
            <div id="products" class="row list-group">
                <div id="columns">
                    <c:forEach var="sb" items="${sb}">
                        <div class="content">
                            <figure class="item">
                                <img src="img?si=${sb.getServiceID()}">
                                <figcaption>
                                    <div class="view-product">
                                        <h4>${sb.getServiceName()}</h4>
                                        <button class="btn btn-link vib">
                                            <h5>View Details</h5>
                                            <input type="hidden" class="main-service" value="${sb.getServiceID()}">
                                        </button>
                                    </div>
                                </figcaption>
                            </figure>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <!--Service Details Modal-->
    <div id="service-modal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body text-center">
                    <div id="show-services"></div>
                    <c:if test="${account_type == 'client'}">
                        <form action="serviceinquiry" method="GET">
                            <div class="inquiry-element"></div>
                            <input type="submit" name="submit" id="submit" value="Inquire this Service" class="btn btn-warning">
                        </form>
                    </c:if>
                    <c:if test="${inventory_score == true}">
                    <form action="editservice" method="GET">
                        <div class="edit-element"></div>
                        <input type="submit" name="submit" id="submit" value="Edit Service" class="btn btn-warning">
                    </form>
                    </c:if>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!--Service Details Modal-->
    <!-- pagination -->
    <nav class="text-center">
            <div class="col-lg-12">
                    <ul class="pagination">
                            <li class="pag_prev">
                                    <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                    </a>
                            </li>
                            <li class="pag_next">
                                    <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                    </a>
                            </li>
                    </ul>
            </div>
    </nav>
    <script src="js/pagination.js"></script>
    <script>
        $(document).ready(function(){
                $('.vib').click(function(){
                    $('#show-services').empty();
                    $('.modal-title').empty();
                    $('.edit-element').empty();
                    $('.inquiry-element').empty();
                    var $service_id = $(this).find('.main-service').val();
                    $.getJSON('retrieveservice', {service_id: $service_id})
                        .done(function(json){
                            var $tableData = "";
                            var $spanData = "";
                            var $editData = "";
                            var $inquiryData = "";
                            for(var i=0; i<json.length; i++){
                                
                                $tableData = $('<div/>');
                                $tableData.append('<div class="col-md-8 col-md-offset-2"><legend>' + json[i].name + '</legend></div>');
                                $tableData.append('<div class="col-md-12"><strong>' + json[i].description + '</strong><br>Service Description</div>');
                                $('#show-services').append($tableData);
                                
                                $editData = $('<span/>');
                                $editData.append('<input type="hidden" name="service_id" id="service_id" value="'+ json[i].id +'">');
                                $('.edit-element').append($editData);
                                
                                $inquiryData = $('<span/>');
                                $inquiryData.append('<input type="hidden" name="service_id" id="service_id" value="' + json[i].id + '">');
                                $('.inquiry-element').append($inquiryData);
                            }
                        
                        });
                    $('#service-modal').modal('show');
                });
            });
    </script>
    </c:if>
</body>

</html>
