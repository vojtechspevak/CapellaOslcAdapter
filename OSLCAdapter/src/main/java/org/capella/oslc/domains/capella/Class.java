package org.capella.oslc.domains.capella;

import java.util.ArrayList;
import java.util.List;

public class Class extends EObjectMock {
	private boolean IS_PRIMITIVE_EDEFAULT;
	private boolean isPrimitive;
	private List<IdentifiableReferenceObject> keyParts = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedStateMachines = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedDataValues = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedInformationRealizations = new ArrayList<IdentifiableReferenceObject>();
	private boolean FINAL_EDEFAULT;
	private boolean final_;
	private IdentifiableReferenceObject VISIBILITY_EDEFAULT;
	private IdentifiableReferenceObject visibility;
	private List<IdentifiableReferenceObject> nestedGeneralClasses = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedFeatures = new ArrayList<IdentifiableReferenceObject>();
	private boolean ABSTRACT_EDEFAULT;
	private boolean abstract_;
	private List<IdentifiableReferenceObject> ownedGeneralizations = new ArrayList<IdentifiableReferenceObject>();
	private boolean VISIBLE_IN_DOC_EDEFAULT;
	private boolean visibleInDoc;
	private boolean VISIBLE_IN_LM_EDEFAULT;
	private boolean visibleInLM;
	private java.lang.String SUMMARY_EDEFAULT;
	private java.lang.String summary;
	private java.lang.String DESCRIPTION_EDEFAULT;
	private java.lang.String description;
	private java.lang.String REVIEW_EDEFAULT;
	private java.lang.String review;
	private List<IdentifiableReferenceObject> ownedPropertyValues = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedEnumerationPropertyTypes = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> appliedPropertyValues = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedPropertyValueGroups = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> appliedPropertyValueGroups = new ArrayList<IdentifiableReferenceObject>();
	private IdentifiableReferenceObject status;
	private List<IdentifiableReferenceObject> features = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedTraces = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> namingRules = new ArrayList<IdentifiableReferenceObject>();
	private java.lang.String NAME_EDEFAULT;
	private java.lang.String name;
	private java.lang.String ID_EDEFAULT;
	private java.lang.String id;
	private java.lang.String SID_EDEFAULT;
	private java.lang.String sid;
	private List<IdentifiableReferenceObject> ownedConstraints = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedMigratedElements = new ArrayList<IdentifiableReferenceObject>();
	private List<IdentifiableReferenceObject> ownedExtensions = new ArrayList<IdentifiableReferenceObject>();
	private int EDELIVER;
	private int EDYNAMIC_CLASS;
	private int EPROXY;
	private int ELAST_NOTIFIER_FLAG;
	private int ELAST_EOBJECT_FLAG;
	private int eFlags;
	private int eContainerFeatureID;
	private java.lang.String ESCAPE;
	private int EVIRTUAL_SET;
	private int EVIRTUAL_UNSET;
	private int EVIRTUAL_GET;
	private int EVIRTUAL_IS_SET;
	private java.lang.Object EVIRTUAL_NO_VALUE;
	private boolean $assertionsDisabled;

	public boolean isIS_PRIMITIVE_EDEFAULT() {
		return IS_PRIMITIVE_EDEFAULT;
	}

	public void setIS_PRIMITIVE_EDEFAULT(boolean iS_PRIMITIVE_EDEFAULT) {
		IS_PRIMITIVE_EDEFAULT = iS_PRIMITIVE_EDEFAULT;
	}

	public boolean isPrimitive() {
		return isPrimitive;
	}

	public void setPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
	}

	public List<IdentifiableReferenceObject> getKeyParts() {
		return keyParts;
	}

	public void setKeyParts(List<IdentifiableReferenceObject> keyParts) {
		this.keyParts = keyParts;
	}

	public List<IdentifiableReferenceObject> getOwnedStateMachines() {
		return ownedStateMachines;
	}

	public void setOwnedStateMachines(List<IdentifiableReferenceObject> ownedStateMachines) {
		this.ownedStateMachines = ownedStateMachines;
	}

	public List<IdentifiableReferenceObject> getOwnedDataValues() {
		return ownedDataValues;
	}

	public void setOwnedDataValues(List<IdentifiableReferenceObject> ownedDataValues) {
		this.ownedDataValues = ownedDataValues;
	}

	public List<IdentifiableReferenceObject> getOwnedInformationRealizations() {
		return ownedInformationRealizations;
	}

	public void setOwnedInformationRealizations(List<IdentifiableReferenceObject> ownedInformationRealizations) {
		this.ownedInformationRealizations = ownedInformationRealizations;
	}

	public boolean isFINAL_EDEFAULT() {
		return FINAL_EDEFAULT;
	}

	public void setFINAL_EDEFAULT(boolean fINAL_EDEFAULT) {
		FINAL_EDEFAULT = fINAL_EDEFAULT;
	}

	public boolean isFinal_() {
		return final_;
	}

	public void setFinal_(boolean final_) {
		this.final_ = final_;
	}

	public IdentifiableReferenceObject getVISIBILITY_EDEFAULT() {
		return VISIBILITY_EDEFAULT;
	}

	public void setVISIBILITY_EDEFAULT(IdentifiableReferenceObject vISIBILITY_EDEFAULT) {
		VISIBILITY_EDEFAULT = vISIBILITY_EDEFAULT;
	}

	public IdentifiableReferenceObject getVisibility() {
		return visibility;
	}

	public void setVisibility(IdentifiableReferenceObject visibility) {
		this.visibility = visibility;
	}

	public List<IdentifiableReferenceObject> getNestedGeneralClasses() {
		return nestedGeneralClasses;
	}

	public void setNestedGeneralClasses(List<IdentifiableReferenceObject> nestedGeneralClasses) {
		this.nestedGeneralClasses = nestedGeneralClasses;
	}

	public List<IdentifiableReferenceObject> getOwnedFeatures() {
		return ownedFeatures;
	}

	public void setOwnedFeatures(List<IdentifiableReferenceObject> ownedFeatures) {
		this.ownedFeatures = ownedFeatures;
	}

	public boolean isABSTRACT_EDEFAULT() {
		return ABSTRACT_EDEFAULT;
	}

	public void setABSTRACT_EDEFAULT(boolean aBSTRACT_EDEFAULT) {
		ABSTRACT_EDEFAULT = aBSTRACT_EDEFAULT;
	}

	public boolean isAbstract() {
		return abstract_;
	}

	public void setAbstract(boolean abstract_) {
		this.abstract_ = abstract_;
	}

	public void setAbstract_(boolean abstract_) {
		this.abstract_ = abstract_;
	}

	public List<IdentifiableReferenceObject> getOwnedGeneralizations() {
		return ownedGeneralizations;
	}

	public void setOwnedGeneralizations(List<IdentifiableReferenceObject> ownedGeneralizations) {
		this.ownedGeneralizations = ownedGeneralizations;
	}

	public boolean isVISIBLE_IN_DOC_EDEFAULT() {
		return VISIBLE_IN_DOC_EDEFAULT;
	}

	public void setVISIBLE_IN_DOC_EDEFAULT(boolean vISIBLE_IN_DOC_EDEFAULT) {
		VISIBLE_IN_DOC_EDEFAULT = vISIBLE_IN_DOC_EDEFAULT;
	}

	public boolean isVisibleInDoc() {
		return visibleInDoc;
	}

	public void setVisibleInDoc(boolean visibleInDoc) {
		this.visibleInDoc = visibleInDoc;
	}

	public boolean isVISIBLE_IN_LM_EDEFAULT() {
		return VISIBLE_IN_LM_EDEFAULT;
	}

	public void setVISIBLE_IN_LM_EDEFAULT(boolean vISIBLE_IN_LM_EDEFAULT) {
		VISIBLE_IN_LM_EDEFAULT = vISIBLE_IN_LM_EDEFAULT;
	}

	public boolean isVisibleInLM() {
		return visibleInLM;
	}

	public void setVisibleInLM(boolean visibleInLM) {
		this.visibleInLM = visibleInLM;
	}

	public java.lang.String getSUMMARY_EDEFAULT() {
		return SUMMARY_EDEFAULT;
	}

	public void setSUMMARY_EDEFAULT(java.lang.String sUMMARY_EDEFAULT) {
		SUMMARY_EDEFAULT = sUMMARY_EDEFAULT;
	}

	public java.lang.String getSummary() {
		return summary;
	}

	public void setSummary(java.lang.String summary) {
		this.summary = summary;
	}

	public java.lang.String getDESCRIPTION_EDEFAULT() {
		return DESCRIPTION_EDEFAULT;
	}

	public void setDESCRIPTION_EDEFAULT(java.lang.String dESCRIPTION_EDEFAULT) {
		DESCRIPTION_EDEFAULT = dESCRIPTION_EDEFAULT;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getREVIEW_EDEFAULT() {
		return REVIEW_EDEFAULT;
	}

	public void setREVIEW_EDEFAULT(java.lang.String rEVIEW_EDEFAULT) {
		REVIEW_EDEFAULT = rEVIEW_EDEFAULT;
	}

	public java.lang.String getReview() {
		return review;
	}

	public void setReview(java.lang.String review) {
		this.review = review;
	}

	public List<IdentifiableReferenceObject> getOwnedPropertyValues() {
		return ownedPropertyValues;
	}

	public void setOwnedPropertyValues(List<IdentifiableReferenceObject> ownedPropertyValues) {
		this.ownedPropertyValues = ownedPropertyValues;
	}

	public List<IdentifiableReferenceObject> getOwnedEnumerationPropertyTypes() {
		return ownedEnumerationPropertyTypes;
	}

	public void setOwnedEnumerationPropertyTypes(List<IdentifiableReferenceObject> ownedEnumerationPropertyTypes) {
		this.ownedEnumerationPropertyTypes = ownedEnumerationPropertyTypes;
	}

	public List<IdentifiableReferenceObject> getAppliedPropertyValues() {
		return appliedPropertyValues;
	}

	public void setAppliedPropertyValues(List<IdentifiableReferenceObject> appliedPropertyValues) {
		this.appliedPropertyValues = appliedPropertyValues;
	}

	public List<IdentifiableReferenceObject> getOwnedPropertyValueGroups() {
		return ownedPropertyValueGroups;
	}

	public void setOwnedPropertyValueGroups(List<IdentifiableReferenceObject> ownedPropertyValueGroups) {
		this.ownedPropertyValueGroups = ownedPropertyValueGroups;
	}

	public List<IdentifiableReferenceObject> getAppliedPropertyValueGroups() {
		return appliedPropertyValueGroups;
	}

	public void setAppliedPropertyValueGroups(List<IdentifiableReferenceObject> appliedPropertyValueGroups) {
		this.appliedPropertyValueGroups = appliedPropertyValueGroups;
	}

	public IdentifiableReferenceObject getStatus() {
		return status;
	}

	public void setStatus(IdentifiableReferenceObject status) {
		this.status = status;
	}

	public List<IdentifiableReferenceObject> getFeatures() {
		return features;
	}

	public void setFeatures(List<IdentifiableReferenceObject> features) {
		this.features = features;
	}

	public List<IdentifiableReferenceObject> getOwnedTraces() {
		return ownedTraces;
	}

	public void setOwnedTraces(List<IdentifiableReferenceObject> ownedTraces) {
		this.ownedTraces = ownedTraces;
	}

	public List<IdentifiableReferenceObject> getNamingRules() {
		return namingRules;
	}

	public void setNamingRules(List<IdentifiableReferenceObject> namingRules) {
		this.namingRules = namingRules;
	}

	public java.lang.String getNAME_EDEFAULT() {
		return NAME_EDEFAULT;
	}

	public void setNAME_EDEFAULT(java.lang.String nAME_EDEFAULT) {
		NAME_EDEFAULT = nAME_EDEFAULT;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getID_EDEFAULT() {
		return ID_EDEFAULT;
	}

	public void setID_EDEFAULT(java.lang.String iD_EDEFAULT) {
		ID_EDEFAULT = iD_EDEFAULT;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getSID_EDEFAULT() {
		return SID_EDEFAULT;
	}

	public void setSID_EDEFAULT(java.lang.String sID_EDEFAULT) {
		SID_EDEFAULT = sID_EDEFAULT;
	}

	public java.lang.String getSid() {
		return sid;
	}

	public void setSid(java.lang.String sid) {
		this.sid = sid;
	}

	public List<IdentifiableReferenceObject> getOwnedConstraints() {
		return ownedConstraints;
	}

	public void setOwnedConstraints(List<IdentifiableReferenceObject> ownedConstraints) {
		this.ownedConstraints = ownedConstraints;
	}

	public List<IdentifiableReferenceObject> getOwnedMigratedElements() {
		return ownedMigratedElements;
	}

	public void setOwnedMigratedElements(List<IdentifiableReferenceObject> ownedMigratedElements) {
		this.ownedMigratedElements = ownedMigratedElements;
	}

	public List<IdentifiableReferenceObject> getOwnedExtensions() {
		return ownedExtensions;
	}

	public void setOwnedExtensions(List<IdentifiableReferenceObject> ownedExtensions) {
		this.ownedExtensions = ownedExtensions;
	}

	public int getEDELIVER() {
		return EDELIVER;
	}

	public void setEDELIVER(int eDELIVER) {
		EDELIVER = eDELIVER;
	}

	public int getEDYNAMIC_CLASS() {
		return EDYNAMIC_CLASS;
	}

	public void setEDYNAMIC_CLASS(int eDYNAMIC_CLASS) {
		EDYNAMIC_CLASS = eDYNAMIC_CLASS;
	}

	public int getEPROXY() {
		return EPROXY;
	}

	public void setEPROXY(int ePROXY) {
		EPROXY = ePROXY;
	}

	public int getELAST_NOTIFIER_FLAG() {
		return ELAST_NOTIFIER_FLAG;
	}

	public void setELAST_NOTIFIER_FLAG(int eLAST_NOTIFIER_FLAG) {
		ELAST_NOTIFIER_FLAG = eLAST_NOTIFIER_FLAG;
	}

	public int getELAST_EOBJECT_FLAG() {
		return ELAST_EOBJECT_FLAG;
	}

	public void setELAST_EOBJECT_FLAG(int eLAST_EOBJECT_FLAG) {
		ELAST_EOBJECT_FLAG = eLAST_EOBJECT_FLAG;
	}

	public int geteFlags() {
		return eFlags;
	}

	public void seteFlags(int eFlags) {
		this.eFlags = eFlags;
	}

	public int geteContainerFeatureID() {
		return eContainerFeatureID;
	}

	public void seteContainerFeatureID(int eContainerFeatureID) {
		this.eContainerFeatureID = eContainerFeatureID;
	}

	public java.lang.String getESCAPE() {
		return ESCAPE;
	}

	public void setESCAPE(java.lang.String eSCAPE) {
		ESCAPE = eSCAPE;
	}

	public int getEVIRTUAL_SET() {
		return EVIRTUAL_SET;
	}

	public void setEVIRTUAL_SET(int eVIRTUAL_SET) {
		EVIRTUAL_SET = eVIRTUAL_SET;
	}

	public int getEVIRTUAL_UNSET() {
		return EVIRTUAL_UNSET;
	}

	public void setEVIRTUAL_UNSET(int eVIRTUAL_UNSET) {
		EVIRTUAL_UNSET = eVIRTUAL_UNSET;
	}

	public int getEVIRTUAL_GET() {
		return EVIRTUAL_GET;
	}

	public void setEVIRTUAL_GET(int eVIRTUAL_GET) {
		EVIRTUAL_GET = eVIRTUAL_GET;
	}

	public int getEVIRTUAL_IS_SET() {
		return EVIRTUAL_IS_SET;
	}

	public void setEVIRTUAL_IS_SET(int eVIRTUAL_IS_SET) {
		EVIRTUAL_IS_SET = eVIRTUAL_IS_SET;
	}

	public java.lang.Object getEVIRTUAL_NO_VALUE() {
		return EVIRTUAL_NO_VALUE;
	}

	public void setEVIRTUAL_NO_VALUE(java.lang.Object eVIRTUAL_NO_VALUE) {
		EVIRTUAL_NO_VALUE = eVIRTUAL_NO_VALUE;
	}

	public boolean is$assertionsDisabled() {
		return $assertionsDisabled;
	}

	public void set$assertionsDisabled(boolean $assertionsDisabled) {
		this.$assertionsDisabled = $assertionsDisabled;
	}

}
