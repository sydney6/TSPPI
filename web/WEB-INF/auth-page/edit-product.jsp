<%-- 
    Document   : edit-product
    Created on : Oct 17, 2016, 6:40:29 PM
    Author     : cruzsyd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/form.css" rel="stylesheet" type="text/css">
        
    </head>
    <body>
        <%@include file="../static-page/navbar.jsp" %>
        
        <header id="login-header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h1>Edit Product</h1>
                    </div>
                </div>
            </div>
	</header>
        <section>
            <div id="login-body">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-md-offset-3">
                            <div class="panel panel-login">
                                <div class="panel-heading">
                                    <hr>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form id="edit-form" action="editproductservlet" method="post" autocomplete="off">
                                                <c:forEach var="pb" items="${pb}">
                                                <div class="form-group">
                                                    <input type="text" name="product_name" id="product_name" class="form-control" placeholder="Product Name" value="${pb.getProductName()}">
                                                </div>                       
                                                <div class="form-group">
                                                        <input type="number" step="0.01" name="msrp" id="msrp" class="form-control" placeholder="MSRP" value="${pb.getMSRP()}">
                                                </div>
                                                <div class="form-group">
                                                    <input type="number" name="stock" id="stock" class="form-control" placeholder="Stock" value="${pb.getStock()}">
                                                </div>		
                                                <div class="form-group">
                                                    <textarea rows="4" cols="50" name="product_detail" id="product_detail" class="form-control" placeholder="Details about the product">${pb.getProductDetail()}</textarea>
                                                </div>
                                                </c:forEach>
                                                <div class="form-group text-center">
                                                    <label>Product Category: </label>
                                                    <select name="product_category">
                                                        <c:forEach var="pcb" items="${pcb}">
                                                            <option value="${pcb.getCategoryID()}">${pcb.getCategoryName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-sm-6 col-sm-offset-3"> 
                                                            <c:forEach var="pb" items="${pb}">
                                                                <input type="hidden" name="product_id" value="${pb.getProductID()}">
                                                            </c:forEach>
                                                            <input type="submit" name="submit" id="register-submit" class="form-control btn btn-register" value="Save Changes">
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="text-center">
                                                                <a href="productapproval" class="forgot-password">Approve products.</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="text-center">
                                                                <a href="products" class="forgot-password">See all products.</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>                 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	</section>
    </body>
</html>