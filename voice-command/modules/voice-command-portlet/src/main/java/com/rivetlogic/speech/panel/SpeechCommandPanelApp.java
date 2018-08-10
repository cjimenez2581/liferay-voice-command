/**
 * 
 */
package com.rivetlogic.speech.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.rivetlogic.speech.util.SpeechCommandPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Christopher Jimenez
 *
 */
@Component(

	    immediate = true,
	    property = {
	        "panel.app.order:Integer=100",
	        "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_CONFIGURATION
	    },
	    service = PanelApp.class
)
public class SpeechCommandPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID;
	}

	@Override
    @Reference(
        target = "(javax.portlet.name=" + SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID + ")"
    )
    public void setPortlet(Portlet portlet) {
        super.setPortlet(portlet);
    }

}
