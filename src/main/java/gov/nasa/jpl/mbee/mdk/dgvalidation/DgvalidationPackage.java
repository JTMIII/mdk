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
package gov.nasa.jpl.mbee.mdk.dgvalidation;

import org.eclipse.emf.ecore.*;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @model kind="package"
 * @generated
 * @see gov.nasa.jpl.mbee.mdk.dgvalidation.DgvalidationFactory
 */
public interface DgvalidationPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "dgvalidation";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://mbee.jpl.nasa.gov/docgen/dgvalidation";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "gov.nasa.jpl.mbee.mdk.dgvalidation";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    DgvalidationPackage eINSTANCE = gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl
            .init();

    /**
     * The meta object id for the '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.impl.RuleImpl
     * <em>Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.RuleImpl
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getRule()
     */
    int RULE = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RULE__NAME = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RULE__DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RULE__SEVERITY = 2;

    /**
     * The feature id for the '<em><b>Violations</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RULE__VIOLATIONS = 3;

    /**
     * The number of structural features of the '<em>Rule</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RULE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.impl.ViolationImpl
     * <em>Violation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.ViolationImpl
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getViolation()
     */
    int VIOLATION = 1;

    /**
     * The feature id for the '<em><b>Element Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIOLATION__ELEMENT_ID = 0;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIOLATION__COMMENT = 1;

    /**
     * The number of structural features of the '<em>Violation</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIOLATION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.impl.SuiteImpl
     * <em>Suite</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.SuiteImpl
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getSuite()
     */
    int SUITE = 2;

    /**
     * The feature id for the '<em><b>Show Detail</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SUITE__SHOW_DETAIL = 0;

    /**
     * The feature id for the '<em><b>Show Summary</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SUITE__SHOW_SUMMARY = 1;

    /**
     * The feature id for the '<em><b>Own Section</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SUITE__OWN_SECTION = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SUITE__NAME = 3;

    /**
     * The feature id for the '<em><b>Rules</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SUITE__RULES = 4;

    /**
     * The number of structural features of the '<em>Suite</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SUITE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Severity
     * <em>Severity</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Severity
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getSeverity()
     */
    int SEVERITY = 3;

    /**
     * Returns the meta object for class '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Rule <em>Rule</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Rule</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Rule
     */
    EClass getRule();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getName()
     * @see #getRule()
     */
    EAttribute getRule_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getDescription()
     * @see #getRule()
     */
    EAttribute getRule_Description();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getSeverity
     * <em>Severity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Severity</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getSeverity()
     * @see #getRule()
     */
    EAttribute getRule_Severity();

    /**
     * Returns the meta object for the containment reference list '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getViolations
     * <em>Violations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     * <em>Violations</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Rule#getViolations()
     * @see #getRule()
     */
    EReference getRule_Violations();

    /**
     * Returns the meta object for class '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Violation
     * <em>Violation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Violation</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Violation
     */
    EClass getViolation();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Violation#getElementId
     * <em>Element Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Element Id</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Violation#getElementId()
     * @see #getViolation()
     */
    EAttribute getViolation_ElementId();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Violation#getComment
     * <em>Comment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Comment</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Violation#getComment()
     * @see #getViolation()
     */
    EAttribute getViolation_Comment();

    /**
     * Returns the meta object for class '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Suite <em>Suite</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Suite</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Suite
     */
    EClass getSuite();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#isShowDetail
     * <em>Show Detail</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Show Detail</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#isShowDetail()
     * @see #getSuite()
     */
    EAttribute getSuite_ShowDetail();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#isShowSummary
     * <em>Show Summary</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Show Summary</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#isShowSummary()
     * @see #getSuite()
     */
    EAttribute getSuite_ShowSummary();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#isOwnSection
     * <em>Own Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Own Section</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#isOwnSection()
     * @see #getSuite()
     */
    EAttribute getSuite_OwnSection();

    /**
     * Returns the meta object for the attribute '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#getName()
     * @see #getSuite()
     */
    EAttribute getSuite_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#getRules
     * <em>Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     * <em>Rules</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Suite#getRules()
     * @see #getSuite()
     */
    EReference getSuite_Rules();

    /**
     * Returns the meta object for enum '
     * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Severity
     * <em>Severity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Severity</em>'.
     * @generated
     * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Severity
     */
    EEnum getSeverity();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DgvalidationFactory getDgvalidationFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.impl.RuleImpl
         * <em>Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.RuleImpl
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getRule()
         */
        EClass RULE = eINSTANCE.getRule();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RULE__NAME = eINSTANCE.getRule_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RULE__DESCRIPTION = eINSTANCE.getRule_Description();

        /**
         * The meta object literal for the '<em><b>Severity</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RULE__SEVERITY = eINSTANCE.getRule_Severity();

        /**
         * The meta object literal for the '<em><b>Violations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference RULE__VIOLATIONS = eINSTANCE.getRule_Violations();

        /**
         * The meta object literal for the '
         * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.impl.ViolationImpl
         * <em>Violation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @generated
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.ViolationImpl
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getViolation()
         */
        EClass VIOLATION = eINSTANCE.getViolation();

        /**
         * The meta object literal for the '<em><b>Element Id</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute VIOLATION__ELEMENT_ID = eINSTANCE.getViolation_ElementId();

        /**
         * The meta object literal for the '<em><b>Comment</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute VIOLATION__COMMENT = eINSTANCE.getViolation_Comment();

        /**
         * The meta object literal for the '
         * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.impl.SuiteImpl
         * <em>Suite</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.SuiteImpl
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getSuite()
         */
        EClass SUITE = eINSTANCE.getSuite();

        /**
         * The meta object literal for the '<em><b>Show Detail</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SUITE__SHOW_DETAIL = eINSTANCE.getSuite_ShowDetail();

        /**
         * The meta object literal for the '<em><b>Show Summary</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SUITE__SHOW_SUMMARY = eINSTANCE.getSuite_ShowSummary();

        /**
         * The meta object literal for the '<em><b>Own Section</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SUITE__OWN_SECTION = eINSTANCE.getSuite_OwnSection();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SUITE__NAME = eINSTANCE.getSuite_Name();

        /**
         * The meta object literal for the '<em><b>Rules</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SUITE__RULES = eINSTANCE.getSuite_Rules();

        /**
         * The meta object literal for the '
         * {@link gov.nasa.jpl.mbee.mdk.dgvalidation.Severity
         * <em>Severity</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @generated
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.Severity
         * @see gov.nasa.jpl.mbee.mdk.dgvalidation.impl.DgvalidationPackageImpl#getSeverity()
         */
        EEnum SEVERITY = eINSTANCE.getSeverity();

    }

} // DgvalidationPackage
