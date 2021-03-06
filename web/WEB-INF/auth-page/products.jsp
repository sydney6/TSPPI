<%-- 
    Document   : products
    Created on : Oct 1, 2016, 2:56:32 PM
    Author     : cruzsyd
--%>

<%@page import="com.tsppi.bean.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <link href="imports/bootstrap.css" rel="stylesheet">

    <link href="css/3-col-portfolio.css" rel="stylesheet">

</head>

<body>

    <!-- Navigation -->
    <%@include file="../static-page/navbar.jsp"%>

    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Page Heading
                    <small>Secondary Text</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        
        <div class="row">
        <c:forEach var="pb" items="${pb}">
            
            <div class="col-md-4 portfolio-item">
                <img class="img-responsive" src="img/car-placeholder.png" alt="">
                <h1 class="product-title">${pb.getProductName()}</h1>
                <div>
                    <table class="product-card">
                        <tr>
                            <th>
                                MSRP: 
                            </th>
                            <td>
                                ${pb.getMSRP()}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Stock: 
                            </th>
                            <td>
                                ${pb.getStock()}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Detail: 
                            </th>
                            <td>
                                ${pb.getProductDetail()}
                            </td>
                        </tr>
                        
                    </table>
                </div>
            </div>
                
        </c:forEach>
            
        </div>
            

        <!-- /.row -->

        <hr>
    </div>
    <!-- /.container -->

    <script src="imports/jquery.matchHeight-min.js"></script>
    <script>
        $(function(){
            $('.portfolio-item').matchHeight();
        });
    </script>
</body>

</html>
