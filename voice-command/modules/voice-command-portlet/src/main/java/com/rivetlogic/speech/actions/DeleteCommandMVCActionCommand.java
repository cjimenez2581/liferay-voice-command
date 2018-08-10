package com.rivetlogic.speech.actions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.rivetlogic.speech.util.SpeechCommandPortletKeys;
import com.rivetlogic.speech.util.SpeechUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
			"javax.portlet.name=" +  SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID,
			"mvc.command.name=deleteCommand"
	},
	service = MVCActionCommand.class
)
public class DeleteCommandMVCActionCommand extends BaseMVCActionCommand  {

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
		try {
	        SpeechUtil.deleteVoiceCommand(actionRequest);
	    } catch (ReadOnlyException | ValidatorException | IOException e) {
	    	LOG.error(e);
	    }
	}

	private static final Log LOG = LogFactoryUtil.getLog(DeleteCommandMVCActionCommand.class);
}
