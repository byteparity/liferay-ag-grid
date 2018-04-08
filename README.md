# liferay-ag-grid

[Ag-Grid](https://www.ag-grid.com) is feature-rich open source datagrid. Easily integrate in liferay framework to deliver filtering, grouping, aggregation, pivoting and much more. By using ag grid you do not have to code for providing different filters.

Ag-Grid Portlet hase below capabilities.

* Specify the display Record Size
* Text Filter like contains,equals etc.
* Date Filter like equals,less than,greater than 
* Specify the column wise different  filter

## Environment

* Liferay 7 - GA4 +
*	MySQL 5.6 +

## How to use

1. Download,build and install ag-grid portlet jar on your server.
2. Check module status in liferay server using console log OR using gogo shell.
3. Now add Ag Grid portlet on specific page. For Demo purpose we have not created any custom entity, we have directly fire queries on User entity.

![ScreenShot](https://user-images.githubusercontent.com/24852574/38452673-44d876fa-3a66-11e8-8692-cd4fa99573a4.jpg)

By Default ag grid shows 10 records, but you can specify display record size as shown in below screenshot.

![ScreenShot](https://user-images.githubusercontent.com/24852574/38452694-ce749a6a-3a66-11e8-9478-771dda97cb80.jpg)

For demo purpose we have set text filter like contains and equals for FirstName, LastName, and Email Address. Also Date filters like equals, less than and grater than for Create Date and Modified Date.

![ScreenShot](https://user-images.githubusercontent.com/24852574/38452717-7298a424-3a67-11e8-8822-77d8b9caa180.jpg)

![ScreenShot](https://user-images.githubusercontent.com/24852574/38452718-72cfd6ec-3a67-11e8-988d-11b6b45c7314.jpg)

