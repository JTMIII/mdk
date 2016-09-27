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
package gov.nasa.jpl.mbee.mdk.actions.ems;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.ui.ProgressStatusRunner;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import gov.nasa.jpl.mbee.mdk.ems.ExportUtility;
import gov.nasa.jpl.mbee.mdk.ems.ModelExportRunner;
import gov.nasa.jpl.mbee.mdk.lib.Utils;

import java.awt.event.ActionEvent;

public class ExportModelAction extends MDAction {

    private static final long serialVersionUID = 1L;

    private Element start;

    public static final String actionid = "ExportModel";

    public ExportModelAction(Element e) {
        //JJS--MDEV-567 fix: changed 'Import' to 'Accept'
        //
        super(actionid, "(Commit Model)", null, null);
        start = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JJS--MDEV-567 fix: changed 'Export' to 'Commit'
        //
        Boolean packageOnly = false;//Utils.getUserYesNoAnswer("Commit package structure only?");
        if (packageOnly == null) {
            return;
        }
        String depths = "0";//(String)JOptionPane.showInputDialog("Max Depth? 0 is infinite");
        if (depths == null) {
            return;
        }
        int depth = 0;
        try {
            depth = Integer.parseInt(depths);
        } catch (Exception ex) {
            return;
        }
        String url = ExportUtility.getPostElementsUrl();
        if (url == null) {
            return;
        }
        String[] buttons = {"Background job on server", "Background job on magicdraw", "Abort Export"};
        Boolean background = Utils.getUserYesNoAnswerWithButton("Use background export on server? You'll get an email when done.", buttons, true);
        if (background == null) {
            return;
        }
        ProgressStatusRunner.runWithProgressStatus(new ModelExportRunner(start, depth, packageOnly, url, background), "Exporting Model", true, 0);
        /*
        if (start instanceof Model) {
            me = new ModelExporter(Application.getInstance().getProject(), depth, packageOnly);
        } else {
            List<Element> root = new ArrayList<Element>();
            root.add(start);
            me = new ModelExporter(root, depth, packageOnly);
        }
        JSONObject result = me.getResult();
        String json = result.toJSONString();

        gl.log(json);
        gl.log("Number of Elements: " + me.getNumberOfElements());
        String url = ViewEditUtils.getUrl(false);
        if (url == null) {
            return;
        }
        url += "/javawebscripts/sites/europa/projects/" + Application.getInstance().getProject().getPrimaryProject().getProjectID() + "/model";
       // gl.log("*** Starting export view comments ***");
        ExportUtility.send(url, json);
        */
    }

}
