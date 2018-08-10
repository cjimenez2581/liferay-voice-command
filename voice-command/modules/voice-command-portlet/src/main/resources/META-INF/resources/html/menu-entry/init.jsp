<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/product-navigation" prefix="liferay-product-navigation" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.portal.kernel.model.Portlet"%>
<%@page import="com.liferay.portal.kernel.model.GroupConstants"%>
<%@page import="com.liferay.portal.kernel.model.LayoutConstants"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.PortletKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil"%>

<%@page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>

<%@page import="com.liferay.portal.kernel.exception.SystemException" %>
<%@page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@page import="com.rivetlogic.speech.util.SpeechCommandPortletKeys"%>
<%@page import="javax.portlet.PortletPreferences"%>

<liferay-frontend:defineObjects />
<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
boolean userSetupComplete = user.isSetupComplete();
%>