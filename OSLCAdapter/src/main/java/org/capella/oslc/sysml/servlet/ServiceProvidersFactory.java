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

package org.capella.oslc.sysml.servlet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.PrefixDefinition;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderFactory;
import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;

import org.capella.oslc.sysml.ServiceProviderInfo;

import org.eclipse.lyo.oslc.domains.am.Oslc_amDomainConstants;
import org.eclipse.lyo.oslc.domains.DctermsDomainConstants;
import org.eclipse.lyo.oslc.domains.FoafDomainConstants;
import org.eclipse.lyo.oslc.domains.jazz_am.Jazz_amDomainConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcDomainConstants;
import org.oasis.oslcop.sysml.SysmlDomainConstants;
import org.capella.oslc.sysml.services.ServiceProviderService1;
import org.capella.oslc.sysml.services.ServiceProviderService2;
import org.capella.oslc.sysml.services.ServiceProviderService3;
import org.capella.oslc.sysml.services.ServiceProviderService4;
import org.capella.oslc.sysml.services.ServiceProviderService5;
import org.capella.oslc.sysml.services.ServiceProviderService6;
import org.capella.oslc.sysml.services.ServiceProviderService7;
import org.capella.oslc.sysml.services.ServiceProviderService8;
import org.capella.oslc.sysml.services.ServiceProviderService9;
import org.capella.oslc.sysml.services.ServiceProviderService10;
import org.capella.oslc.sysml.services.ServiceProviderService11;
import org.capella.oslc.sysml.services.ServiceProviderService12;
import org.capella.oslc.sysml.services.ServiceProviderService13;

// Start of user code imports
// End of user code

public class ServiceProvidersFactory
{
    private static Class<?>[] RESOURCE_CLASSES =
    {
        ServiceProviderService2.class, ServiceProviderService3.class, ServiceProviderService4.class, ServiceProviderService5.class, ServiceProviderService9.class, ServiceProviderService12.class, ServiceProviderService13.class
    };

    private ServiceProvidersFactory()
    {
        super();
    }

    public static URI constructURI(final String projectId)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("projectId", projectId);
        String instanceURI = "projects/{projectId}";

        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }

    public static URI constructURI(final ServiceProviderInfo serviceProviderInfo)
    {
        return constructURI(serviceProviderInfo.projectId);
    }

    public static String constructIdentifier(final String projectId)
    {
        return projectId;
    }

    public static String constructIdentifier(final ServiceProviderInfo serviceProviderInfo)
    {
        return constructIdentifier(serviceProviderInfo.projectId);
    }

    public static ServiceProvider createServiceProvider(final ServiceProviderInfo serviceProviderInfo) 
            throws OslcCoreApplicationException, URISyntaxException, IllegalArgumentException {
        // Start of user code init
        // End of user code
        URI serviceProviderURI = constructURI(serviceProviderInfo);
        String identifier = constructIdentifier(serviceProviderInfo);
        String basePath = OSLC4JUtils.getServletURI();
        String title = serviceProviderInfo.name;
        String description = String.format("%s (id: %s; kind: %s)",
            "Capella Project",
            identifier,
            "Capella Project");
        Publisher publisher = null;
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("projectId", serviceProviderInfo.projectId);

        ServiceProvider serviceProvider = ServiceProviderFactory.createServiceProvider(basePath,
                                                    "",
                                                    title,
                                                    description,
                                                    publisher,
                                                    RESOURCE_CLASSES,
                                                    parameterMap);

        final PrefixDefinition[] prefixDefinitions =
        {
            new PrefixDefinition(OslcConstants.DCTERMS_NAMESPACE_PREFIX, new URI(OslcConstants.DCTERMS_NAMESPACE)),
            new PrefixDefinition(OslcConstants.OSLC_CORE_NAMESPACE_PREFIX, new URI(OslcConstants.OSLC_CORE_NAMESPACE)),
            new PrefixDefinition(OslcConstants.OSLC_DATA_NAMESPACE_PREFIX, new URI(OslcConstants.OSLC_DATA_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDF_NAMESPACE_PREFIX, new URI(OslcConstants.RDF_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDFS_NAMESPACE_PREFIX, new URI(OslcConstants.RDFS_NAMESPACE)),
            new PrefixDefinition(Oslc_amDomainConstants.ARCHITECTURE_MANAGEMENT_NAMSPACE_PREFIX, new URI(Oslc_amDomainConstants.ARCHITECTURE_MANAGEMENT_NAMSPACE))
,
            new PrefixDefinition(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE_PREFIX, new URI(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE))
,
            new PrefixDefinition(FoafDomainConstants.FOAF_NAMSPACE_PREFIX, new URI(FoafDomainConstants.FOAF_NAMSPACE))
,
            new PrefixDefinition(Jazz_amDomainConstants.JAZZ_ARCHITECTURE_MANAGEMENT_NAMSPACE_PREFIX, new URI(Jazz_amDomainConstants.JAZZ_ARCHITECTURE_MANAGEMENT_NAMSPACE))
,
            new PrefixDefinition(OslcDomainConstants.OSLC_NAMSPACE_PREFIX, new URI(OslcDomainConstants.OSLC_NAMSPACE))
,
            new PrefixDefinition(SysmlDomainConstants.SYSML_NAMSPACE_PREFIX, new URI(SysmlDomainConstants.SYSML_NAMSPACE))
        };
        serviceProvider.setPrefixDefinitions(prefixDefinitions);

        serviceProvider.setAbout(serviceProviderURI);
        serviceProvider.setIdentifier(identifier);
        serviceProvider.setCreated(new Date());
        serviceProvider.setDetails(new URI[] {serviceProviderURI});

        // Start of user code finalize
        // End of user code
        return serviceProvider;
    }
}
