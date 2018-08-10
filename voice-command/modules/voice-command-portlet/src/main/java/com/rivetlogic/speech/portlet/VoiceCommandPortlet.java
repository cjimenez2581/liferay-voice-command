package com.rivetlogic.speech.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.rivetlogic.speech.bean.CommandBean;
import com.rivetlogic.speech.bean.impl.CommandBeanImpl;
import com.rivetlogic.speech.util.SpeechCommandPortletKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author christopherjimenez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Voice Command Portlet",
		"javax.portlet.init-param.template-path=/",
		//"javax.portlet.init-param.edit-jsp=/html/voice-commands/edit.jsp",
		"javax.portlet.init-param.help-jsp=/html/voice-commands/help.jsp",
		"javax.portlet.init-param.view-template=/html/voice-commands/view.jsp",
		"javax.portlet.portlet-mode=text/html;view,help",
		"javax.portlet.name=" + SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class VoiceCommandPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		 String key;
		    List<CommandBean> commandBeans = new ArrayList<CommandBean>();
		
		    PortletPreferences preference = renderRequest.getPreferences();
		
		    Map<String, String[]> preferenceMap = preference.getMap();
		    for (Map.Entry<String, String[]> entry : preferenceMap.entrySet()) {
		        key = entry.getKey().trim();
		        if (!key.isEmpty() && !key.equalsIgnoreCase(SpeechCommandPortletKeys.KEY_PHRASE)) {
		            commandBeans.add(new CommandBeanImpl(entry.getKey(), entry.getValue()[0]));
		        }
		    }
		    renderRequest.setAttribute(SpeechCommandPortletKeys.VOICE_COMMAND_COUNT, commandBeans.size());
		    renderRequest.setAttribute(SpeechCommandPortletKeys.VOICE_COMMAND_LIST, commandBeans);
		    SessionMessages.clear(renderRequest);
		super.render(renderRequest, renderResponse);
	}
	    
}