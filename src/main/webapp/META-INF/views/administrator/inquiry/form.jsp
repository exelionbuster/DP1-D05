<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="administrator.inquiry.form.label.title" path="title"/>
	
	<jstl:if test="${command == 'show'}">
		<acme:form-moment code="administrator.inquiry.form.label.creation-date" path="creationDate" readonly="true"/>	
	</jstl:if>
	
	<acme:form-moment code="administrator.inquiry.form.label.deadline" path="deadline"/>	
	<acme:form-textarea code="administrator.inquiry.form.label.description" path="description"/>
	<acme:form-money code="administrator.inquiry.form.label.min-money" path="minMoney"/>
	<acme:form-money code="administrator.inquiry.form.label.max-money" path="maxMoney"/>
	<acme:form-textbox code="administrator.inquiry.form.label.contact-email" path="contactEmail"/>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.inquiry.form.button.create" action="/administrator/inquiry/create"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.inquiry.form.button.update" action="/administrator/inquiry/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.inquiry.form.button.delete" action="/administrator/inquiry/delete"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.inquiry.form.button.update" action="/administrator/inquiry/update"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.inquiry.form.button.delete" action="/administrator/inquiry/delete"/>
	
	<acme:form-return code="administrator.inquiry.form.button.return" />
		
</acme:form>
