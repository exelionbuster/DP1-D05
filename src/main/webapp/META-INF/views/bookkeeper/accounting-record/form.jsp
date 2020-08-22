<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<acme:form readonly="true">

	<acme:form-textbox code="bookkeeper.accounting-record.form.label.title" path="title"/>
	<acme:form-moment code="bookkeeper.accounting-record.form.label.creation-date" path="creationDate"/>
	<acme:form-textbox code="bookkeeper.accounting-record.form.label.status" path="status"/>	
	<acme:form-textarea code="bookkeeper.accounting-record.form.label.body" path="body"/>
	
	<acme:form-return code="bookkeeper.accounting-record.form.button.return" />
		
</acme:form>
