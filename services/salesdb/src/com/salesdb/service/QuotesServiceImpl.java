/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.salesdb.FollowUps;
import com.salesdb.Quotes;
import com.salesdb.Sales;


/**
 * ServiceImpl object for domain model class Quotes.
 *
 * @see Quotes
 */
@Service("salesdb.QuotesService")
@Validated
public class QuotesServiceImpl implements QuotesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotesServiceImpl.class);

    @Lazy
    @Autowired
	@Qualifier("salesdb.FollowUpsService")
	private FollowUpsService followUpsService;

    @Lazy
    @Autowired
	@Qualifier("salesdb.SalesService")
	private SalesService salesService;

    @Autowired
    @Qualifier("salesdb.QuotesDao")
    private WMGenericDao<Quotes, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Quotes, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
	public Quotes create(Quotes quotes) {
        LOGGER.debug("Creating a new Quotes with information: {}", quotes);
        Quotes quotesCreated = this.wmGenericDao.create(quotes);
        if(quotesCreated.getSaleses() != null) {
            for(Sales salese : quotesCreated.getSaleses()) {
                salese.setQuotes(quotesCreated);
                LOGGER.debug("Creating a new child Sales with information: {}", salese);
                salesService.create(salese);
            }
        }

        if(quotesCreated.getFollowUpses() != null) {
            for(FollowUps followUpse : quotesCreated.getFollowUpses()) {
                followUpse.setQuotes(quotesCreated);
                LOGGER.debug("Creating a new child FollowUps with information: {}", followUpse);
                followUpsService.create(followUpse);
            }
        }
        return quotesCreated;
    }

	@Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public Quotes getById(Integer quotesId) throws EntityNotFoundException {
        LOGGER.debug("Finding Quotes by id: {}", quotesId);
        Quotes quotes = this.wmGenericDao.findById(quotesId);
        if (quotes == null){
            LOGGER.debug("No Quotes found with id: {}", quotesId);
            throw new EntityNotFoundException(String.valueOf(quotesId));
        }
        return quotes;
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public Quotes findById(Integer quotesId) {
        LOGGER.debug("Finding Quotes by id: {}", quotesId);
        return this.wmGenericDao.findById(quotesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "salesdbTransactionManager")
	@Override
	public Quotes update(Quotes quotes) throws EntityNotFoundException {
        LOGGER.debug("Updating Quotes with information: {}", quotes);
        this.wmGenericDao.update(quotes);

        Integer quotesId = quotes.getId();

        return this.wmGenericDao.findById(quotesId);
    }

    @Transactional(value = "salesdbTransactionManager")
	@Override
	public Quotes delete(Integer quotesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Quotes with id: {}", quotesId);
        Quotes deleted = this.wmGenericDao.findById(quotesId);
        if (deleted == null) {
            LOGGER.debug("No Quotes found with id: {}", quotesId);
            throw new EntityNotFoundException(String.valueOf(quotesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public Page<Quotes> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Quotes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<Quotes> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Quotes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service salesdb for table Quotes to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<Sales> findAssociatedSaleses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated saleses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("quotes.id = '" + id + "'");

        return salesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<FollowUps> findAssociatedFollowUpses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated followUpses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("quotes.id = '" + id + "'");

        return followUpsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FollowUpsService instance
	 */
	protected void setFollowUpsService(FollowUpsService service) {
        this.followUpsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SalesService instance
	 */
	protected void setSalesService(SalesService service) {
        this.salesService = service;
    }

}

