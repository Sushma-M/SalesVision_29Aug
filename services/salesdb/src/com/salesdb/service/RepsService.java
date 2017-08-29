/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.salesdb.Quotes;
import com.salesdb.Reps;
import com.salesdb.Tasks;

/**
 * Service object for domain model class {@link Reps}.
 */
public interface RepsService {

    /**
     * Creates a new Reps. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Reps if any.
     *
     * @param reps Details of the Reps to be created; value cannot be null.
     * @return The newly created Reps.
     */
	Reps create(Reps reps);


	/**
	 * Returns Reps by given id if exists.
	 *
	 * @param repsId The id of the Reps to get; value cannot be null.
	 * @return Reps associated with the given repsId.
     * @throws EntityNotFoundException If no Reps is found.
	 */
	Reps getById(Integer repsId) throws EntityNotFoundException;

    /**
	 * Find and return the Reps by given id if exists, returns null otherwise.
	 *
	 * @param repsId The id of the Reps to get; value cannot be null.
	 * @return Reps associated with the given repsId.
	 */
	Reps findById(Integer repsId);


	/**
	 * Updates the details of an existing Reps. It replaces all fields of the existing Reps with the given reps.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Reps if any.
     *
	 * @param reps The details of the Reps to be updated; value cannot be null.
	 * @return The updated Reps.
	 * @throws EntityNotFoundException if no Reps is found with given input.
	 */
	Reps update(Reps reps) throws EntityNotFoundException;

    /**
	 * Deletes an existing Reps with the given id.
	 *
	 * @param repsId The id of the Reps to be deleted; value cannot be null.
	 * @return The deleted Reps.
	 * @throws EntityNotFoundException if no Reps found with the given id.
	 */
	Reps delete(Integer repsId) throws EntityNotFoundException;

	/**
	 * Find all Reps matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Reps.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Reps> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Reps matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Reps.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Reps> findAll(String query, Pageable pageable);

    /**
	 * Exports all Reps matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Reps in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Reps.
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

    /*
     * Returns the associated taskses for given Reps id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Tasks instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Tasks> findAssociatedTaskses(Integer id, Pageable pageable);

    /*
     * Returns the associated quoteses for given Reps id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Quotes instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Quotes> findAssociatedQuoteses(Integer id, Pageable pageable);

}

