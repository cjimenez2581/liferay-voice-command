/**
 * 
 */
package com.rivetlogic.speech.controlmenuentry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.product.navigation.control.menu.BaseJSPProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Christopher Jimenez
 *
 */
@Component(
		immediate = true,
		property = {
			"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.USER,
			"product.navigation.control.menu.entry.order:Integer=100"
		},
		service = ProductNavigationControlMenuEntry.class
	)
public class SpeechNavigationControlMenuEntry extends BaseJSPProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	/**
	 * Returns the Control Menu entry's label stored in the module's resource
	 * bundle.
	 *
	 * @param  locale the label's locale
	 * @return the Control Menu entry's label
	 */
	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "javax.portlet.title.VoiceCommandPortlet");
	}

	@Override
	public String getBodyJspPath() {
		return "/html/menu-entry/view.jsp";
	}

	@Override
	public String getIconJspPath() {
		return "/html/menu-entry/icon.jsp";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=voice.command.portlet)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	public boolean isShow(HttpServletRequest request) throws PortalException {
		User user = PortalUtil.getUser(request);
		return user.isSetupComplete();
	}
}
