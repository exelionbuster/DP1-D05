<%@page language="java"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message var="nostars" code="administrator.tool-record.form.nostars"/>

<acme:form>
	
	<acme:form-textbox code="administrator.tool-record.form.label.title" path="title" />
	<acme:form-select code="administrator.tool-record.form.label.activity-sector" path="activitySector">
		<jstl:if test="${command !=create}">
			<acme:form-option code="${activitySector}" value="${activitySector}"/>
		</jstl:if>
		<jstl:forEach items="${sectors}"  var="sector">
			<acme:form-option code="${sector}" value="${sector}"/>
		</jstl:forEach>
	</acme:form-select>
	<acme:form-textbox code="administrator.tool-record.form.label.inventor" path="inventor" />
	<acme:form-textbox code="administrator.tool-record.form.label.description" path="description" />
	<acme:form-url code="administrator.tool-record.form.label.website" path="webSite" />
	<acme:form-textbox code="administrator.tool-record.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.tool-record.form.label.open-source" path="openSource" />
	<jstl:choose>
    	<jstl:when test="${stars == null}">
        	<acme:form-double code="administrator.tool-record.form.label.stars" path="stars" placeholder="${nostars}"/>
    	</jstl:when>
    	<jstl:otherwise>
        	<acme:form-double code="administrator.tool-record.form.label.stars" path="stars" />
   	 </jstl:otherwise>
	</jstl:choose>
	
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.tool-record.form.button.create" action="/administrator/tool-record/create"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.tool-record.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.tool-record.form.button.delete" action="/administrator/tool-record/delete"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.tool-record.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.tool-record.form.button.delete" action="/administrator/tool-record/delete"/>
	
	<acme:form-return code="administrator.tool-record.form.button.return" />
		
</acme:form>
