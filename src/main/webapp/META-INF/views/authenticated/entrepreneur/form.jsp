<%--
- form.jsp
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

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.startUpName" path="startUpName"/>
	<acme:form-select code="administrator.entrepreneur.form.label.activity-sector" path="activitySector">
		<jstl:if test="${command != 'create'}">
			<acme:form-option code="${activitySector}" value="${activitySector}"/>
		</jstl:if>
		<jstl:forEach items="${sectors}"  var="sector">
			<acme:form-option code="${sector}" value="${sector}"/>
		</jstl:forEach>
	</acme:form-select>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.qualifications" path="qualifications"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.skills" path="skills"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.entrepreneur.form.button.create" action="/authenticated/entrepreneur/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.entrepreneur.form.button.update" action="/authenticated/entrepreneur/update"/>
	<acme:form-return code="authenticated.entrepreneur.form.button.return"/>
</acme:form>
