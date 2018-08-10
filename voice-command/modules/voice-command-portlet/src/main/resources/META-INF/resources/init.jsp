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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<%@page import="java.util.List"%>
<%@ page import="java.text.Format" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="javax.portlet.PortletPreferences" %>

<%@page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@page import="com.liferay.portal.kernel.util.StringPool" %>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log" %>
<%@page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="javax.portlet.PortletURL"%>

<%@page import="com.rivetlogic.speech.util.SpeechCommandPortletKeys"%>
<%@page import="com.rivetlogic.speech.bean.CommandBean"%>
<%@page import="com.rivetlogic.speech.util.SpeechUtil"%>


<liferay-theme:defineObjects />
<portlet:defineObjects />

<c:set var="pns"><portlet:namespace/></c:set>

<%
String redirect = ParamUtil.getString(request, "redirect");
String currentURL = PortalUtil.getCurrentURL(request);
%>