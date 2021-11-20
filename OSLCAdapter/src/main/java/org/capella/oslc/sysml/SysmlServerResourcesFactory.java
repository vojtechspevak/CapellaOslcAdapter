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
 */
// End of user code

package org.capella.oslc.sysml;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.lyo.oslc4j.core.model.Link;
import org.oasis.oslcop.sysml.*;
import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;

// Start of user code imports
// End of user code

// Start of user code pre_class_code
// End of user code

public class SysmlServerResourcesFactory {

    // Start of user code class_attributes
    // End of user code
    
    // Start of user code class_methods
    // End of user code

    //methods for AttributeUsage resource
    
    public static AttributeUsage createAttributeUsage(final String projectId, final String id)
    {
        return new AttributeUsage(constructURIForAttributeUsage(projectId, id));
    }
    
    public static URI constructURIForAttributeUsage(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/attributeUsages/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForAttributeUsage(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForAttributeUsage(projectId, id), label);
    }
    
    public static Link constructLinkForAttributeUsage(final String projectId, final String id)
    {
        return new Link(constructURIForAttributeUsage(projectId, id));
    }
    

    //methods for SysmlClass resource
    
    public static SysmlClass createSysmlClass(final String projectId, final String id)
    {
        return new SysmlClass(constructURIForSysmlClass(projectId, id));
    }
    
    public static URI constructURIForSysmlClass(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/classes/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForSysmlClass(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForSysmlClass(projectId, id), label);
    }
    
    public static Link constructLinkForSysmlClass(final String projectId, final String id)
    {
        return new Link(constructURIForSysmlClass(projectId, id));
    }
    

    //methods for Element resource
    
    public static Element createElement(final String projectId, final String id)
    {
        return new Element(constructURIForElement(projectId, id));
    }
    
    public static URI constructURIForElement(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/elements/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForElement(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForElement(projectId, id), label);
    }
    
    public static Link constructLinkForElement(final String projectId, final String id)
    {
        return new Link(constructURIForElement(projectId, id));
    }
    

    //methods for Feature resource
    
    public static Feature createFeature(final String projectId, final String id)
    {
        return new Feature(constructURIForFeature(projectId, id));
    }
    
    public static URI constructURIForFeature(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/features/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForFeature(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForFeature(projectId, id), label);
    }
    
    public static Link constructLinkForFeature(final String projectId, final String id)
    {
        return new Link(constructURIForFeature(projectId, id));
    }
    

    //methods for FeatureMembership resource
    
    public static FeatureMembership createFeatureMembership(final String projectId, final String id)
    {
        return new FeatureMembership(constructURIForFeatureMembership(projectId, id));
    }
    
    public static URI constructURIForFeatureMembership(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/featureMemberships/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForFeatureMembership(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForFeatureMembership(projectId, id), label);
    }
    
    public static Link constructLinkForFeatureMembership(final String projectId, final String id)
    {
        return new Link(constructURIForFeatureMembership(projectId, id));
    }
    

    //methods for FeatureTyping resource
    
    public static FeatureTyping createFeatureTyping(final String projectId, final String id)
    {
        return new FeatureTyping(constructURIForFeatureTyping(projectId, id));
    }
    
    public static URI constructURIForFeatureTyping(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/featureTypings/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForFeatureTyping(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForFeatureTyping(projectId, id), label);
    }
    
    public static Link constructLinkForFeatureTyping(final String projectId, final String id)
    {
        return new Link(constructURIForFeatureTyping(projectId, id));
    }
    

    //methods for Generalization resource
    
    public static Generalization createGeneralization(final String projectId, final String id)
    {
        return new Generalization(constructURIForGeneralization(projectId, id));
    }
    
    public static URI constructURIForGeneralization(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/generalizations/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForGeneralization(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForGeneralization(projectId, id), label);
    }
    
    public static Link constructLinkForGeneralization(final String projectId, final String id)
    {
        return new Link(constructURIForGeneralization(projectId, id));
    }
    

    //methods for PartUsage resource
    
    public static PartUsage createPartUsage(final String projectId, final String id)
    {
        return new PartUsage(constructURIForPartUsage(projectId, id));
    }
    
    public static URI constructURIForPartUsage(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/partUsages/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForPartUsage(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForPartUsage(projectId, id), label);
    }
    
    public static Link constructLinkForPartUsage(final String projectId, final String id)
    {
        return new Link(constructURIForPartUsage(projectId, id));
    }
    

    //methods for PortUsage resource
    
    public static PortUsage createPortUsage(final String projectId, final String id)
    {
        return new PortUsage(constructURIForPortUsage(projectId, id));
    }
    
    public static URI constructURIForPortUsage(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/portUsages/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForPortUsage(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForPortUsage(projectId, id), label);
    }
    
    public static Link constructLinkForPortUsage(final String projectId, final String id)
    {
        return new Link(constructURIForPortUsage(projectId, id));
    }
    

    //methods for Relationship resource
    
    public static Relationship createRelationship(final String projectId, final String id)
    {
        return new Relationship(constructURIForRelationship(projectId, id));
    }
    
    public static URI constructURIForRelationship(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/relationships/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForRelationship(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForRelationship(projectId, id), label);
    }
    
    public static Link constructLinkForRelationship(final String projectId, final String id)
    {
        return new Link(constructURIForRelationship(projectId, id));
    }
    

    //methods for Subsetting resource
    
    public static Subsetting createSubsetting(final String projectId, final String id)
    {
        return new Subsetting(constructURIForSubsetting(projectId, id));
    }
    
    public static URI constructURIForSubsetting(final String projectId, final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        pathParameters.put("id", id);
        String instanceURI = "projects/{projectId}/subsettings/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForSubsetting(final String projectId, final String id , final String label)
    {
        return new Link(constructURIForSubsetting(projectId, id), label);
    }
    
    public static Link constructLinkForSubsetting(final String projectId, final String id)
    {
        return new Link(constructURIForSubsetting(projectId, id));
    }
    

}
