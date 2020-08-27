<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.bookkeeper-request.form.label.auth-name" path="authName" readonly="true"/>
	<acme:form-textbox code="administrator.bookkeeper-request.form.label.firm-name" path="firmName" readonly="true"/>
	<acme:form-textbox code="administrator.bookkeeper-request.form.label.responsibility-statement" path="responsibilityStatement" readonly="true"/>
	<jstl:if test="${!isAccepted}">
		<acme:form-checkbox code="administrator.bookkeeper-request.form.label.accepted" path="accepted" />
		<acme:form-submit test="${command == 'show'}" code="administrator.bookkeeper-request.form.button.update" action="/administrator/bookkeeper-request/update"/>
		<acme:form-submit test="${command == 'update'}" code="administrator.bookkeeper-request.form.button.update" action="/administrator/bookkeeper-request/update"/>
	</jstl:if>
	<jstl:if test="${isAccepted}">
		<acme:form-checkbox code="administrator.bookkeeper-request.form.label.accepted" path="accepted" readonly="true"/>
	</jstl:if>

	
	<acme:form-return code="administrator.bookkeeper-request.form.button.return" />
		
</acme:form>