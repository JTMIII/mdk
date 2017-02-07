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
package gov.nasa.jpl.mbee.mdk.ems.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nomagic.magicdraw.annotation.Annotation;
import com.nomagic.magicdraw.annotation.AnnotationAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import gov.nasa.jpl.mbee.mdk.MDKPlugin;
import gov.nasa.jpl.mbee.mdk.api.incubating.MDKConstants;
import gov.nasa.jpl.mbee.mdk.docgen.validation.IRuleViolationAction;
import gov.nasa.jpl.mbee.mdk.docgen.validation.RuleViolationAction;
import gov.nasa.jpl.mbee.mdk.ems.MMSUtils;
import gov.nasa.jpl.mbee.mdk.ems.ServerException;
import gov.nasa.jpl.mbee.mdk.json.JacksonUtils;
import gov.nasa.jpl.mbee.mdk.lib.MDUtils;
import org.apache.http.client.utils.URIBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

public class CommitOrgAction extends RuleViolationAction implements AnnotationAction, IRuleViolationAction {

    public static final String DEFAULT_ID = CommitOrgAction.class.getSimpleName();
    public static final String COMMIT_MODEL_DEFAULT_ID = DEFAULT_ID + "_Commit_Org";
    private final Project project;

    public CommitOrgAction(Project project) {
        this(project, false);
    }

    public CommitOrgAction(Project project, boolean isDeveloperAction) {
        super(DEFAULT_ID, "Commit Org" + (isDeveloperAction ? " [DEVELOPER]" : ""), null, null);
        this.project = project;
    }

    @Override
    public boolean canExecute(Collection<Annotation> arg0) {
        return false;
    }


    @Override
    public void execute(Collection<Annotation> annos) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commitAction();
    }

    public String commitAction() {
        if (!MDUtils.isDeveloperMode()) {
            return null;
        }

        // check for existing org
        URIBuilder requestUri = MMSUtils.getServiceOrgsUri(project);
        if (requestUri == null) {
            return null;
        }

        JFrame selectionDialog = new JFrame();
        String org = JOptionPane.showInputDialog(selectionDialog, "[DEVELOPER] Input MMS org below");
        if (org == null || org.isEmpty()) {
            Application.getInstance().getGUILog().log("[ERROR] Unable to commit org without name.");
            return null;
        }

        ObjectNode response;
        try {
            response = MMSUtils.sendMMSRequest(MMSUtils.buildRequest(MMSUtils.HttpRequestType.GET, requestUri));
            JsonNode arrayNode;
            if ((arrayNode = response.get("elements")) != null && arrayNode.isArray()) {
                for (JsonNode orgNode : arrayNode) {
                    JsonNode value;
                    if ((value = orgNode.get("orgId")) != null && value.isTextual()) {
                        if (value.asText() == org) {
                            Application.getInstance().getGUILog().log("[WARNING] Org already exists. Creation will not proceed.");
                            return org;
                        }
                    }
                }
            }
        } catch (IOException | URISyntaxException | ServerException e1) {
            Application.getInstance().getGUILog().log("[ERROR] Unable to query MMS orgs. Attempting to create without checking.");
            e1.printStackTrace();
        }

        // build post data
        ObjectNode requestData = JacksonUtils.getObjectMapper().createObjectNode();
        ArrayNode elementsArrayNode = requestData.putArray("elements");
        requestData.put("source", "magicdraw");
        requestData.put("mdkVersion", MDKPlugin.VERSION);
        ObjectNode orgObjectNode = JacksonUtils.getObjectMapper().createObjectNode();
        orgObjectNode.put(MDKConstants.ORG_ID_KEY, org);
        orgObjectNode.put(MDKConstants.NAME_KEY, org);
        elementsArrayNode.add(orgObjectNode);

        // do post request
        response = null;
        try {
            response = MMSUtils.sendMMSRequest(MMSUtils.buildRequest(MMSUtils.HttpRequestType.POST, requestUri, requestData));
        } catch (IOException | URISyntaxException | ServerException e1) {
            Application.getInstance().getGUILog().log("[ERROR] Unexpected error while committing org. Reason: " + e1.getMessage());
            e1.printStackTrace();
        }
        JsonNode value;
        if (response == null || (((value = response.get("message" )) != null) && value.isTextual()
                && value.asText().equals("Site was not created"))) {
            return null;
        }
        return org;
    }
}
