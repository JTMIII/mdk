package gov.nasa.jpl.mbee.mdk.ems.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.annotation.Annotation;
import com.nomagic.magicdraw.annotation.AnnotationAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import gov.nasa.jpl.mbee.mdk.MDKPlugin;
import gov.nasa.jpl.mbee.mdk.api.incubating.MDKConstants;
import gov.nasa.jpl.mbee.mdk.docgen.validation.IRuleViolationAction;
import gov.nasa.jpl.mbee.mdk.docgen.validation.RuleViolationAction;
import gov.nasa.jpl.mbee.mdk.ems.ExportUtility;
import gov.nasa.jpl.mbee.mdk.ems.sync.queue.OutputQueue;
import gov.nasa.jpl.mbee.mdk.ems.sync.queue.Request;
import gov.nasa.jpl.mbee.mdk.json.JacksonUtils;

import javax.annotation.CheckForNull;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by igomes on 9/27/16.
 */
// TODO Abstract this and update to a common class @donbot
public class CommitClientElementAction extends RuleViolationAction implements AnnotationAction, IRuleViolationAction {
    private static final String NAME = "Commit Element to MMS";

    private final String id;
    private final Element element;
    private final ObjectNode elementObjectNode;
    private final Project project;

    public CommitClientElementAction(String id, Element element, ObjectNode elementObjectNode, Project project) {
        super(NAME, NAME, null, null);
        this.id = id;
        this.element = element;
        this.elementObjectNode = elementObjectNode;
        this.project = project;
    }

    @Override
    public void execute(Collection<Annotation> annotations) {
        List<ObjectNode> elementsToUpdate = new ArrayList<>(annotations.size());
        List<String> elementsToDelete = new ArrayList<>(annotations.size());

        for (Annotation annotation : annotations) {
            for (NMAction action : annotation.getActions()) {
                if (action instanceof CommitClientElementAction) {
                    ObjectNode elementObjectNode = ((CommitClientElementAction) action).getElementObjectNode();
                    if (elementObjectNode != null) {
                        elementsToUpdate.add(elementObjectNode);
                    }
                    else {
                        elementsToDelete.add(id);
                    }
                    break;
                }
            }
        }
        try {
            CommitClientElementAction.request(elementsToUpdate, elementsToDelete, project);
        } catch (JsonProcessingException e) {
            Application.getInstance().getGUILog().log("[ERROR]: Exception occurred committing element to server. Commit aborted.");
            e.printStackTrace();
        }
    }

    @Override
    public boolean canExecute(Collection<Annotation> annotations) {
        return true;
    }

    public Element getElement() {
        return element;
    }

    public ObjectNode getElementObjectNode() {
        return elementObjectNode;
    }

    @Override
    public void actionPerformed(@CheckForNull ActionEvent actionEvent) {
        List<ObjectNode> elementsToUpdate = new ArrayList<>(1);
        List<String> elementsToDelete = new ArrayList<>(1);
        if (elementObjectNode != null) {
            elementsToUpdate.add(elementObjectNode);
        }
        else {
            elementsToDelete.add(id);
        }
        try {
            CommitClientElementAction.request(elementsToUpdate, elementsToDelete, project);
        } catch (JsonProcessingException e) {
            Application.getInstance().getGUILog().log("[ERROR]: Exception occurred committing element to server. Commit aborted.");
            e.printStackTrace();
        }
    }

    private static void request(List<ObjectNode> elementsToUpdate, List<String> elementsToDelete, Project project) throws JsonProcessingException {
        if (elementsToUpdate != null && !elementsToUpdate.isEmpty()) {
            Application.getInstance().getGUILog().log("[INFO] Queueing request to create/update " + elementsToUpdate.size() + " element" + (elementsToUpdate.size() != 1 ? "s" : "") + " on MMS.");
            ObjectNode request = JacksonUtils.getObjectMapper().createObjectNode();
            ArrayNode elements = request.putArray("elements");
            elements.addAll(elementsToUpdate);
            request.put("source", "magicdraw");
            request.put("mmsVersion", MDKPlugin.VERSION);
            OutputQueue.getInstance().offer((new Request(ExportUtility.getPostElementsUrl(), JacksonUtils.getObjectMapper().writeValueAsString(request), "POST", true, elements.size(), "Sync Changes")));
        }
        if (elementsToDelete != null && !elementsToDelete.isEmpty()) {
            Application.getInstance().getGUILog().log("[INFO] Queueing request to delete " + elementsToDelete.size() + " element" + (elementsToDelete.size() != 1 ? "s" : "") + " on MMS.");
            ObjectNode request = JacksonUtils.getObjectMapper().createObjectNode();
            ArrayNode elements = request.putArray("elements");
            for (String id : elementsToDelete) {
                ObjectNode curElement = JacksonUtils.getObjectMapper().createObjectNode();
                curElement.put(MDKConstants.SYSML_ID_KEY, id);
                elements.add(curElement);
            }
            request.put("source", "magicdraw");
            request.put("mmsVersion", MDKPlugin.VERSION);
            OutputQueue.getInstance().offer(new Request(ExportUtility.getUrlWithWorkspace() + "/elements", JacksonUtils.getObjectMapper().writeValueAsString(request), "DELETEALL", true, elements.size(), "Sync Deletes"));
        }
    }
}