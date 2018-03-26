package com.byteparity.aggrid.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.byteparity.aggrid.constants.AgGridMVCPortletKeys;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" +AgGridMVCPortletKeys.PORTLET_ID,
	        "mvc.command.name=/view_user_detail"
	    },
	    service = MVCResourceCommand.class
	)
public class ViewUserDetailMVCResourceCommand implements MVCResourceCommand{
	
	private Log _log = LogFactoryUtil.getLog(ViewUserDetailMVCResourceCommand.class.getName());
	
	/**
	 * This method will be called when Apply Filter is clicked from ag grid
	 */
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// GET PARAMETER VALUE
		int recordSize = ParamUtil.getInteger(resourceRequest, "recordSize");
		String firstName = ParamUtil.getString(resourceRequest, "filterModel[firstName][filter]");
		String firstNameFilterType = ParamUtil.getString(resourceRequest, "filterModel[firstName][type]");
		String lastName = ParamUtil.getString(resourceRequest, "filterModel[lastName][filter]");
		String lastNameFilterType = ParamUtil.getString(resourceRequest, "filterModel[lastName][type]");
		String emailAddress = ParamUtil.getString(resourceRequest, "filterModel[emailAddress][filter]");
		String emailAddressFilterType = ParamUtil.getString(resourceRequest, "filterModel[emailAddress][type]");
		String create = ParamUtil.getString(resourceRequest, "filterModel[createDate][dateFrom]");
		String createFilterType = ParamUtil.getString(resourceRequest, "filterModel[createDate][type]");
		String modified = ParamUtil.getString(resourceRequest, "filterModel[modifiedDate][dateFrom]");
		String modifiedFilterType = ParamUtil.getString(resourceRequest, "filterModel[modifiedDate][type]");
		
		// SET FILTER CRITEAREA
		DynamicQuery dynamicQuery = UserLocalServiceUtil.dynamicQuery();
		Criterion criterion = null;
		if (Validator.isNotNull(firstName)) {
			if (firstNameFilterType.equals("contains")) {
				criterion = RestrictionsFactoryUtil.like("firstName",
						StringPool.PERCENT + firstName + StringPool.PERCENT);
			} else if (firstNameFilterType.equals("equals")) {
				criterion = RestrictionsFactoryUtil.eq("firstName", firstName);
			}
			dynamicQuery.add(criterion);
		}
		if (Validator.isNotNull(lastName)) {
			if (lastNameFilterType.equals("contains")) {
				criterion = RestrictionsFactoryUtil.like("lastName",
						StringPool.PERCENT + lastName + StringPool.PERCENT);
			} else if (lastNameFilterType.equals("equals")) {
				criterion = RestrictionsFactoryUtil.eq("lastName", lastName);
			}
			dynamicQuery.add(criterion);
		}
		if (Validator.isNotNull(emailAddress)) {
			if (emailAddressFilterType.equals("contains")) {
				criterion = RestrictionsFactoryUtil.like("emailAddress",
						StringPool.PERCENT + emailAddress + StringPool.PERCENT);
			} else if (emailAddressFilterType.equals("equals")) {
				criterion = RestrictionsFactoryUtil.eq("emailAddress", emailAddress);
			}
			dynamicQuery.add(criterion);
		}
		if (Validator.isNotNull(create)) {
			Date createDate = ParamUtil.getDate(resourceRequest, "filterModel[createDate][dateFrom]", formatter);
			if (createFilterType.equals("equals")) {
				calendar.setTime(createDate);
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				Date filterDate = calendar.getTime();
				dynamicQuery.add(PropertyFactoryUtil.forName("createDate").between(createDate, filterDate));
			} else if (createFilterType.equals("lessThan")) {
				calendar.setTime(createDate);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				Date filterDate = calendar.getTime();
				dynamicQuery.add(RestrictionsFactoryUtil.lt("createDate", filterDate));
			} else if (createFilterType.equals("greaterThan")) {
				calendar.setTime(createDate);
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				Date filterDate = calendar.getTime();
				dynamicQuery.add(RestrictionsFactoryUtil.gt("createDate", filterDate));
			}

		}
		if (Validator.isNotNull(modified)) {
			Date modifiedDate = ParamUtil.getDate(resourceRequest, "filterModel[modifiedDate][dateFrom]", formatter);
			if (modifiedFilterType.equals("equals")) {
				calendar.setTime(modifiedDate);
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				Date filterDate = calendar.getTime();
				dynamicQuery.add(PropertyFactoryUtil.forName("modifiedDate").between(modifiedDate, filterDate));
			} else if (modifiedFilterType.equals("lessThan")) {
				calendar.setTime(modifiedDate);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				Date filterDate = calendar.getTime();
				dynamicQuery.add(RestrictionsFactoryUtil.lt("modifiedDate", filterDate));
			} else if (modifiedFilterType.equals("greaterThan")) {
				calendar.setTime(modifiedDate);
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				Date filterDate = calendar.getTime();
				dynamicQuery.add(RestrictionsFactoryUtil.gt("modifiedDate", filterDate));
			}
		}
		if (recordSize == 0) {
			dynamicQuery.setLimit(0, 10);
		} else {
			dynamicQuery.setLimit(0, recordSize);
		}

		// GET FINAL RESULT
		List<User> users = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
		JSONObject jsonObject = userJSONObject(users, formatter, recordSize);

		try {
			resourceResponse.getWriter().println(jsonObject);
		} catch (IOException e) {
			_log.error(e.getMessage());
		}
		return true;
	}

	/**
	 * Return users information
	 * 
	 * @param users
	 * @param formatter
	 * @param recordSize
	 * @return
	 */
	private JSONObject userJSONObject(List<User> users, SimpleDateFormat formatter, int recordSize) {
		JSONObject result = null;
		JSONArray data = JSONFactoryUtil.createJSONArray();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		for (User user : users) {
			result = JSONFactoryUtil.createJSONObject();
			result.put("firstName", user.getFirstName());
			result.put("lastName", user.getLastName());
			result.put("emailAddress", user.getEmailAddress());
			result.put("createDate", formatter.format(user.getCreateDate()));
			result.put("modifiedDate", formatter.format(user.getModifiedDate()));
			data.put(result);
		}

		jsonObject.put("data", data);
		jsonObject.put("success", "success");
		if (recordSize == 0) {
			jsonObject.put("count", 10);
		} else {
			jsonObject.put("count", recordSize);
		}
		return jsonObject;
	}
}
