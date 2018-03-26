<%@page import="com.byteparity.aggrid.constants.AgGridMVCPortletKeys"%>
<script type="text/javascript">
	
	AUI().ready('liferay-portlet-url', function(A) {
		var eGridDiv = document.querySelector('#myGrid');
	   	new agGrid.Grid(eGridDiv, gridOptions);
	   	setRowData(null);
	});

	var columnDefs = [
				{
					menuTabs: ['filterMenuTab'], 
					headerName: "<liferay-ui:message key='user-first-name'/>", 
					field: "firstName", 
					editable: false, 
					width: 160, 
					filter: 'text',
					filterParams: {
						filterOptions:['contains','equals'], 
						caseSensitive:true, 
						applyButton:true,
						clearButton:true
					},
					newRowsAction: 'keep', minWidth: 150
				},{
					menuTabs: ['filterMenuTab'], 
					headerName: "<liferay-ui:message key='user-last-name'/>", 
					field: "lastName", 
					editable: false, 
					width: 160, 
					filter: 'text',
					filterParams: {
						filterOptions:['contains','equals'], 
						caseSensitive:true, 
						applyButton:true,
						clearButton:true
					},
					newRowsAction: 'keep', minWidth: 150
				},{
					menuTabs: ['filterMenuTab'], 
					headerName: "<liferay-ui:message key='user-email-address'/>", 
					field: "emailAddress", 
					editable: false, 
					width: 260, 
					filter: 'text',
					filterParams: {
						filterOptions:['contains','equals'], 
						caseSensitive:true, 
						applyButton:true,
						clearButton:true
					},
					newRowsAction: 'keep', minWidth: 260
				},{
					menuTabs: ['filterMenuTab'], 
					headerName: "<liferay-ui:message key='user-create-date'/>", 
					field: "createDate", 
					width: 180, 
					minWidth: 180,
					filter: 'date', 
					editable: false, 
					filterParams: {
						filterOptions:['equals','lessThan','greaterThan'], 
						applyButton:true,
						clearButton:true
					}, 
					newRowsAction: 'keep'
				},{
					menuTabs: ['filterMenuTab'], 
					headerName: "<liferay-ui:message key='user-modified-date'/>", 
					field: "modifiedDate", 
					width: 180, 
					minWidth: 180,
					filter: 'date', 
					editable: false, 
					filterParams: {
						filterOptions:['equals','lessThan','greaterThan'], 
						applyButton:true,
						clearButton:true
					}, 
					newRowsAction: 'keep'
				}
			];
	
	var gridOptions = {
		columnDefs: columnDefs,
     	rowData: null,
     	enableServerSideFilter: true,
     	paginationPageSize: 10,
        domLayout: 'autoHeight',
     	rowModelType: 'infinite',
     	pagination: true,
     	animateRows: true,
   		onFilterChanged: function(e) {
   			setRowData(null);	
   		},
   		onFilterModified: function() {
   			console.log("onFilterModified event");
   		},
   		onGridSizeChanged: function(params) {
   		    var responsiveCols = ['status', 'recommendationText', 'sompoCompanyId'];
   		    var isFullGrid = $('#myGrid').width() >= 768;
   		    gridOptions.columnApi.setColumnsVisible(responsiveCols, isFullGrid);
   		    gridOptions.columnApi.setColumnsVisible(['more'], !isFullGrid);
   		    gridOptions.api.sizeColumnsToFit();
   		}
  	};
	function getRecordSizeList(){
  		var recordSize = $("#<portlet:namespace/>recordSize").val();
  		setRowData(recordSize);
	}
  	function setRowData(recordSize) {
      var dataSource = {
          rowCount: null, // behave as infinite scroll
          getRows: function (params) {
              var resourceURL = Liferay.PortletURL.createResourceURL();
              resourceURL.setParameter("startRow", params.startRow);
              resourceURL.setParameter("endRow", params.endRow);
              resourceURL.setParameter("recordSize", recordSize);
              resourceURL.setParameter("filterModel", params.filterModel);
              resourceURL.setDoAsGroupId('true');
              resourceURL.setPortletId('<%= AgGridMVCPortletKeys.PORTLET_ID%>');
              resourceURL.setResourceId('/view_user_detail');
              console.log("resourceURL: " + resourceURL.toString());
              
              $.ajax({
			    method: 'GET',
			    cache: false,
			    dataType: 'json',
			    url:  resourceURL.toString(),
			    success: function(result) {
			    	if (result.success) {
			         	params.successCallback(result.data, result.count);
	                }
			    }
              });
          }
      };
      gridOptions.api.setDatasource(dataSource);
	}
    
</script>