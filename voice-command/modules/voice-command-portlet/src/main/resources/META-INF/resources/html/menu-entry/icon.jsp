<%@ include file="/html/menu-entry/init.jsp" %>
<%
	String key;
	String keyWord = "";
	String KEY_WORD = "key_phrase";
	String jsResultMap = "{";
	String jsResultArray = "[";
	long plidCP = 0;
	long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
	int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;
	long companyId = themeDisplay.getCompanyId();
	String portletId = SpeechCommandPortletKeys.VOICE_COMMAND_PORTLET_ID;

	try {
		Group groupTemp = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.CONTROL_PANEL);
		List<Layout> layoutList = LayoutLocalServiceUtil.getLayouts(groupTemp.getGroupId(), true, LayoutConstants.TYPE_CONTROL_PANEL);
		if(layouts != null && !layoutList.isEmpty()) {
			plidCP = layoutList.get(0).getPlid(); 
		}
		PortletPreferences prefer = PortletPreferencesLocalServiceUtil.getPreferences(companyId, ownerId, ownerType, plidCP, portletId);
		if(prefer != null) {
			keyWord = prefer.getValue(KEY_WORD, StringPool.BLANK);
			
			for(Map.Entry<String, String[]> entry : prefer.getMap().entrySet()) {
				key = entry.getKey().trim();
				if (!key.isEmpty() && !key.equalsIgnoreCase(KEY_WORD)) {
					if(jsResultArray.equals("[")) {
						jsResultArray += "'" + key + "'";
					} else {
						jsResultArray += ",'" + key + "'";
					}
					if(jsResultMap.equals("{")) {
						jsResultMap += "'" + key + "':'" + entry.getValue()[0] + "'";
					} else {
						jsResultMap += ",'" + key + "':'" + entry.getValue()[0] + "'";
					}
				}
			}
		}
		jsResultMap += "}";
		jsResultArray += "]";
	} catch (SystemException | PortalException e) {
		e.printStackTrace();
	}
%>
<aui:nav-item cssClass="speech" dropdown="false" id="speech" label="">
	<button id="start_button">
		<img id="start_img" class="inactive" src="<%=request.getContextPath()%>/image/mic.gif" alt="Start" width="36px">	
	</button>
</aui:nav-item>

	<c:if test="<%= !layout.getGroup().isControlPanel() && themeDisplay.isSignedIn() %>">
		<div id="speechresults" class="speechresults">
	</c:if>
	<c:if test="<%= layout.getGroup().isControlPanel() && themeDisplay.isSignedIn() %>">
		<div id="speechresults" class="speechresults-cpanel">
	</c:if>
		<span id="final_span" class="final"></span>
		<span id="interim_span" class="interim"></span>
		<p>
	</div>


<aui:script use="speechrecognition">
	var signedIn = <%= themeDisplay.isSignedIn() %>;
	var speechRecognition;
	if(signedIn) {
		speechRecognition = new A.speechrecognition.SpeechRecognition(
			{
				commandsMap: <%= jsResultMap %>,
				commandsKey: <%= jsResultArray %>,
				keyWord: '<%= keyWord %>',
				userSetupComplete: <%= userSetupComplete %>,
				urlParamOptions: {
					screenName: '<%= user.getScreenName() %>'
				}
			});
			
		var speechButton = A.one('.speech :button');
		if(speechButton) {
			speechButton.on('click', A.speechrecognition.startButton);
		}
	}
</aui:script>

<style>
  .speech button {
  /*  background-image: linear-gradient(#118ade 0,#118ade 47%,#1273c7 100%);
    background-image: -webkit-gradient(linear,50% 0,50% 100%,color-stop(0%,#118ade),color-stop(47%,#118ade),color-stop(100%,#1273c7));
    */
    background: #469aa7;
    border: 0px;
    color: #fff;
    padding: 0px;
  }
  .speech button :hover {
    background: #469aa7;
  }
  .final {
    color: black;
    padding-right: 3px; 
  }
  .interim {
    color: gray;
  }
  .speechresults {
    font-size: 12px;
    font-weight: bold;
    border: 1px solid #ddd;
    padding: 0px;
    text-align: left;
    min-height: 10px;
    /*width: 362px;*/
    height: 20px;
    float: right;
    /*margin-top: 45px;
    margin-right: 70px;*/
  }
  .speechresults-cpanel {
    font-size: 12px;
    font-weight: bold;
    border: 1px solid #ddd;
    padding: 0px;
    text-align: left;
    min-height: 10px;
    /*width: 362px;*/
    height: 20px;
    float: right;
  }
  #speechresults {
    opacity: 0;
  }
</style>