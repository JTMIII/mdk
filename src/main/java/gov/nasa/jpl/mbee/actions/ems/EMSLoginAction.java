/*******************************************************************************
 * Copyright (c) <2013>, California Institute of Technology ("Caltech").  
 * U.S. Government sponsorship acknowledged.
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 *  - Redistributions of source code must retain the above copyright notice, this list of 
 *    conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, this list 
 *    of conditions and the following disclaimer in the documentation and/or other materials 
 *    provided with the distribution.
 *  - Neither the name of Caltech nor its operating division, the Jet Propulsion Laboratory, 
 *    nor the names of its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS 
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER  
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package gov.nasa.jpl.mbee.actions.ems;

import com.nomagic.magicdraw.core.Project;
import gov.nasa.jpl.mbee.ems.ExportUtility;
import gov.nasa.jpl.mbee.ems.ServerException;
import gov.nasa.jpl.mbee.ems.jms.JMSUtils;
import gov.nasa.jpl.mbee.ems.sync.delta.DeltaSyncProjectEventListenerAdapter;
import gov.nasa.jpl.mbee.ems.sync.realtime.RealTimeSyncProjectEventListenerAdapter;
import gov.nasa.jpl.mbee.lib.Utils;
import gov.nasa.jpl.mbee.viewedit.ViewEditUtils;

import java.awt.event.ActionEvent;

import com.nomagic.magicdraw.actions.ActionsStateUpdater;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;

public class EMSLoginAction extends MDAction {                       
    private static final long serialVersionUID = 1L;
    public static final String actionid = "Login";

    private EMSLogoutAction logout;
    
    public EMSLoginAction() {
        super(actionid, "Login to MMS", null, null);
    }

    public void setLogoutAction(EMSLogoutAction logout) {
        this.logout = logout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // passing in "" as the username will trigger the login dialogue popup
    	loginAction("", "");
    	ActionsStateUpdater.updateActionsState();
    }
    
    public boolean loginAction(String username, String password)
    {
        return loginAction(username, password, true);
    }
    
    public static boolean loginAction(String username, String password, boolean initJms) {
        Project project = Application.getInstance().getProject();
        ViewEditUtils.clearUsernameAndPassword();
        if (project == null) {
            Utils.showPopupMessage("You need to have a project open first!");
            return false;
        }
        String url = ExportUtility.getUrl(project);
        if (url == null)
            return false;
        String response = null;
        try {
            response = ExportUtility.getTicket(url + "/api/login", username, password, true); //used to be /checklogin
        } catch (ServerException ex) {
            ViewEditUtils.clearUsernameAndPassword();
        }
        if (response == null) {
            ViewEditUtils.clearUsernameAndPassword();
            return false;
        }
        if (initJms && !RealTimeSyncProjectEventListenerAdapter.getProjectMapping(project).isDisabled()) {
            Application.getInstance().getGUILog().log("Initializing MMS message queue...");
            if (JMSUtils.initializeJms(Application.getInstance().getProject()))
                Application.getInstance().getGUILog().log("MMS message queue initialized.");
        }
        Application.getInstance().getGUILog().log("Login complete.");
        return true;
    }

}
