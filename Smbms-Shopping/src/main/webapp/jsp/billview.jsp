<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>订单管理页面 >> 信息查看</span>
     </div>
     <div class="providerView">
         <p><strong>订单编号：</strong><span id="billCode"></span></p>
         <p><strong>商品名称：</strong><span id="productName"></span></p>
         <p><strong>商品单位：</strong><span id="productUnit"></span></p>
         <p><strong>商品数量：</strong><span id="productCount"></span></p>
         <p><strong>总金额：</strong><span id="totalPrice"></span></p>
         <p><strong>供应商：</strong><span id="providerName"></span></p>
         <p><strong>是否付款：</strong><span id="isPayment"></span></p>
		<div class="providerAddBtn">
         	<input type="button" id="back" name="back" value="返回" >
        </div>
     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="../js/billview.js"></script>