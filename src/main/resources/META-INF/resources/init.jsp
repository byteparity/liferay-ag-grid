<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<c:set var = "now" value = "<%= new java.util.Date()%>" />
<fmt:formatDate var="temp" pattern="s" type="time" value="${now}" />
<script src="<%=request.getContextPath()%>/js/ag-grid.min.js?${temp}"></script>

<liferay-theme:defineObjects />
<portlet:defineObjects />
