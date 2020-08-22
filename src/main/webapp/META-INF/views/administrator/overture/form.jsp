<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="administrator.overture.form.label.title" path="title"/>
	
	<jstl:if test="${command == 'show'}">
		<acme:form-moment code="administrator.overture.form.label.creation-date" path="creationDate" readonly="true"/>	
	</jstl:if>
	
	<acme:form-moment code="administrator.overture.form.label.deadline" path="deadline"/>	
	<acme:form-textarea code="administrator.overture.form.label.description" path="description"/>
	<acme:form-money code="administrator.overture.form.label.min-money" path="minMoney"/>
	<acme:form-money code="administrator.overture.form.label.max-money" path="maxMoney"/>
	<acme:form-textbox code="administrator.overture.form.label.contact-email" path="contactEmail"/>
	

	<acme:form-submit test="${command == 'create'}" code="administrator.overture.form.button.create" action="/administrator/overture/create"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.overture.form.button.update" action="/administrator/overture/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.overture.form.button.delete" action="/administrator/overture/delete"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.overture.form.button.update" action="/administrator/overture/update"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.overture.form.button.delete" action="/administrator/overture/delete"/>	
	
	<acme:form-return code="administrator.overture.form.button.return" />
		
</acme:form>
