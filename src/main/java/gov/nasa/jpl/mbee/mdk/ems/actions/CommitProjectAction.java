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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nomagic.magicdraw.annotation.Annotation;
import com.nomagic.magicdraw.annotation.AnnotationAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.task.RunnableWithProgress;
import com.nomagic.ui.ProgressStatusRunner;
import gov.nasa.jpl.mbee.mdk.MDKPlugin;
import gov.nasa.jpl.mbee.mdk.docgen.validation.IRuleViolationAction;
import gov.nasa.jpl.mbee.mdk.docgen.validation.RuleViolationAction;
import gov.nasa.jpl.mbee.mdk.ems.MMSUtils;
import gov.nasa.jpl.mbee.mdk.ems.ManualSyncActionRunner;
import gov.nasa.jpl.mbee.mdk.ems.ServerException;
import gov.nasa.jpl.mbee.mdk.json.JacksonUtils;
import org.apache.http.client.utils.URIBuilder;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;

public class CommitProjectAction extends RuleViolationAction implements AnnotationAction, IRuleViolationAction {

    public static final String DEFAULT_ID = CommitProjectAction.class.getSimpleName();
    public static final String COMMIT_MODEL_DEFAULT_ID = DEFAULT_ID + "_Commit_Model";

    private final Project project;
    private final boolean shouldCommitModel;

    public CommitProjectAction(Project project) {
        this(project, false, false);
    }

    public CommitProjectAction(Project project, boolean shouldCommitModel) {
        this(project, shouldCommitModel, false);
    }

    public CommitProjectAction(Project project, boolean shouldCommitModel, boolean isDeveloperAction) {
        super(shouldCommitModel ? COMMIT_MODEL_DEFAULT_ID : DEFAULT_ID, "Commit Project" + (shouldCommitModel ? " and Model" : "") + (isDeveloperAction ? " [DEVELOPER]" : ""), null, null);
        this.project = project;
        this.shouldCommitModel = shouldCommitModel;
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
        ObjectNode requestData = JacksonUtils.getObjectMapper().createObjectNode();
        ArrayNode elementsArrayNode = requestData.putArray("elements");
        requestData.put("source", "magicdraw");
        requestData.put("mdkVersion", MDKPlugin.VERSION);

        ObjectNode projectObjectNode = MMSUtils.getProjectObjectNode(project);
        elementsArrayNode.add(projectObjectNode);
        URIBuilder requestUri = MMSUtils.getServiceWorkspacesSitesUri(project);
        if (requestUri == null) {
            return;
        }
        requestUri.setPath(requestUri.getPath() + "/projects");

        ObjectNode response = null;
        try {
            response = MMSUtils.sendMMSRequest(MMSUtils.buildRequest(MMSUtils.HttpRequestType.POST, requestUri, requestData));
        } catch (IOException | URISyntaxException | ServerException e1) {
            Application.getInstance().getGUILog().log("[ERROR] Unexpected error while checking if project was initialized. Reason: " + e1.getMessage());
            e1.printStackTrace();
        }
        if (response == null) {
            return;
        }
        if (shouldCommitModel) {
            requestUri = MMSUtils.getServiceWorkspacesSitesElementsUri(project);
            if (requestUri == null) {
                return;
            }
            RunnableWithProgress temp = new ManualSyncActionRunner<>(CommitClientElementAction.class, Collections.singletonList(project.getPrimaryModel()), project, true, -1);
            ProgressStatusRunner.runWithProgressStatus(temp, "Model Initialization", true, 0);
        }
    }
}

