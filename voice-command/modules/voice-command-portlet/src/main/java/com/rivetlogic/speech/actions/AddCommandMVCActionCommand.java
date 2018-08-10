package com.rivetlogic.speech.actions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.rivetlogic.speech.util.SpeechCommandPortletKeys;
import com.rivetlogic.speech.util.SpeechUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
			"javax.portlet.name=" +  SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID,
			"mvc.command.name=addCommandAction"
	},
	service = MVCActionCommand.class
)
public class AddCommandMVCActionCommand extends BaseMVCActionCommand  {

	/**
	 * Action to add new speech command and its value.
	 *
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @throws PortalException
	 * @throws SystemException
	 */
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
	 PortletPreferences preference;
	    try {
	        String voiceCommand = ParamUtil.getString(actionRequest, SpeechCommandPortletKeys.DELETE_VOICE_COMMAND);
	        if(voiceCommand != null && !voiceCommand.trim().isEmpty()) {
	            preference = SpeechUtil.deleteVoiceCommand(actionRequest, voiceCommand);
	        } else {
	            preference = actionRequest.getPreferences();
	        }
	        preference.setValue(SpeechCommandPortletKeys.KEY_PHRASE, 
	        		ParamUtil.getString(actionRequest, SpeechCommandPortletKeys.KEY_PHRASE));
	        preference.setValue(ParamUtil.getString(actionRequest, SpeechCommandPortletKeys.VOICE_COMMAND), 
	                ParamUtil.getString(actionRequest, SpeechCommandPortletKeys.VOICE_COMMAND_VALUE));
	        preference.store();

	        SessionMessages.add(actionRequest, SpeechCommandPortletKeys.SESSION_MESSAGE_SUCCESS);
	    } catch (ReadOnlyException | ValidatorException | IOException e) {
	      LOG.error(e);
	    }
	}

	private static final Log LOG = LogFactoryUtil.getLog(AddCommandMVCActionCommand.class);
}
