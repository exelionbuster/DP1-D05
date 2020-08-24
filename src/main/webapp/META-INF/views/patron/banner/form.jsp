<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="noCreditCard" code="patron.banner.form.label.no-credit-card" />
<acme:form>
	<acme:form-textbox code="patron.banner.form.label.picture" path="picture"/>
	<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="patron.banner.form.label.target" path="target"/>
	
	
	<jstl:if test="${command != 'create' and !hasCreditCard}">
		<jstl:set var="creditCardId" value="" />
		<acme:form-textbox code="patron.banner.form.label.credit-card" path="creditCardId" placeholder="${noCreditCard}" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create' and hasCreditCard}">
	<acme:form-select code="patron.banner.form.label.credit-card" path="creditCardId">
		<jstl:if test="${!bannerHasCC}">
			<acme:form-option code="patron.banner.form.credit-card.default" value=""/>
			<acme:form-option code="${creditCardNumber}" value="${creditCardId}"/>
		</jstl:if>
		<jstl:if test="${bannerHasCC}">
			<acme:form-option code="${creditCardNumber}" value="${creditCardId}"/>
			<acme:form-option code="patron.banner.form.credit-card.default" value=""/>
		</jstl:if>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${bannerHasCC and command != 'create'}">
		<acme:form-submit method="get" code="patron.banner.form.button.credit-card" action="/patron/credit-card/show?id=${creditCardId}"/>
		<br>
		<br>
	</jstl:if>
	
	<jstl:if test="${!hasCreditCard and command != 'create'}">
		<acme:form-submit method="get" code="patron.banner.form.button.create-credit-card" action="/patron/credit-card/create" />
		<br>
		<br>
	</jstl:if>

	
	<acme:form-submit test="${command == 'create'}" code="patron.banner.form.button.create" action="/patron/banner/create"/>
	<acme:form-submit test="${command == 'show'}" code="patron.banner.form.button.update" action="/patron/banner/update"/>
	<acme:form-submit test="${command == 'show'}" code="patron.banner.form.button.delete" action="/patron/banner/delete"/>
	<acme:form-submit test="${command == 'update'}" code="patron.banner.form.button.update" action="/patron/banner/update"/>
	<acme:form-submit test="${command == 'update'}" code="patron.banner.form.button.delete" action="/patron/banner/delete"/>
	<acme:form-return code="patron.banner.form.button.return" />
		
</acme:form>
