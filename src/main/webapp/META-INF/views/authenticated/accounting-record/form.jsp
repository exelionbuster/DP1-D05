<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<acme:form readonly="true">

	<acme:form-textbox code="authenticated.accounting-record.form.label.title" path="title"/>
	
	<acme:form-checkbox code="authenticated.accounting-record.form.label.publish" path="published"/>

	<jstl:if test="${command!='create'}">
		<acme:form-moment code="authenticated.accounting-record.form.label.creation-date" path="creationDate"/>
		<acme:form-textbox code="authenticated.accounting-record.form.label.status" path="status"/>	
	</jstl:if>
	
	<acme:form-textarea code="authenticated.accounting-record.form.label.body" path="body"/>
	
	<acme:form-return code="authenticated.accounting-record.form.button.return" />
	
</acme:form>
