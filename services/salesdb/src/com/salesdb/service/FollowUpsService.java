/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.salesdb.FollowUps;

/**
 * Service object for domain model class {@link FollowUps}.
 */
public interface FollowUpsService {

    /**
     * Creates a new FollowUps. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FollowUps if any.
     *
     * @param followUps Details of the FollowUps to be created; value cannot be null.
     * @return The newly created FollowUps.
     */
	FollowUps create(@Valid FollowUps followUps);


	/**
	 * Returns FollowUps by given id if exists.
	 *
	 * @param followupsId The id of the FollowUps to get; value cannot be null.
	 * @return FollowUps associated with the given followupsId.
     * @throws EntityNotFoundException If no FollowUps is found.
	 */
	FollowUps getById(Integer followupsId) throws EntityNotFoundException;

    /**
	 * Find and return the FollowUps by given id if exists, returns null otherwise.
	 *
	 * @param followupsId The id of the FollowUps to get; value cannot be null.
	 * @return FollowUps associated with the given followupsId.
	 */
	FollowUps findById(Integer followupsId);


	/**
	 * Updates the details of an existing FollowUps. It replaces all fields of the existing FollowUps with the given followUps.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on FollowUps if any.
     *
	 * @param followUps The details of the FollowUps to be updated; value cannot be null.
	 * @return The updated FollowUps.
	 * @throws EntityNotFoundException if no FollowUps is found with given input.
	 */
	FollowUps update(@Valid FollowUps followUps) throws EntityNotFoundException;

    /**
	 * Deletes an existing FollowUps with the given id.
	 *
	 * @param followupsId The id of the FollowUps to be deleted; value cannot be null.
	 * @return The deleted FollowUps.
	 * @throws EntityNotFoundException if no FollowUps found with the given id.
	 */
	FollowUps delete(Integer followupsId) throws EntityNotFoundException;

	/**
	 * Find all FollowUps matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FollowUps.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<FollowUps> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all FollowUps matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FollowUps.
     *
     * @see Pageable
     * @see Page
	 */
    Page<FollowUps> findAll(String query, Pageable pageable);

    /**
	 * Exports all FollowUps matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the FollowUps in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the FollowUps.
	 */
	long count(String query);

	/**
	 * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
	 * @return Paginated data with included fields.

     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
	Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);


}

