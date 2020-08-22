<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="anonymous.technology-record.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.technology-record.list.label.licence" path="licence" width="20%"/>
	<acme:list-column code="anonymous.technology-record.list.label.activity-sector" path="activitySector" width="20%"/>
	<acme:list-column code="anonymous.technology-record.list.label.stars" path="stars" width="20%"/>	
	
</acme:list>