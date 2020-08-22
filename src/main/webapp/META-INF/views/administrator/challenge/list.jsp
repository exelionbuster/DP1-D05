<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.challenge.list.label.title" path="title" width="40%"/>
	<acme:list-column code="administrator.challenge.list.label.deadline" path="deadline" width="60%"/>		
</acme:list>