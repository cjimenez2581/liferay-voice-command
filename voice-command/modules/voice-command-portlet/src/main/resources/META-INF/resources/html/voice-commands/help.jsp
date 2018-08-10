<%--
/**
 * Copyright (C) 2005-2014 Rivet Logic Corporation.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 */
--%>


<%@include file="/init.jsp" %>

<%
PortletURL viewURL=renderResponse.createRenderURL();
viewURL.setWindowState(LiferayWindowState.NORMAL);
viewURL.setPortletMode(PortletMode.VIEW);
%>

<aui:container>
	<aui:row>
		<aui:col width="100">
			<a class="pull-right" href="<%=viewURL.toString()%>" title="Back"><span class="icon-circle-arrow-left"></span> Back</a>
			<div class="text-center">
				<a href="http://www.rivetlogic.com" target="_blank">
					<img src="<%=renderRequest.getContextPath()%>/rivetlogic-logo.png" style="max-width:200px;margin-bottom:15px;"/>
				</a>
			</div>
			<p>
			Thank you for using this application.<br/>
			We welcome feature/bug reports concerning this app and other improvements you may want. 
			Discussion helps clarify the ways the app can be used and also helps define directions for future development.<br/></br>
			Please post your concerns at <a href="http://issues.rivetlogic.com/browse/LRA">issues.rivetlogic.com/browse/LRA</a>.
			You may also want to check the app documentation <a href="http://wiki.rivetlogic.com/display/LRA/Liferay+Apps+Home">here</a>
			</p>
			<p>
			Rivet Logic's Liferay Marketplace apps team.<br/>
			<a href="http://www.rivetlogic.com" target="_blank">http://www.rivetlogic.com</a>
			</p>
		</aui:col>
	</aui:row>
</aui:container>