// Start of user code Copyright
/*
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Simple
 *
 * This file is generated by Lyo Designer (https://www.eclipse.org/lyo/)
 */
// End of user code

package org.oasis.oslcop.sysml;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.annotation.OslcAllowedValue;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcMemberProperty;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRdfCollectionType;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRange;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRepresentation;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Link;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.Representation;
import org.eclipse.lyo.oslc4j.core.model.ValueType;
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;
import org.eclipse.lyo.oslc4j.core.model.ResourceShapeFactory;

import org.oasis.oslcop.sysml.SysmlDomainConstants;
import org.oasis.oslcop.sysml.Namespace;

import org.oasis.oslcop.sysml.SysmlDomainConstants;

import org.oasis.oslcop.sysml.Annotation;
import org.oasis.oslcop.sysml.Comment;
import org.oasis.oslcop.sysml.Conjugation;
import org.oasis.oslcop.sysml.Documentation;
import org.oasis.oslcop.sysml.Element;
import org.oasis.oslcop.sysml.Feature;
import org.oasis.oslcop.sysml.FeatureMembership;
import org.oasis.oslcop.sysml.Generalization;
import org.oasis.oslcop.sysml.SysmlImport;
import org.oasis.oslcop.sysml.Membership;
import org.oasis.oslcop.sysml.Multiplicity;
import org.oasis.oslcop.sysml.Namespace;
import org.eclipse.lyo.oslc.domains.Person;
import org.oasis.oslcop.sysml.Relationship;
import org.oasis.oslcop.sysml.TextualRepresentation;
// Start of user code imports
// End of user code

// Start of user code preClassCode
// End of user code

// Start of user code classAnnotations
// End of user code
@OslcNamespace(SysmlDomainConstants.TYPE_NAMESPACE)
@OslcName(SysmlDomainConstants.TYPE_LOCALNAME)
@OslcResourceShape(title = "Type Resource Shape", describes = SysmlDomainConstants.TYPE_TYPE)
public class Type
    extends Namespace
    implements IType
{
    // Start of user code attributeAnnotation:isAbstract
    // End of user code
    private Boolean isAbstract;
    // Start of user code attributeAnnotation:isSufficient
    // End of user code
    private Boolean isSufficient;
    // Start of user code attributeAnnotation:isConjugated
    // End of user code
    private Boolean isConjugated;
    // Start of user code attributeAnnotation:ownedGeneralization
    // End of user code
    private Set<Link> ownedGeneralization = new HashSet<Link>();
    // Start of user code attributeAnnotation:ownedFeatureMembership_comp
    // End of user code
    private Set<Link> ownedFeatureMembership_comp = new HashSet<Link>();
    // Start of user code attributeAnnotation:feature
    // End of user code
    private Set<Link> feature = new HashSet<Link>();
    // Start of user code attributeAnnotation:ownedFeature
    // End of user code
    private Set<Link> ownedFeature = new HashSet<Link>();
    // Start of user code attributeAnnotation:input
    // End of user code
    private Set<Link> input = new HashSet<Link>();
    // Start of user code attributeAnnotation:output
    // End of user code
    private Set<Link> output = new HashSet<Link>();
    // Start of user code attributeAnnotation:inheritedMembership
    // End of user code
    private Set<Link> inheritedMembership = new HashSet<Link>();
    // Start of user code attributeAnnotation:endFeature
    // End of user code
    private Set<Link> endFeature = new HashSet<Link>();
    // Start of user code attributeAnnotation:ownedEndFeature
    // End of user code
    private Set<Link> ownedEndFeature = new HashSet<Link>();
    // Start of user code attributeAnnotation:ownedConjugator
    // End of user code
    private Link ownedConjugator;
    // Start of user code attributeAnnotation:featureMembership
    // End of user code
    private Set<Link> featureMembership = new HashSet<Link>();
    // Start of user code attributeAnnotation:inheritedFeature
    // End of user code
    private Set<Link> inheritedFeature = new HashSet<Link>();
    // Start of user code attributeAnnotation:multiplicity
    // End of user code
    private Link multiplicity;
    // Start of user code attributeAnnotation:ownedFeatureMembership
    // End of user code
    private Set<Link> ownedFeatureMembership = new HashSet<Link>();
    
    // Start of user code classAttributes
    // End of user code
    // Start of user code classMethods
    // End of user code
    public Type()
    {
        super();
    
        // Start of user code constructor1
        // End of user code
    }
    
    public Type(final URI about)
    {
        super(about);
    
        // Start of user code constructor2
        // End of user code
    }
    
    public static ResourceShape createResourceShape() throws OslcCoreApplicationException, URISyntaxException {
        return ResourceShapeFactory.createResourceShape(OSLC4JUtils.getServletURI(),
        OslcConstants.PATH_RESOURCE_SHAPES,
        SysmlDomainConstants.TYPE_PATH,
        Type.class);
    }
    
    
    public String toString()
    {
        return toString(false);
    }
    
    public String toString(boolean asLocalResource)
    {
        String result = "";
        // Start of user code toString_init
        // End of user code
    
        if (asLocalResource) {
            result = result + "{a Local Type Resource} - update Type.toString() to present resource as desired.";
            // Start of user code toString_bodyForLocalResource
            // End of user code
        }
        else {
            result = String.valueOf(getAbout());
        }
    
        // Start of user code toString_finalize
        result = getShortTitle();
        // End of user code
    
        return result;
    }
    
    public void addOwnedGeneralization(final Link ownedGeneralization)
    {
        this.ownedGeneralization.add(ownedGeneralization);
    }
    
    public void addOwnedFeatureMembership_comp(final Link ownedFeatureMembership_comp)
    {
        this.ownedFeatureMembership_comp.add(ownedFeatureMembership_comp);
    }
    
    public void addFeature(final Link feature)
    {
        this.feature.add(feature);
    }
    
    public void addOwnedFeature(final Link ownedFeature)
    {
        this.ownedFeature.add(ownedFeature);
    }
    
    public void addInput(final Link input)
    {
        this.input.add(input);
    }
    
    public void addOutput(final Link output)
    {
        this.output.add(output);
    }
    
    public void addInheritedMembership(final Link inheritedMembership)
    {
        this.inheritedMembership.add(inheritedMembership);
    }
    
    public void addEndFeature(final Link endFeature)
    {
        this.endFeature.add(endFeature);
    }
    
    public void addOwnedEndFeature(final Link ownedEndFeature)
    {
        this.ownedEndFeature.add(ownedEndFeature);
    }
    
    public void addFeatureMembership(final Link featureMembership)
    {
        this.featureMembership.add(featureMembership);
    }
    
    public void addInheritedFeature(final Link inheritedFeature)
    {
        this.inheritedFeature.add(inheritedFeature);
    }
    
    public void addOwnedFeatureMembership(final Link ownedFeatureMembership)
    {
        this.ownedFeatureMembership.add(ownedFeatureMembership);
    }
    
    
    // Start of user code getterAnnotation:isAbstract
    // End of user code
    @OslcName("isAbstract")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "isAbstract")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Boolean)
    @OslcReadOnly(false)
    public Boolean isIsAbstract()
    {
        // Start of user code getterInit:isAbstract
        // End of user code
        return isAbstract;
    }
    
    // Start of user code getterAnnotation:isSufficient
    // End of user code
    @OslcName("isSufficient")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "isSufficient")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Boolean)
    @OslcReadOnly(false)
    public Boolean isIsSufficient()
    {
        // Start of user code getterInit:isSufficient
        // End of user code
        return isSufficient;
    }
    
    // Start of user code getterAnnotation:isConjugated
    // End of user code
    @OslcName("isConjugated")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "isConjugated")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Boolean)
    @OslcReadOnly(false)
    public Boolean isIsConjugated()
    {
        // Start of user code getterInit:isConjugated
        // End of user code
        return isConjugated;
    }
    
    // Start of user code getterAnnotation:ownedGeneralization
    // End of user code
    @OslcName("ownedGeneralization")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "ownedGeneralization")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.GENERALIZATION_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getOwnedGeneralization()
    {
        // Start of user code getterInit:ownedGeneralization
        // End of user code
        return ownedGeneralization;
    }
    
    // Start of user code getterAnnotation:ownedFeatureMembership_comp
    // End of user code
    @OslcName("ownedFeatureMembership_comp")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "ownedFeatureMembership_comp")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATUREMEMBERSHIP_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getOwnedFeatureMembership_comp()
    {
        // Start of user code getterInit:ownedFeatureMembership_comp
        // End of user code
        return ownedFeatureMembership_comp;
    }
    
    // Start of user code getterAnnotation:feature
    // End of user code
    @OslcName("feature")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "feature")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATURE_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getFeature()
    {
        // Start of user code getterInit:feature
        // End of user code
        return feature;
    }
    
    // Start of user code getterAnnotation:ownedFeature
    // End of user code
    @OslcName("ownedFeature")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "ownedFeature")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATURE_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getOwnedFeature()
    {
        // Start of user code getterInit:ownedFeature
        // End of user code
        return ownedFeature;
    }
    
    // Start of user code getterAnnotation:input
    // End of user code
    @OslcName("input")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "input")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATURE_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getInput()
    {
        // Start of user code getterInit:input
        // End of user code
        return input;
    }
    
    // Start of user code getterAnnotation:output
    // End of user code
    @OslcName("output")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "output")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATURE_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getOutput()
    {
        // Start of user code getterInit:output
        // End of user code
        return output;
    }
    
    // Start of user code getterAnnotation:inheritedMembership
    // End of user code
    @OslcName("inheritedMembership")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "inheritedMembership")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.MEMBERSHIP_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getInheritedMembership()
    {
        // Start of user code getterInit:inheritedMembership
        // End of user code
        return inheritedMembership;
    }
    
    // Start of user code getterAnnotation:endFeature
    // End of user code
    @OslcName("endFeature")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "endFeature")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATURE_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getEndFeature()
    {
        // Start of user code getterInit:endFeature
        // End of user code
        return endFeature;
    }
    
    // Start of user code getterAnnotation:ownedEndFeature
    // End of user code
    @OslcName("ownedEndFeature")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "ownedEndFeature")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATURE_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getOwnedEndFeature()
    {
        // Start of user code getterInit:ownedEndFeature
        // End of user code
        return ownedEndFeature;
    }
    
    // Start of user code getterAnnotation:ownedConjugator
    // End of user code
    @OslcName("ownedConjugator")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "ownedConjugator")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.CONJUGATION_TYPE})
    @OslcReadOnly(false)
    public Link getOwnedConjugator()
    {
        // Start of user code getterInit:ownedConjugator
        // End of user code
        return ownedConjugator;
    }
    
    // Start of user code getterAnnotation:featureMembership
    // End of user code
    @OslcName("featureMembership")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "featureMembership")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATUREMEMBERSHIP_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getFeatureMembership()
    {
        // Start of user code getterInit:featureMembership
        // End of user code
        return featureMembership;
    }
    
    // Start of user code getterAnnotation:inheritedFeature
    // End of user code
    @OslcName("inheritedFeature")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "inheritedFeature")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATURE_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getInheritedFeature()
    {
        // Start of user code getterInit:inheritedFeature
        // End of user code
        return inheritedFeature;
    }
    
    // Start of user code getterAnnotation:multiplicity
    // End of user code
    @OslcName("multiplicity")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "multiplicity")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.MULTIPLICITY_TYPE})
    @OslcReadOnly(false)
    public Link getMultiplicity()
    {
        // Start of user code getterInit:multiplicity
        // End of user code
        return multiplicity;
    }
    
    // Start of user code getterAnnotation:ownedFeatureMembership
    // End of user code
    @OslcName("ownedFeatureMembership")
    @OslcPropertyDefinition(SysmlDomainConstants.SYSML_NAMSPACE + "ownedFeatureMembership")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({SysmlDomainConstants.FEATUREMEMBERSHIP_TYPE})
    @OslcReadOnly(false)
    public Set<Link> getOwnedFeatureMembership()
    {
        // Start of user code getterInit:ownedFeatureMembership
        // End of user code
        return ownedFeatureMembership;
    }
    
    
    // Start of user code setterAnnotation:isAbstract
    // End of user code
    public void setIsAbstract(final Boolean isAbstract )
    {
        // Start of user code setterInit:isAbstract
        // End of user code
        this.isAbstract = isAbstract;
    
        // Start of user code setterFinalize:isAbstract
        // End of user code
    }
    
    // Start of user code setterAnnotation:isSufficient
    // End of user code
    public void setIsSufficient(final Boolean isSufficient )
    {
        // Start of user code setterInit:isSufficient
        // End of user code
        this.isSufficient = isSufficient;
    
        // Start of user code setterFinalize:isSufficient
        // End of user code
    }
    
    // Start of user code setterAnnotation:isConjugated
    // End of user code
    public void setIsConjugated(final Boolean isConjugated )
    {
        // Start of user code setterInit:isConjugated
        // End of user code
        this.isConjugated = isConjugated;
    
        // Start of user code setterFinalize:isConjugated
        // End of user code
    }
    
    // Start of user code setterAnnotation:ownedGeneralization
    // End of user code
    public void setOwnedGeneralization(final Set<Link> ownedGeneralization )
    {
        // Start of user code setterInit:ownedGeneralization
        // End of user code
        this.ownedGeneralization.clear();
        if (ownedGeneralization != null)
        {
            this.ownedGeneralization.addAll(ownedGeneralization);
        }
    
        // Start of user code setterFinalize:ownedGeneralization
        // End of user code
    }
    
    // Start of user code setterAnnotation:ownedFeatureMembership_comp
    // End of user code
    public void setOwnedFeatureMembership_comp(final Set<Link> ownedFeatureMembership_comp )
    {
        // Start of user code setterInit:ownedFeatureMembership_comp
        // End of user code
        this.ownedFeatureMembership_comp.clear();
        if (ownedFeatureMembership_comp != null)
        {
            this.ownedFeatureMembership_comp.addAll(ownedFeatureMembership_comp);
        }
    
        // Start of user code setterFinalize:ownedFeatureMembership_comp
        // End of user code
    }
    
    // Start of user code setterAnnotation:feature
    // End of user code
    public void setFeature(final Set<Link> feature )
    {
        // Start of user code setterInit:feature
        // End of user code
        this.feature.clear();
        if (feature != null)
        {
            this.feature.addAll(feature);
        }
    
        // Start of user code setterFinalize:feature
        // End of user code
    }
    
    // Start of user code setterAnnotation:ownedFeature
    // End of user code
    public void setOwnedFeature(final Set<Link> ownedFeature )
    {
        // Start of user code setterInit:ownedFeature
        // End of user code
        this.ownedFeature.clear();
        if (ownedFeature != null)
        {
            this.ownedFeature.addAll(ownedFeature);
        }
    
        // Start of user code setterFinalize:ownedFeature
        // End of user code
    }
    
    // Start of user code setterAnnotation:input
    // End of user code
    public void setInput(final Set<Link> input )
    {
        // Start of user code setterInit:input
        // End of user code
        this.input.clear();
        if (input != null)
        {
            this.input.addAll(input);
        }
    
        // Start of user code setterFinalize:input
        // End of user code
    }
    
    // Start of user code setterAnnotation:output
    // End of user code
    public void setOutput(final Set<Link> output )
    {
        // Start of user code setterInit:output
        // End of user code
        this.output.clear();
        if (output != null)
        {
            this.output.addAll(output);
        }
    
        // Start of user code setterFinalize:output
        // End of user code
    }
    
    // Start of user code setterAnnotation:inheritedMembership
    // End of user code
    public void setInheritedMembership(final Set<Link> inheritedMembership )
    {
        // Start of user code setterInit:inheritedMembership
        // End of user code
        this.inheritedMembership.clear();
        if (inheritedMembership != null)
        {
            this.inheritedMembership.addAll(inheritedMembership);
        }
    
        // Start of user code setterFinalize:inheritedMembership
        // End of user code
    }
    
    // Start of user code setterAnnotation:endFeature
    // End of user code
    public void setEndFeature(final Set<Link> endFeature )
    {
        // Start of user code setterInit:endFeature
        // End of user code
        this.endFeature.clear();
        if (endFeature != null)
        {
            this.endFeature.addAll(endFeature);
        }
    
        // Start of user code setterFinalize:endFeature
        // End of user code
    }
    
    // Start of user code setterAnnotation:ownedEndFeature
    // End of user code
    public void setOwnedEndFeature(final Set<Link> ownedEndFeature )
    {
        // Start of user code setterInit:ownedEndFeature
        // End of user code
        this.ownedEndFeature.clear();
        if (ownedEndFeature != null)
        {
            this.ownedEndFeature.addAll(ownedEndFeature);
        }
    
        // Start of user code setterFinalize:ownedEndFeature
        // End of user code
    }
    
    // Start of user code setterAnnotation:ownedConjugator
    // End of user code
    public void setOwnedConjugator(final Link ownedConjugator )
    {
        // Start of user code setterInit:ownedConjugator
        // End of user code
        this.ownedConjugator = ownedConjugator;
    
        // Start of user code setterFinalize:ownedConjugator
        // End of user code
    }
    
    // Start of user code setterAnnotation:featureMembership
    // End of user code
    public void setFeatureMembership(final Set<Link> featureMembership )
    {
        // Start of user code setterInit:featureMembership
        // End of user code
        this.featureMembership.clear();
        if (featureMembership != null)
        {
            this.featureMembership.addAll(featureMembership);
        }
    
        // Start of user code setterFinalize:featureMembership
        // End of user code
    }
    
    // Start of user code setterAnnotation:inheritedFeature
    // End of user code
    public void setInheritedFeature(final Set<Link> inheritedFeature )
    {
        // Start of user code setterInit:inheritedFeature
        // End of user code
        this.inheritedFeature.clear();
        if (inheritedFeature != null)
        {
            this.inheritedFeature.addAll(inheritedFeature);
        }
    
        // Start of user code setterFinalize:inheritedFeature
        // End of user code
    }
    
    // Start of user code setterAnnotation:multiplicity
    // End of user code
    public void setMultiplicity(final Link multiplicity )
    {
        // Start of user code setterInit:multiplicity
        // End of user code
        this.multiplicity = multiplicity;
    
        // Start of user code setterFinalize:multiplicity
        // End of user code
    }
    
    // Start of user code setterAnnotation:ownedFeatureMembership
    // End of user code
    public void setOwnedFeatureMembership(final Set<Link> ownedFeatureMembership )
    {
        // Start of user code setterInit:ownedFeatureMembership
        // End of user code
        this.ownedFeatureMembership.clear();
        if (ownedFeatureMembership != null)
        {
            this.ownedFeatureMembership.addAll(ownedFeatureMembership);
        }
    
        // Start of user code setterFinalize:ownedFeatureMembership
        // End of user code
    }
    
    
}
