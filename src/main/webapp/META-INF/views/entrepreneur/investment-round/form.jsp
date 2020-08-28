<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>

	<jstl:if test="${!isFinalMode}">
	
		<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title"/>
	
		<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker"/>
	
		<jstl:if test="${command != 'create'}">
			<acme:form-moment code="entrepreneur.investment-round.form.label.creation-date" path="creationDate" readonly="true"/>
		</jstl:if>
		
		<acme:form-select code="entrepreneur.investment-round.form.label.kind" path="kind">
			<jstl:if test="${command !='create'}">
				<acme:form-option code="${kind}" value="${kind}"/>
			</jstl:if>
			<jstl:forEach items="${kinds}"  var="kindC">
				<acme:form-option code="${kindC}" value="${kindC}"/>
			</jstl:forEach>
		</acme:form-select>
		
		<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description"/>
		<acme:form-money code="entrepreneur.investment-round.form.label.amount" path="amount"/>
		<acme:form-url code="entrepreneur.investment-round.form.label.link" path="link"/>
	
		<jstl:if test="${command != 'create'}">
			<acme:form-checkbox code="entrepreneur.investment-round.form.label.finalMode" path="finalMode"/>
		</jstl:if>
	
		<acme:form-submit test="${command == 'create'}" code="entrepreneur.investment-round.form.button.create" action="/entrepreneur/investment-round/create" />
		<acme:form-submit test="${command == 'update'}" code="entrepreneur.investment-round.form.button.update" action="/entrepreneur/investment-round/update" />
		<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.update" action="/entrepreneur/investment-round/update" />	
	
	</jstl:if>
	
	<jstl:if test="${isFinalMode}">
		<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker" readonly="true"/>
	
		<jstl:if test="${command != 'create'}">
			<acme:form-moment code="entrepreneur.investment-round.form.label.creation-date" path="creationDate" readonly="true"/>
		</jstl:if>
	
		<acme:form-textbox code="entrepreneur.investment-round.form.label.kind" path="kind" readonly="true"/>	
		<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description" readonly="true"/>
		<acme:form-money code="entrepreneur.investment-round.form.label.amount" path="amount" readonly="true"/>
		<acme:form-url code="entrepreneur.investment-round.form.label.link" path="link" readonly="true"/>
	
	</jstl:if>

	<jstl:if test="${activities != null}">
		<acme:form-submit test="${command != 'create'}" method="get" code="entrepreneur.investment-round.form.button.activities" action="/entrepreneur/activity/list?id=${id}"/>
	</jstl:if>
	
	<jstl:if test="${!isFinalMode}">
		<jstl:if test="${command != 'create'}">
			<acme:form-return  code="entrepreneur.investment-round.form.button.new-activity" action="/entrepreneur/activity/create?investmentRoundId=${id}" />
		</jstl:if>
	</jstl:if>
	
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.investment-round.form.button.delete" action="/entrepreneur/investment-round/delete"/>
	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.delete" action="/entrepreneur/investment-round/delete" />
	<acme:form-submit test="${command == 'delete'}" code="entrepreneur.investment-round.form.button.delete" action="/entrepreneur/investment-round/delete" />
	
	<acme:form-return code="entrepreneur.investment-round.form.button.return" />
		
</acme:form>
