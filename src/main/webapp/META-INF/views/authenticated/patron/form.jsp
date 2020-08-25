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
	<acme:form-textbox code="authenticated.patron.form.label.organisation" path="organisation"/>
	<acme:form-select code="authenticated.patron.form.label.activity-sector" path="activitySector">
		<jstl:if test="${command != 'create'}">
			<acme:form-option code="${activitySector}" value="${activitySector}"/>
		</jstl:if>
		<jstl:forEach items="${sectors}"  var="sector">
			<acme:form-option code="${sector}" value="${sector}"/>
		</jstl:forEach>
	</acme:form-select>
	<acme:form-textbox code="authenticated.patron.form.label.profile" path="profile"/>
	<jstl:if test="${!hasCreditCard and command != 'create'}">
		<acme:form-submit method="get" code="authenticated.patron.form.button.create-credit-card" action="/patron/credit-card/create" />
		<br>
		<br>
	</jstl:if>
	<jstl:if test="${hasCreditCard and command != 'create' and creditCardId != null}">
		<b><acme:message code="authenticated.patron.form.label.credit-card"/></b>
		<acme:form-submit method="get" code="authenticated.patron.form.button.show-credit-card" action="/patron/credit-card/show?id=${creditCardId}" />
		<br>
		<br>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.patron.form.button.create" action="/authenticated/patron/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.patron.form.button.update" action="/authenticated/patron/update"/>
	<acme:form-return code="authenticated.patron.form.button.return"/>
</acme:form>
