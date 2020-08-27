<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<acme:form>

	<!-- draft -->

	<jstl:if test="${!isPublished}">

	<acme:form-textbox code="bookkeeper.accounting-record.form.label.title" path="title"/>
	
	<acme:form-checkbox code="bookkeeper.accounting-record.form.label.publish" path="published"/>

	<jstl:if test="${command!='create'}">
		<acme:form-moment code="bookkeeper.accounting-record.form.label.creation-date" path="creationDate" readonly="true"/>
		<acme:form-textbox code="bookkeeper.accounting-record.form.label.status" path="status" readonly="true"/>	
	</jstl:if>
	
	<acme:form-textarea code="bookkeeper.accounting-record.form.label.body" path="body"/>
	
	<acme:form-submit test="${command == 'show'}" code="bookkeeper.accounting-record.form.button.update" action="/bookkeeper/accounting-record/update"/>
	<acme:form-submit test="${command == 'update'}" code="bookkeeper.accounting-record.form.button.update" action="/bookkeeper/accounting-record/update"/>
	
	</jstl:if>
	
	<!-- published -->

	<jstl:if test="${isPublished}">
	
	<acme:form-textbox code="bookkeeper.accounting-record.form.label.title" path="title" readonly="true"/>
	
	<jstl:if test="${command!='create'}">
		<acme:form-moment code="bookkeeper.accounting-record.form.label.creation-date" path="creationDate" readonly="true"/>
		<acme:form-textbox code="bookkeeper.accounting-record.form.label.status" path="status" readonly="true"/>	
	</jstl:if>
	
	<acme:form-textarea code="bookkeeper.accounting-record.form.label.body" path="body" readonly="true"/>
	
	</jstl:if>
		
	<acme:form-submit test="${command == 'create'}" code="bookkeeper.accounting-record.form.button.create" action="/bookkeeper/accounting-record/create?investmentRoundId=${investmentRoundId}"/>
	
	<acme:form-return code="bookkeeper.accounting-record.form.button.return" />
	
</acme:form>
