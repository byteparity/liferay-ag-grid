<%@ include file="init.jsp" %>
<portlet:resourceURL var="viewUserInfoURL" id="/view_user_detail" />

<div class="container">
	<br/>
	<div class="row">
	   	<div class="col-sm-9"></div>
	   	<div class="col-sm-2">
	   		<aui:select name="recordSize" label="record-size" onChange="getRecordSizeList()">
	   			<aui:option value="5">5</aui:option>
	   			<aui:option value="10" selected="true">10</aui:option>
	   			<aui:option value="50">50</aui:option>
	   			<aui:option value="100">100</aui:option>
	   			<aui:option value="150">150</aui:option>
	   		</aui:select>
	   	</div>
	  </div><br/>
	   <div class="row">
	   		<div class="col-sm-1"></div>
	    	<div class="col-sm-10">
				<div style="width: 100%; height: 350px;" id="myGrid" class="ag-theme-fresh ag-basic">
			    </div>	  
			</div> 
	   </div>
</div>

<%@ include file="view_js.jsp"%>
