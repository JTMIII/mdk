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
package gov.nasa.jpl.mbee.mdk.docgen.validation;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.annotation.Annotation;
import com.nomagic.magicdraw.annotation.AnnotationManager;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.validation.RuleViolationResult;
import gov.nasa.jpl.mbee.mdk.ems.sync.local.LocalSyncProjectEventListenerAdapter;
import gov.nasa.jpl.mbee.mdk.ems.sync.local.LocalSyncTransactionCommitListener;
import gov.nasa.jpl.mbee.mdk.lib.Utils;

import javax.swing.*;
import java.util.Collection;
import java.util.HashSet;

public abstract class RuleViolationAction extends MDAction implements IRuleViolationAction {

    private static final long serialVersionUID = 1L;

    public RuleViolationAction(String paramString1, String paramString2,
                               KeyStroke paramKeyStroke, String paramString3) {
        super(paramString1, paramString2, paramKeyStroke, paramString3);
        // TODO Auto-generated constructor stub
    }

    protected Annotation annotation;
    private RuleViolationResult rvr;
    private ValidationWindowRun vwr;

    public Annotation getAnnotation() {
        return annotation;
    }

    @Override
    public void setAnnotation(Annotation anno) {
        annotation = anno;
    }

    @Override
    public void setRuleViolationResult(RuleViolationResult rvr) {
        this.rvr = rvr;
    }

    @Override
    public void setValidationWindowRun(ValidationWindowRun vwr) {
        this.vwr = vwr;
    }

    public void removeViolationAndUpdateWindow() {
        if (vwr == null) {
            return;
        }
        vwr.results.remove(rvr);
        AnnotationManager.getInstance().remove(annotation);
        AnnotationManager.getInstance().update();
        //ValidationResultsWindowManager.updateValidationResultsWindow(vwr.id, vwr.title, vwr.runData, vwr.results);
    }

    public void removeViolationsAndUpdateWindow(Collection<Annotation> annos) {
        for (Annotation anno : annos) {
            vwr.results.remove(vwr.mapping.get(anno));
            AnnotationManager.getInstance().remove(anno);
        }
        AnnotationManager.getInstance().update();
        //ValidationResultsWindowManager.updateValidationResultsWindow(vwr.id, vwr.title, vwr.runData, vwr.results);
    }

    public void saySuccess() {
        Application.getInstance().getGUILog().log("[INFO] Successful");
    }

    protected boolean doAction(Annotation anno) throws ReadOnlyElementException {
        return true;
    }

    protected void doAfterSuccess() {
    }

    protected void executeMany(Collection<Annotation> annos, String sessionName) {
        LocalSyncTransactionCommitListener listener = LocalSyncProjectEventListenerAdapter.getProjectMapping(Application.getInstance().getProject()).getLocalSyncTransactionCommitListener();
        if (listener != null) {
            listener.setDisabled(true);
        }
        SessionManager.getInstance().createSession(sessionName);
        Collection<Annotation> toremove = new HashSet<Annotation>();
        try {
            boolean noneditable = false;
            for (Annotation anno : annos) {
                if (doAction(anno)) {
                    toremove.add(anno);
                }
                else {
                    noneditable = true;
                }
            }
            SessionManager.getInstance().closeSession();
            if (noneditable) {
                Application.getInstance().getGUILog().log("[ERROR] There were some elements couldn't be imported");
            }
            else {
                saySuccess();
            }
            //AnnotationManager.getInstance().update();
            this.removeViolationsAndUpdateWindow(toremove);

        } catch (Exception ex) {
            SessionManager.getInstance().cancelSession();
            Utils.printException(ex);
        }
        if (listener != null) {
            listener.setDisabled(false);
        }
    }

    protected void execute(String sessionName) {
        LocalSyncTransactionCommitListener listener = LocalSyncProjectEventListenerAdapter.getProjectMapping(Application.getInstance().getProject()).getLocalSyncTransactionCommitListener();
        if (listener != null) {
            listener.setDisabled(true);
        }
        SessionManager.getInstance().createSession("Change Rel");
        try {
            if (doAction(null)) {
                SessionManager.getInstance().closeSession();
                saySuccess();
                doAfterSuccess();
                this.removeViolationAndUpdateWindow();
            }
            else {
                SessionManager.getInstance().cancelSession();
            }
            //AnnotationManager.getInstance().remove(annotation);
            //AnnotationManager.getInstance().update();
        } catch (Exception ex) {
            SessionManager.getInstance().cancelSession();
            Utils.printException(ex);
        }
        if (listener != null) {
            listener.setDisabled(false);
        }
    }
}
