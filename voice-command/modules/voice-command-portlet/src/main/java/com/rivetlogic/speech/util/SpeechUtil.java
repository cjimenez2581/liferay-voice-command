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
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.rivetlogic.speech.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.rivetlogic.speech.bean.CommandBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class SpeechUtil {
    public static List<CommandBean> subList(List<CommandBean> list, Integer start, Integer end) {
        if (list != null) {
            return ListUtil.subList(list, start, end);
        } else {
            return new ArrayList<CommandBean>();
        }
    }
    
    public static void deleteVoiceCommand(ActionRequest request) 
            throws PortalException, SystemException, ReadOnlyException, ValidatorException, IOException {

        String commandKey = ParamUtil.get(request, SpeechCommandPortletKeys.VOICE_COMMAND, StringPool.BLANK);
        deleteVoiceCommand(request, commandKey);
    }
    
    public static PortletPreferences deleteVoiceCommand(ActionRequest request, String voiceCommand) 
            throws PortalException, SystemException, ReadOnlyException, ValidatorException, IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long plid = themeDisplay.getLayout().getPlid();
        long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
        int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;
        long companyId = themeDisplay.getCompanyId();

        PortletPreferences preference = request.getPreferences();
        Map<String, String[]> preferencesMap = new HashMap<String, String[]>(preference.getMap());
        preferencesMap.remove(voiceCommand);

        PortletPreferencesLocalServiceUtil.deletePortletPreferences(ownerId, ownerType, plid, SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID);

        preference = PortletPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType, plid,
                SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID);
        
        for (Map.Entry<String, String[]> entry : preferencesMap.entrySet()) {
            preference.setValue(entry.getKey(), entry.getValue()[0]);
        }
        preference.store();
        return preference;
    }
    
}
